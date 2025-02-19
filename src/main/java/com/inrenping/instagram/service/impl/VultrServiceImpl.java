package com.inrenping.instagram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inrenping.instagram.entity.qcloud.InstagramQuery;
import com.inrenping.instagram.entity.response.*;
import com.inrenping.instagram.repository.*;
import com.inrenping.instagram.service.IVultrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.*;
import java.util.*;

@Service
public class VultrServiceImpl implements IVultrService {

//    @Value("${instagram.query_id}")
//    private String instagram_query_id;
//    @Value("${instagram.query_hash}")
//    private String instagram_query_hash;
//    @Value("${instagram.id}")
//    private String instagram_id;
//    @Value("${instagram.cookie}")
//    private String instagram_cookie;
//    @Value("${instagram.user-agent}")
//    private String instagram_user_agent;

    @Autowired
    InstagramQueryRepository instagramQueryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String firstFetch(String id,String cookie,String userAgent) {
        try {
            RestTemplate client = new RestTemplate();
            String uri = String.format("https://www.instagram.com/%d/?__a=1&__d=dis",id);
            HttpHeaders headers = new HttpHeaders();
            List<String> cookies = new ArrayList<>();
            cookies.add(cookie);
            headers.add("User-Agent", userAgent);
            headers.put(HttpHeaders.COOKIE, cookies);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            System.out.println(uri);
            ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String firstFetchWithUpdate(String user_id,String instagramId,String cookie,String userAgent) {
        try {
            String first = firstFetch(user_id,cookie,userAgent);
            FirstResponse firstResponse = JSONObject.parseObject(first, FirstResponse.class);
            for (Edge_ edge : firstResponse.getGraphql().getUser().getEdge_owner_to_timeline_media().getEdges()) {
                if (edge.getNode().get__typename().equals("GraphImage")) {
                    InstagramQuery query = new InstagramQuery();
                    query.setUserId(Long.parseLong(instagramId));
                    query.setType_name(edge.getNode().get__typename());
                    query.setInsId(edge.getNode().getId());
                    query.setShort_code(edge.getNode().getShortcode());
                    query.setDisplay_url(edge.getNode().getDisplay_url());
                    if (edge.getNode().getLocation() != null) {
                        query.setLocationId(edge.getNode().getLocation().getId());
                        query.setLocationName(edge.getNode().getLocation().getName());
                    }
                    query.setTaken_at_timestamp(edge.getNode().getTaken_at_timestamp());
                    query.setEndCursor(firstResponse.getGraphql().getUser().getEdge_owner_to_timeline_media().getPage_info().getEnd_cursor());
                    query.setLikeCount(edge.getNode().getEdge_media_preview_like().getCount());
                    query.setCreatetime(new Date());
                    query.setIsChild(0);
                    InstagramQuery result = instagramQueryRepository.save(query);
                    System.out.println(JSON.toJSONString(result));
                } else if (edge.getNode().get__typename().equals("GraphSidecar")) {
                    if (edge.getNode().getEdge_sidecar_to_children() != null) {
                        for (Edge_ edge_child : edge.getNode().getEdge_sidecar_to_children().getEdges()) {
                            InstagramQuery query = new InstagramQuery();
                            query.setUserId(Long.parseLong(instagramId));
                            query.setType_name(edge_child.getNode().get__typename());
                            query.setInsId(edge_child.getNode().getId());
                            query.setShort_code(edge_child.getNode().getShortcode());
                            query.setDisplay_url(edge_child.getNode().getDisplay_url());
                            query.setTaken_at_timestamp(edge.getNode().getTaken_at_timestamp());
                            query.setLikeCount(edge.getNode().getEdge_media_preview_like().getCount());
                            query.setCreatetime(new Date());
                            query.setEndCursor(firstResponse.getGraphql().getUser().getEdge_owner_to_timeline_media().getPage_info().getEnd_cursor());
                            query.setIsChild(1);
                            InstagramQuery result = instagramQueryRepository.save(query);
                            System.out.println(JSON.toJSONString(result));
                        }
                    }
                }
            }
            return "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String secondFetch(String end_cursor,String instagramId,String instagramQueryId,String cookie,String userAgent) {
        try {
            Thread.sleep(1000 * 10);
            RestTemplate client = new RestTemplate();
            String uri = "https://instagram.com/graphql/query/";
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
                    .queryParam("query_id", instagramQueryId)
                    .queryParam("id", instagramId)
                    .queryParam("first", 50)
                    .queryParam("after", end_cursor).build();
            List<String> cookies = new ArrayList<>();
            cookies.add(cookie);
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", userAgent);
            headers.put(HttpHeaders.COOKIE, cookies);
            HttpEntity entity = new HttpEntity("", headers);
            System.out.println(builder.toUriString());
            ResponseEntity<String> response = client.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String secondFetchWithUpdate(String end_cursor,String instagramId,String instagramQueryId,String cookie,String userAgent) {
        String second = secondFetch(end_cursor,instagramId,instagramQueryId,cookie,userAgent);
        SecondResponse secondResponse = JSONObject.parseObject(second, SecondResponse.class);
        for (Edge_ edge : secondResponse.getData().getUser().getEdge_owner_to_timeline_media().getEdges()) {
            // System.out.println(edge.getNode().get__typename());
            if (edge.getNode().get__typename().equals("GraphImage")) {
                List<InstagramQuery> list = instagramQueryRepository.findByInsId(edge.getNode().getId());
                if (list.size() > 0) {
                    continue;
                }
                InstagramQuery query = new InstagramQuery();
                query.setUserId(Long.parseLong(instagramId));
                query.setType_name(edge.getNode().get__typename());
                query.setInsId(edge.getNode().getId());
                query.setShort_code(edge.getNode().getShortcode());
                query.setDisplay_url(edge.getNode().getDisplay_url());
                if (edge.getNode().getLocation() != null) {
                    query.setLocationId(edge.getNode().getLocation().getId());
                    query.setLocationName(edge.getNode().getLocation().getName());
                }
                query.setTaken_at_timestamp(edge.getNode().getTaken_at_timestamp());
                query.setEndCursor(secondResponse.getData().getUser().getEdge_owner_to_timeline_media().getPage_info().getEnd_cursor());
                if (edge.getNode().getEdge_media_preview_like() != null) {
                    query.setLikeCount(edge.getNode().getEdge_media_preview_like().getCount());
                }
                query.setCreatetime(new Date());
                query.setIsChild(0);
                instagramQueryRepository.save(query);
                System.out.println(JSON.toJSONString(query));
            } else if (edge.getNode().get__typename().equals("GraphSidecar")) {
                thirdFetchWithUpdate(edge.getNode().getShortcode(),instagramId,cookie,userAgent);
            }
        }
        return secondResponse.getData().getUser().getEdge_owner_to_timeline_media().getPage_info().getEnd_cursor();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String thirdFetch(String shortcode,String cookie,String userAgent) {
        try {
            RestTemplate client = new RestTemplate();
            String uri = "https://www.instagram.com/p/" + shortcode + "/?__a=1";
            HttpHeaders headers = new HttpHeaders();
            List<String> cookies = new ArrayList<>();
            cookies.add(cookie);
            headers.add("User-Agent", userAgent);
            headers.put(HttpHeaders.COOKIE, cookies);
            HttpEntity entity = new HttpEntity("", headers);
            System.out.println(uri);
            Thread.sleep(1000 * 1);
            ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String thirdFetchWithUpdate(String shortcode,String instagramId,String cookie,String userAgent) {
        String third = thirdFetch(shortcode,cookie,userAgent);
        ThirdResponse thirdResponse = JSONObject.parseObject(third, ThirdResponse.class);
        if (thirdResponse.getGraphql().getShortcode_media().getEdge_sidecar_to_children() != null) {
            for (Edge_ edge : thirdResponse.getGraphql().getShortcode_media().getEdge_sidecar_to_children().getEdges()) {
                List<InstagramQuery> list = instagramQueryRepository.findByInsId(edge.getNode().getId());
                if (list.size() > 0) {
                    continue;
                }
                InstagramQuery query = new InstagramQuery();
                query.setTaken_at_timestamp(thirdResponse.getGraphql().getShortcode_media().getTaken_at_timestamp());
                query.setUserId(Long.parseLong(instagramId));
                query.setType_name(edge.getNode().get__typename());
                query.setInsId(edge.getNode().getId());
                query.setShort_code(edge.getNode().getShortcode());
                query.setDisplay_url(edge.getNode().getDisplay_url());
                if (edge.getNode().getLocation() != null) {
                    query.setLocationId(edge.getNode().getLocation().getId());
                    query.setLocationName(edge.getNode().getLocation().getName());
                }
                if (edge.getNode().getEdge_media_preview_like() != null) {
                    query.setLikeCount(edge.getNode().getEdge_media_preview_like().getCount());
                }
                query.setCreatetime(new Date());
                query.setIsChild(1);
                instagramQueryRepository.save(query);
                System.out.println(JSON.toJSONString(query));
            }
        }
        return "success";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String recursionFetchQueryWithUpdate(String end_cursor,String instagramId,String instagramQueryId,String cookie,String userAgent) {
        // do {
        try {
            Thread.sleep(1000 * 3);
            end_cursor = secondFetchWithUpdate(end_cursor,instagramId,instagramQueryId,cookie,userAgent);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "fail";
        }
        // } while (end_cursor.length() == 0);
        return "success";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String download() {
        List<InstagramQuery> query = instagramQueryRepository.findAll();
        for (InstagramQuery ins : query) {
            File file = downloadFile(ins.getDisplay_url(), (ins.getInsId()) + ".jpg");
            System.out.println(ins.getDisplay_url());
            System.out.println(file.getName());
        }
        return "success";
    }

    public File downloadFile(String urlPath, String fileName) {
        File file = null;
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(1000 * 10);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            String path = "/var/www/instagram/files/" + File.separatorChar + fileName;
            // String path = "D:\\instagram" + File.separatorChar + fileName;
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            byte[] b = new byte[2048];
            //把输入流的文件读取到字节数据 b 中，然后输出到指定目录的文件
            while ((size = bin.read(b)) != -1) {
                out.write(b, 0, size);
            }
            // 关闭资源
            bin.close();
            out.close();
            System.out.println("文件下载成功！");
        } catch (MalformedURLException e) {
            System.out.println("文件下载失败！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件下载失败！");
        }
        return file;
    }
}

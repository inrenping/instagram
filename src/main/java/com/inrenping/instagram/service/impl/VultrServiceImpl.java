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

import java.io.*;
import java.net.*;
import java.util.*;

@Service
public class VultrServiceImpl implements IVultrService {

    @Value("${instagram.query_id}")
    private String instagram_query_id;
    @Value("${instagram.query_hash}")
    private String instagram_query_hash;
    @Value("${instagram.id}")
    private String instagram_id;
    @Value("${instagram.cookie}")
    private String instagram_cookie;
    @Value("${instagram.user-agent}")
    private String instagram_user_agent;

    @Autowired
    InstagramQueryRepository instagramQueryRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String firstFetch() {
        try {
            RestTemplate client = new RestTemplate();
            String uri = "https://www.instagram.com/inrenping/?__a=1";
            HttpHeaders headers = new HttpHeaders();
            List<String> cookies = new ArrayList<>();
            cookies.add(instagram_cookie);
            headers.add("User-Agent", instagram_user_agent);
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
    public String firstFetchWithUpdate() {
        try {
            String first = firstFetch();
            FirstResponse firstResponse = JSONObject.parseObject(first, FirstResponse.class);
            for (Edge_ edge : firstResponse.getGraphql().getUser().getEdge_owner_to_timeline_media().getEdges()) {
                InstagramQuery query = new InstagramQuery();
                query.setUserId(Long.parseLong(instagram_id));
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
                if (edge.getNode().getEdge_sidecar_to_children() != null) {
                    for (Edge_ edge_child : edge.getNode().getEdge_sidecar_to_children().getEdges()) {
                        InstagramQuery query_c = new InstagramQuery();
                        query_c.setUserId(Long.parseLong(instagram_id));
                        query_c.setType_name(edge_child.getNode().get__typename());
                        query_c.setInsId(edge_child.getNode().getId());
                        query_c.setShort_code(edge_child.getNode().getShortcode());
                        query_c.setDisplay_url(edge_child.getNode().getDisplay_url());
                        query_c.setTaken_at_timestamp(edge.getNode().getTaken_at_timestamp());
                        query_c.setLikeCount(edge.getNode().getEdge_media_preview_like().getCount());
                        query_c.setCreatetime(new Date());
                        query_c.setEndCursor(firstResponse.getGraphql().getUser().getEdge_owner_to_timeline_media().getPage_info().getEnd_cursor());
                        query_c.setIsChild(1);
                        instagramQueryRepository.save(query_c);
                        InstagramQuery result_c = instagramQueryRepository.save(query_c);
                        System.out.println(JSON.toJSONString(result_c));
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
    public String secondFetch(String end_cursor) {
        try {
            RestTemplate client = new RestTemplate();
            String uri = "https://instagram.com/graphql/query/?query_id={query_id}&id={id}&first={first}&after={after}";
            List<String> cookies = new ArrayList<>();
            cookies.add(instagram_cookie);
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", instagram_user_agent);
            headers.put(HttpHeaders.COOKIE, cookies);
            HashMap<String, Object> params = new HashMap<>();
            params.put("query_id", instagram_query_id);
            params.put("id", instagram_id);
            params.put("first", 50);
            params.put("after", end_cursor);
            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class,params);
            return response.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String secondFetchWithUpdate(String end_cursor) {
        String second = secondFetch(end_cursor);
        SecondResponse secondResponse = JSONObject.parseObject(second, SecondResponse.class);
        for (Edge_ edge : secondResponse.getData().getUser().getEdge_owner_to_timeline_media().getEdges()) {
            InstagramQuery query = new InstagramQuery();
            query.setUserId(Long.parseLong(instagram_id));
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
            query.setLikeCount(edge.getNode().getEdge_media_preview_like().getCount());
            query.setCreatetime(new Date());
            query.setIsChild(0);
            InstagramQuery result = instagramQueryRepository.save(query);
            System.out.println(JSON.toJSONString(result));
//            // cant get getEdge_sidecar_to_children --!
//            if (edge.getNode().getEdge_sidecar_to_children() != null) {
//                for (Edge_ edge_child : edge.getNode().getEdge_sidecar_to_children().getEdges()) {
//                    InstagramQuery query_c = new InstagramQuery();
//                    query_c.setUserId(Long.parseLong(instagram_id));
//                    query_c.setType_name(edge_child.getNode().get__typename());
//                    query_c.setInsId(edge_child.getNode().getId());
//                    query_c.setShort_code(edge_child.getNode().getShortcode());
//                    query_c.setDisplay_url(edge_child.getNode().getDisplay_url().replace("\\u0026", "&"));
//                    query_c.setTaken_at_timestamp(edge.getNode().getTaken_at_timestamp());
//                    query_c.setLikeCount(edge.getNode().getEdge_media_preview_like().getCount());
//                    query_c.setCreatetime(new Date());
//                    query_c.setEndCursor(secondResponse.getData().getUser().getEdge_owner_to_timeline_media().getPage_info().getEnd_cursor());
//                    query_c.setIsChild(1);
//                    instagramQueryRepository.save(query_c);
//                    InstagramQuery result_c = instagramQueryRepository.save(query_c);
//                    System.out.println(JSON.toJSONString(result_c));
//                }
//            }
        }
        return secondResponse.getData().getUser().getEdge_owner_to_timeline_media().getPage_info().getEnd_cursor();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String thirdFetch(String shortcode) {
        try {
            RestTemplate client = new RestTemplate();
            String uri = "https://www.instagram.com/graphql/query/?query_hash={query_hash}&variables={variables}";
            Map<String, String> shortCodeParam =new HashMap<String,String>();
            shortCodeParam.put("shortcode",shortcode);
            HttpHeaders headers = new HttpHeaders();
            List<String> cookies = new ArrayList<>();
            cookies.add(instagram_cookie);
            headers.add("User-Agent", instagram_user_agent);
            headers.put(HttpHeaders.COOKIE, cookies);
            HashMap<String, Object> params = new HashMap<>();
            params.put("query_hash",instagram_query_hash);
            params.put("variables", JSON.toJSONString(shortCodeParam));
            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class,params);
            return response.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
    }

    @Override
    public String thirdFetchWithUpdate(String shortcode) {
        return "";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String recursionFetchQueryWithUpdate(String end_cursor) {
        do {
            try {
                end_cursor = secondFetchWithUpdate(end_cursor);
                Thread.sleep(1000 * 20);
            } catch (Exception ex) {
                ex.printStackTrace();
                return "fail";
            }
        } while (end_cursor != "");
        return "success";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String download() {
        try {
            List<InstagramQuery> query = instagramQueryRepository.findAll();
            for (InstagramQuery ins : query) {
                File file = downloadFile(ins.getDisplay_url(), String.valueOf(ins.getId()) + ".jpg");
                System.out.println(ins.getDisplay_url());
                System.out.println(file.getName());
                Thread.sleep(1000 * 3);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
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
            //把输入流的文件读取到字节数据b中，然后输出到指定目录的文件
            while ((size = bin.read(b)) != -1) {
                out.write(b, 0, size);
            }
            // 关闭资源
            bin.close();
            out.close();
            System.out.println("文件下载成功！");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.out.println("文件下载失败！");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("文件下载失败！");
        } finally {
            return file;
        }
    }
}

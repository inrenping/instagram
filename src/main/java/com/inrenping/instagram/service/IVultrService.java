package com.inrenping.instagram.service;

public interface IVultrService {

    /**
     * 前十二张图片获取
     * @param id
     * @param cookie
     * @param userAgent
     * @return
     */
    String firstFetch(String id,String cookie,String userAgent);

    /**
     * 前 12 张图片获取并写入
     * @return
     */
    String firstFetchWithUpdate(String user_id,String instagramId,String cookie,String user_agent);

    /**
     * 翻页获取
     * @param end_cursor
     * @return
     */
    String secondFetch(String end_cursor,String instagramId,String instagramQueryId,String cookie,String userAgent);

    /**
     * 翻页获取并写入
     * @param end_cursor
     * @param instagramId
     * @param instagramQueryId
     * @param cookie
     * @param userAgent
     * @return
     */
    String secondFetchWithUpdate(String end_cursor,String instagramId,String instagramQueryId,String cookie,String userAgent);

    /**
     *
     * @param shortcode
     * @param instagramId
     * @return
     */
    String thirdFetchWithUpdate(String shortcode,String instagramId,String cookie,String userAgent);

    /**
     * 自动翻页获取并写入
     * @param shortcode
     * @param cookie
     * @param userAgent
     * @return
     */
    String thirdFetch(String shortcode,String cookie,String userAgent);

    String recursionFetchQueryWithUpdate(String end_cursor,String instagramId,String instagramQueryId,String cookie,String userAgent);
    /**
     * 批量下载
     * @return
     */
    String download();
}

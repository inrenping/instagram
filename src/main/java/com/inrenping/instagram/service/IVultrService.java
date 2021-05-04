package com.inrenping.instagram.service;

public interface IVultrService {

    /**
     * 前十二张图片获取
     * @return
     */
    String firstFetch();

    /**
     * 前 12 张图片获取并写入
     * @return
     */
    String firstFetchWithUpdate();

    /**
     * 翻页获取
     * @param end_cursor
     * @return
     */
    String secondFetch(String end_cursor);

    /**
     * 翻页获取并写入
     * @param end_cursor
     * @return
     */
    String secondFetchWithUpdate(String end_cursor);

    /**
     * 自动翻页获取并写入
     * @param end_cursor
     * @return
     */
    String recursionFetchQueryWithUpdate(String end_cursor);

    /**
     * 批量下载
     * @return
     */
    String download();
}

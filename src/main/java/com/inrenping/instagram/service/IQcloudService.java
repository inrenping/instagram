package com.inrenping.instagram.service;

import com.inrenping.instagram.entity.qcloud.InstagramQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.util.*;

public interface IQcloudService {

    /**
     * 根据用户 ID 按页数获取图片列表
     * @param userId
     * @param pageable
     * @return
     */
    Page<InstagramQuery> pageByUserId(Long userId, Pageable pageable);

    /**
     * 获取单张图片信息
     * @param id
     * @return
     */
    InstagramQuery detail(Long id);

    /**
     * 获取图片文件
     * @param id
     * @return
     */
    File getFile(Long id);
}

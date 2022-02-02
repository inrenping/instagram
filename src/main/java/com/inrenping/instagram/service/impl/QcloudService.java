package com.inrenping.instagram.service.impl;

import com.inrenping.instagram.entity.qcloud.InstagramQuery;
import com.inrenping.instagram.repository.InstagramQueryRepository;
import com.inrenping.instagram.service.IQcloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class QcloudService implements IQcloudService {

    @Autowired
    InstagramQueryRepository instagramQueryRepository;

    @Override
    public Page<InstagramQuery> pageByUserId(Long userId, Pageable pageable) {
        Page<InstagramQuery> page = instagramQueryRepository.findByUserId(userId, pageable);
        return page;
    }

    @Override
    public InstagramQuery detail(Long id) {
        InstagramQuery instagramQuery = instagramQueryRepository.findById(id).get();
        return instagramQuery;
    }

    @Override
    public File getFile(Long id) {
        String baseUrl = "D://instagram/";
        // String baseUrl = "/var/www/instagram/instagram_files/";
         File file = new File(baseUrl + id.toString() + ".jpg");
        return file;
    }
}

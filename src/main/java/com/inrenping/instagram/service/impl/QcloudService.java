package com.inrenping.instagram.service.impl;

import com.inrenping.instagram.entity.qcloud.InstagramQuery;
import com.inrenping.instagram.repository.InstagramQueryRepository;
import com.inrenping.instagram.service.IQcloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

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
    public Page<InstagramQuery> pageByUserIdTypeName(Long userId,String typeName, Pageable pageable) {
        Page<InstagramQuery> page = instagramQueryRepository.findByUserIdTypeName(userId,typeName, pageable);
        return page;
    }

    @Override
    public InstagramQuery detail(Long id) {
        InstagramQuery instagramQuery = instagramQueryRepository.findById(id).get();
        return instagramQuery;
    }

    @Override
    public File getFile(Long id) {
        // File file = new File("D://instagram/" + id.toString() + ".jpg");
        File file = new File("/var/www/instagram/instagram_files/" + id.toString() + ".jpg");
        return file;
    }
}

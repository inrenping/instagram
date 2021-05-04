package com.inrenping.instagram.repository;

import com.inrenping.instagram.entity.qcloud.InstagramFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstagramFileReposity extends JpaRepository<InstagramFile, Long>, JpaSpecificationExecutor {

}
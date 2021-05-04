package com.inrenping.instagram.service;

import com.inrenping.instagram.entity.qcloud.InstagramQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.util.*;

public interface IQcloudService {

    Page<InstagramQuery> pageByUserId(Long userId, Pageable pageable);

    Page<InstagramQuery> pageByUserIdTypeName(Long userId,String typeName, Pageable pageable);

    InstagramQuery detail(Long id);

    File getFile(Long id);
}

package com.inrenping.instagram.repository;

import com.inrenping.instagram.entity.qcloud.InstagramQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstagramQueryRepository extends JpaRepository<InstagramQuery, Long>, JpaSpecificationExecutor {

    @Query(value = "from InstagramQuery t where t.userId = ?1")
    Page<InstagramQuery> findByUserId(Long userId,Pageable pageable);

    @Query(value = "from InstagramQuery t where t.userId = ?1 and t.type_name=?2")
    Page<InstagramQuery> findByUserIdTypeName(Long userId,String typeName,Pageable pageable);
}
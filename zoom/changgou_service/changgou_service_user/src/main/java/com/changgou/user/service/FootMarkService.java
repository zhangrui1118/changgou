package com.changgou.user.service;

import com.changgou.user.mongo.FootMark;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FootMarkService {
    void recordWithFootmarkToCookie(String skuId, String footmark_keyValue);

    void recordWithFootmarkToUsername(String skuId, String username);

    Page<FootMark> findAll(String username,String pageNum);
}

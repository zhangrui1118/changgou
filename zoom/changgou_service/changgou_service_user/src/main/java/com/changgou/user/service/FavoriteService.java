package com.changgou.user.service;

import com.changgou.goods.pojo.Sku;

import java.util.List;
import java.util.Map;

public interface FavoriteService {
    Map<String,Object> findFavoriteByUserName(String username, String pageNum);
}

package com.changgou.user.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author cyf
 */
public interface FootMarkMapper extends MongoRepository<FootMark, String> {

    //List<FootMark> findByUsername(String username);


    FootMark findBySkuIdAndUsername(String skuId, String username);

   // Page<FootMark> findByUsername(String username);


    FootMark findBySkuIdAndFootKey(String skuId, String footKey);

}

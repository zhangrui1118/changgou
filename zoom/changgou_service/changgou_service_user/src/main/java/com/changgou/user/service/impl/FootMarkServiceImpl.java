package com.changgou.user.service.impl;

import com.changgou.entity.Result;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.feign.SpuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.user.mongo.FootMark;
import com.changgou.user.mongo.FootMarkMapper;
import com.changgou.user.mongo.SpringDataPageable;
import com.changgou.user.service.FootMarkService;
import com.changgou.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/20
 * Time: 19:14
 * Description: ....
 */

@Service
public class FootMarkServiceImpl implements FootMarkService {

    @Autowired
    private FootMarkMapper footMarkMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SkuFeign skuFegin;

    @Autowired
    private SpuFeign spuFeign;


    /**
     * 查询所有的足迹信息
     *
     * @param
     * @return
     */
    @Override
    public Page<FootMark> findAll(String username, String pageNum) {
        int PageSize = 5;
        int PageNum = Integer.parseInt(pageNum);
        PageRequest pageable = new PageRequest(PageNum-1,PageSize);


        Page<FootMark> page = footMarkMapper.findAll(pageable);


        return page;
    }


    /**
     * 关联 cookie
     *
     * @param skuId
     * @param footKeyValue
     * @param
     * @return
     */
    @Override
    public void recordWithFootmarkToCookie(String skuId, String footKeyValue) {
        // 不能添加重复的足迹  存在就更新  不存在就插入
        FootMark footMark = footMarkMapper.findBySkuIdAndFootKey(skuId, footKeyValue);
        if (footMark == null) {
            // 插入
            footMark = createFootMark(skuId, footKeyValue, "");
        } else {
            // 更新
            footMark.setMarkTime(new Date());

        }
      /*  Spu spu = spuFeign.findSpuById(skuId).getData();
        if (spu != null) {
            footMark.setName(spu.getName());
            footMark.setImage(spu.getImage());
        }*/

        // 这个save 既是添加也是修改 有判空
        footMarkMapper.save(footMark);

    }

    /**
     * 关联用户名
     *
     * @param skuId
     * @param username
     */
    @Override
    public void recordWithFootmarkToUsername(String skuId, String username) {
        // 不能添加重复的足迹  存在就更新  不存在就插入
        FootMark footMark = footMarkMapper.findBySkuIdAndUsername(skuId, username);
        if (footMark == null) {
            // 插入
            footMark = createFootMark(skuId, "", username);
        } else {
            // 更新
            footMark.setMarkTime(new Date());

        }

     /*   Spu spu = spuFeign.findSpuById(skuId).getData();
        if (spu != null) {
            footMark.setName(spu.getName());
            footMark.setImage(spu.getImage());
        }*/

        // 这个save 既是添加也是修改 有判空
        footMarkMapper.save(footMark);

    }


    public FootMark createFootMark(String skuId, String footKeyValue, String username) {
        FootMark footMark = new FootMark();

        footMark.setMarkTime(new Date());
        footMark.setFootKey(footKeyValue);
        footMark.setUsername(username);
        footMark.setSkuId(skuId);
        footMark.setId(String.valueOf(idWorker.nextId()));
        // 调用 goods feign 接口
        Result<Spu> result = spuFeign.findSpuById(skuId);
        Spu spu = result.getData();
        if (spu != null) {
            footMark.setName(spu.getName());
            footMark.setImage(spu.getImage());
            String spuId = spu.getId();

            List<Sku> skuListBySpuId = skuFegin.findSkuListBySpuId(spuId);

            List<Integer> collect = skuListBySpuId.stream().map(s -> s.getPrice()).collect(Collectors.toList());
            Integer max = Collections.max(collect);
            Integer min = Collections.min(collect);

            footMark.setPrice(min + "--" + max);


        }
        return footMark;

    }
}

package com.changgou.web.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.web.pojo.AddressInfo;
import com.changgou.web.util.LogisticsUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/22
 * Time: 10:56
 * Description: ....
 */
@Controller
@RequestMapping("/wuser/map")
@CrossOrigin
public class MapController {


    /**
     * 地图服务
     */
    @GetMapping("/mapPage")
    public String showMap() {


        return "map";


    }

    /**
     * 地图数据
     *
     * @return
     */
    @GetMapping("/mapInfo")
    @ResponseBody
    public Result mapInfo() {
        // 117.33566,31.866749
        Map<String, String> hashMap = new HashMap<String, String>();
        List<Map> list = new ArrayList<>();


        String json = LogisticsUtils.logisticsAPI("4306401100051","");

        AddressInfo address = JSON.parseObject(json, AddressInfo.class);

        List<Map<String,String>> list1 = address.getResult().getList();
        for (Map map : list1) {
            String cityLoeLae = (String) map.get("cityLoeLae");
            if( ! StringUtils.isBlank(cityLoeLae) && !cityLoeLae.contains("[\"\",\"\",\"\"]")){
                ArrayList<String> arrayList = JSON.parseObject(cityLoeLae, ArrayList.class);


                String city = (String) arrayList.get(0);
                String longitude = (String) arrayList.get(1);
                String latitude = (String) arrayList.get(2);

                hashMap = new HashMap<>();
                hashMap.put("city",city);
                hashMap.put("longitude",longitude);
                hashMap.put("latitude",latitude);
                list.add(hashMap);

            }


        }

       /* map.put("longitude", "117.33566");
        map.put("latitude","31.866749");
        map.put("address","合肥黑马");*/



        return new  Result<Map>(true, StatusCode.OK,"成功",list);

    }


    /**
     * 地图第二页面
     *
     * @return
     */
    @GetMapping("/mapdetail")
    public String mapDetailInfo() {


        return "map_detail";


    }


}

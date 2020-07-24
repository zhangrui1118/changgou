package com.changgou.user.controller;

import com.changgou.user.config.TokenDecode;
import com.changgou.user.mongo.FootMark;
import com.changgou.user.service.FootMarkService;
import com.changgou.user.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/20
 * Time: 19:03
 * Description: ....
 */
@RestController
@RequestMapping("/user/footmark")
public class FootMarkController {

    @Autowired
    private FootMarkService footMarkService;

    @Autowired
    private TokenDecode tokenDecode;

    /**
     * 添加用户足迹
     *
     * @param
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/record/{spuId}")
    public String footMarkDetail(@PathVariable String spuId, HttpServletRequest request, HttpServletResponse response) {
        String footmark_keyValue = request.getHeader("footmark_key");
        if (StringUtils.isBlank(footmark_keyValue)) {

            footmark_keyValue = UUID.randomUUID().toString();

        }
        CookieUtil.addCookie(response, "120.78.212.224", "/", "footmark_key", footmark_keyValue, 7 * 24 * 60 * 60, true);

        String username = tokenDecode.getUserInfo().get("username");


        // 如果用户未登录关联 cookie  如果用户登录 关联username
        if (StringUtils.isBlank(username)) {
            footMarkService.recordWithFootmarkToCookie(spuId, footmark_keyValue);


        } else {
            footMarkService.recordWithFootmarkToUsername(spuId, username);

        }

        return "ok";


    }

    /**
     * 查询用户足迹
     *
     * @return
     */
    @GetMapping("/findAll")
    public Map<String, Object> findAllFootMark(@RequestParam("pageNum") String pageNum) {
        String username = tokenDecode.getUserInfo().get("username");
        Page<FootMark> page = footMarkService.findAll(username, pageNum);
        // 拆解
        Map<String, Object> map = new HashMap();
        long l = page.getTotalElements();
        map.put("totalNum", l);
        int totalPage = page.getTotalPages();
        map.put("totalPage", totalPage);
        List<FootMark> content = page.getContent();
        map.put("rows",content);

        return map;
    }


}

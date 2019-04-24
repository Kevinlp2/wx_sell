package com.lp.wx_sell.controller;

import com.google.common.collect.Maps;
import com.lp.wx_sell.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Liupeng
 * @date 2019/4/20 10:00
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeiXinController {

    @GetMapping("/getCode")
    public void getcode(@RequestParam("code") String code){
        log.info( "获取当前用户的授权码:{}",code );

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxcec0b9e65c084712&secret=05a7e861c1985ced86af77fb8f7163bc&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
//        Map<String, String> map = JsonUtil.string2object( forObject, new TypeReference<Map<String, String>>(){});
//        String access_token = map.get( "access_token" );
//        String openid = map.get( "openid" );
//        log.info( "access_token:",access_token );
//        log.info( "openid:",openid );
//        String url2="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
//        String forObject1 = restTemplate.getForObject( url2, String.class );
//        log.info( "用户信息：",forObject1 );

        System.out.println(forObject);
    }
}

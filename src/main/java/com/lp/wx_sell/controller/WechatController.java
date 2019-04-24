package com.lp.wx_sell.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Liupeng
 * @date 2019/4/20 10:23
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService wxMpService;

    @RequestMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        String url = "http;//xmcc.natapp1.cc/sell/wechat/getUserInfo";

        wxMpService.oauth2buildAuthorizationUrl( url, WxConsts.OAuth2Scope.SNSAPI_PRIVATEINFO, URLEncoder.encode( returnUrl,"UTF-8" ) );
        return "redirect:"+returnUrl;
    }
    @RequestMapping("/getUserInfo")
    public String getUserInfo(@RequestParam("code") String code,@RequestParam("state")String returnUrl) throws UnsupportedEncodingException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken( code );
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo( wxMpOAuth2AccessToken, null );
            log.info( "获取用户信息:{}",wxMpUser.getNickname() );
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
       //获取openid
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+ URLDecoder.decode( returnUrl,"UTF-8" )+"?openid="+openId;

    }

    //测试是否获得openid
    @RequestMapping("/testOpenid")
    public void testOpenid(@RequestParam("openid")String openid){
        log.info("获得用户的openid为:{}",openid);
    }
}

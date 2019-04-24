package com.lp.wx_sell.config;

import com.lp.wx_sell.properties.WeixinProperties;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liupeng
 * @date 2019/4/20 10:19
 */
@Configuration
public class WechatConfig {
    @Autowired
    private WeixinProperties weixinProperties;

    @Bean
    public WxMpService wxMpService(){
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage( wxMpConfigStorage() );
       return wxMpService;
    }

    private WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId( weixinProperties.getAppid() );
        wxMpInMemoryConfigStorage.setSecret( weixinProperties.getSecret() );
        return wxMpInMemoryConfigStorage;
    }
}

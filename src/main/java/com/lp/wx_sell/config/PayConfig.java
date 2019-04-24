package com.lp.wx_sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lp.wx_sell.properties.WeixinProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liupeng
 * @date 2019/4/23 9:02
 */
@Configuration
public class PayConfig {
    @Autowired
    private WeixinProperties weixinProperties;

    @Bean
    public BestPayService bestPayService(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId( weixinProperties.getAppid() );
        wxPayH5Config.setAppSecret( weixinProperties.getSecret() );
        wxPayH5Config.setKeyPath( weixinProperties.getKeyPath() );
        wxPayH5Config.setMchId( weixinProperties.getMchId() );
        wxPayH5Config.setMchKey( wxPayH5Config.getMchKey() );
        wxPayH5Config.setNotifyUrl( weixinProperties.getNotifyUrl() );

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config( wxPayH5Config );

        return bestPayService;
    }
}

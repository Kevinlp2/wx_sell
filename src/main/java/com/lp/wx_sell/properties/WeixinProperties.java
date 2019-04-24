package com.lp.wx_sell.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Liupeng
 * @date 2019/4/20 10:18
 */
@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WeixinProperties {
    private String appid;
    private String secret;
    //商户号
    private String mchId;

    //商户密钥
    private String mchKey;
    //商户证书路径
    private String keyPath;
    //微信支付异步通知
    private String notifyUrl;
}

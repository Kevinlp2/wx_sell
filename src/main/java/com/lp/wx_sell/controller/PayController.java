package com.lp.wx_sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.lp.wx_sell.entity.OrderMaster;
import com.lp.wx_sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liupeng
 * @date 2019/4/23 9:07
 */
@Controller
@RequestMapping("pay")
@Slf4j
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping("create")
    /**
     * 根据API文档创建接口
     * orderId: 订单ID 这里只能传递一个ID 防止别人传入非法的金额
     * returnUrl: 回调地址
     */
    public ModelAndView create(@RequestParam("orderId")String orderId,
                               @RequestParam("returnUrl")String returnUrl){
        //根据id查询订单
        OrderMaster orderMaster = payService.findOrderById(orderId);

        //根据订单创建支付
        PayResponse payResponse = payService.create( orderMaster );
        Map<String, Object> map = new HashMap<>();
        map.put( "payResponse",payResponse );
        map.put( "returnUrl",returnUrl );
        return new ModelAndView( "WeiXinPay",map );

    }
    @RequestMapping("test")
    public void test(){
        log.info("异步回调OK");
    }

    @RequestMapping("notify")
    public ModelAndView weixin_notify(@RequestBody String notifyData){
        log.info( "微信支付，异步回调" );
        payService.weixin_notify( notifyData );
        return  new ModelAndView( "success" );
    }
}

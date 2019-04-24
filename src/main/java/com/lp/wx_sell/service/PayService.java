package com.lp.wx_sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lp.wx_sell.entity.OrderMaster;

/**
 * @author Liupeng
 * @date 2019/4/23 9:08
 */
public interface PayService {
    OrderMaster findOrderById(String orderid);
    PayResponse create(OrderMaster orderMaster);

    void weixin_notify(String notifyData);
    //微信退款
    RefundResponse refund(OrderMaster orderMaster);
}

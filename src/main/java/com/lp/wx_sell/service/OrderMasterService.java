package com.lp.wx_sell.service;

import com.lp.wx_sell.common.ResultResponse;
import com.lp.wx_sell.dto.OrderMasterDto;

/**
 * @author Liupeng
 * @date 2019/4/17 15:01
 */
public interface OrderMasterService {
    ResultResponse insertOrder(OrderMasterDto orderMasterDto);

    ResultResponse findAllByPage(String openid,Integer page,Integer size);

    //查看订单详情
    ResultResponse findDetail(String openId,String orderId);

    //取消订单
    ResultResponse cancel(String openId,String orderId);
}

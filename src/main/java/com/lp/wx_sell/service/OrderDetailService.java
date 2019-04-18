package com.lp.wx_sell.service;

import com.lp.wx_sell.entity.OrderDetail;

import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/17 14:56
 */
public interface OrderDetailService {
    //批量插入
    void batchInsert(List<OrderDetail> orderDetailList);

    List<OrderDetail> findAllByOrderId(String orderid);
}

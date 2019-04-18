package com.lp.wx_sell.repository;

import com.lp.wx_sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/17 14:04
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findAllByOrderId(String orderid);
}

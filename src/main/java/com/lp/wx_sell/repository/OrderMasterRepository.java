package com.lp.wx_sell.repository;

import com.lp.wx_sell.entity.OrderMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/17 14:06
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    List<OrderMaster> findAllByBuyerOpenid(Pageable pageable, String openid);


    OrderMaster findAllByBuyerOpenidAndOrderId(String openid,String orderid);
}

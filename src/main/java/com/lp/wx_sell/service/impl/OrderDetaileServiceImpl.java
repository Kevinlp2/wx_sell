package com.lp.wx_sell.service.impl;

import com.lp.wx_sell.dao.BatchDao;
import com.lp.wx_sell.dao.impl.BatchDaoImpl;
import com.lp.wx_sell.entity.OrderDetail;
import com.lp.wx_sell.repository.OrderDetailRepository;
import com.lp.wx_sell.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/17 14:57
 */
@Service
public class OrderDetaileServiceImpl extends BatchDaoImpl<OrderDetail> implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    @Transactional
    public void batchInsert(List<OrderDetail> orderDetailList) {
       super.batchInsert( orderDetailList );
    }

    @Override
    public List<OrderDetail> findAllByOrderId(String orderid) {
        return orderDetailRepository.findAllByOrderId( orderid );
    }
}

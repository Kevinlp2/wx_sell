package com.lp.wx_sell.dto;

import com.lp.wx_sell.entity.OrderDetail;
import com.lp.wx_sell.entity.OrderMaster;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/18 10:31
 */
@Data
public class OrderMasterDetaDto extends OrderMaster {
    List<OrderDetail> orderDetailList;

    public static OrderMasterDetaDto build(OrderMaster orderMaster){
        OrderMasterDetaDto orderMasterDetaDto = new OrderMasterDetaDto();
        BeanUtils.copyProperties( orderMaster,orderMasterDetaDto );
        return orderMasterDetaDto;
    }
}

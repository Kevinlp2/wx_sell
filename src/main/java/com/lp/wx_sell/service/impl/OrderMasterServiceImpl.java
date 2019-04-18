package com.lp.wx_sell.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lp.wx_sell.common.*;
import com.lp.wx_sell.dto.OrderDetailDto;
import com.lp.wx_sell.dto.OrderMasterDetaDto;
import com.lp.wx_sell.dto.OrderMasterDto;
import com.lp.wx_sell.entity.OrderDetail;
import com.lp.wx_sell.entity.OrderMaster;
import com.lp.wx_sell.entity.ProductInfo;
import com.lp.wx_sell.exception.CustomException;
import com.lp.wx_sell.repository.OrderMasterRepository;
import com.lp.wx_sell.service.OrderDetailService;
import com.lp.wx_sell.service.OrderMasterService;
import com.lp.wx_sell.service.ProductInfoService;
import com.lp.wx_sell.util.BigDecimalUtil;
import com.lp.wx_sell.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Liupeng
 * @date 2019/4/17 15:02
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public ResultResponse insertOrder(OrderMasterDto orderMasterDto) {
        //获取订单项
        List<OrderDetailDto> items = orderMasterDto.getItem();

        List<OrderDetail> orderDetailList = Lists.newArrayList();
        //总价格
        BigDecimal totalDecima = new BigDecimal( "0" );

        for (OrderDetailDto item:items) {
            //根据商品id获得商品信息
            ResultResponse<ProductInfo> infoResultResponse = productInfoService.queryById( item.getProductId() );
            ProductInfo productInfo = infoResultResponse.getData();

            //如果商品不存在
            if(infoResultResponse.getCode() == ResultEnums.NOT_EXITS.getCode()){
                return ResultResponse.fail( ResultEnums.NOT_EXITS.getMsg()+":"+ productInfo.getProductName());
            }

            //判断库存
            if(productInfo.getProductStock()<item.getProductQuantity()){
                return ResultResponse.fail( ResultEnums.PRODUCT_NOT_ENOUGH.getMsg()+":"+productInfo.getProductName() );
            }

            OrderDetail orderDetail = OrderDetail.builder().detailId( IDUtil.createIdbyUUID() ).createTime( new Date() ).productIcon( productInfo.getProductIcon() )
                    .productId( productInfo.getProductId() ).productName( productInfo.getProductName() ).productPrice( productInfo.getProductPrice() )
                    .productQuantity( item.getProductQuantity() ).build();
            orderDetailList.add( orderDetail );

            //减少库存
            productInfo.setProductStock( productInfo.getProductStock()-item.getProductQuantity() );

            productInfoService.updateProduct( productInfo );

            totalDecima= BigDecimalUtil.add( totalDecima,BigDecimalUtil.multi( productInfo.getProductPrice(),item.getProductQuantity()) );

        }
        //生成订单id
        String orderId = IDUtil.createIdbyUUID();

        OrderMaster orderMaster = OrderMaster.builder().orderId( orderId ).orderAmount( totalDecima ).buyerAddress( orderMasterDto.getAddress() )
                .buyerName( orderMasterDto.getName() ).buyerOpenid( orderMasterDto.getOpenid() ).buyerPhone( orderMasterDto.getPhone())
                .createTime( new Date() ).orderStatus( OrderEnums.NEW.getCode() ).payStatus( PayEnums.WAIT.getCode() ).build();
        //将生成的orderid添加到订单项中
        List<OrderDetail> newOrderDetailList = orderDetailList.stream().map( orderDetail -> {orderDetail.setOrderId( orderId );
        return  orderDetail;
        }).collect( Collectors.toList() );

        orderDetailService.batchInsert( newOrderDetailList );

        orderMasterRepository.save( orderMaster );

        HashMap<String, String> map = Maps.newHashMap();
        map.put( "roderId",orderId );

        return ResultResponse.success(map);
    }

    @Override
    public ResultResponse findAllByPage(String openid,Integer page,Integer size) {
        //判断参数是否异常
        if(StringUtils.isBlank( openid )){
            return ResultResponse.fail( OrderEnums.OPENID_ERROR.getMsg() );
        }


        PageRequest pageRequest=PageRequest.of( page,size );
        List<OrderMaster> orderMasterList = orderMasterRepository.findAllByBuyerOpenid(pageRequest,openid);
        return ResultResponse.success(orderMasterList);
    }

    @Override
    public ResultResponse findDetail(String openId, String orderId) {
        //判断参数是否异常
        if(StringUtils.isBlank( openId )){
            return ResultResponse.fail( OrderEnums.OPENID_ERROR.getMsg() );
        }
        if(StringUtils.isBlank( orderId )){
            return ResultResponse.fail( ResultEnums.PARAM_ERROR.getMsg()+":"+orderId);
        }

        Optional<OrderMaster> byId = orderMasterRepository.findById( orderId );
        OrderMaster orderMaster1 = byId.get();
        //订单不存在
        if(!byId.isPresent()){
            return ResultResponse.fail( OrderEnums.OPDER_NOT_EXITS.getMsg());
        }


        OrderMaster orderMaster = orderMasterRepository.findAllByBuyerOpenidAndOrderId( openId ,orderId);
        if(orderMaster==null){
            return ResultResponse.fail( OrderEnums.OPDER_NOT_EXITS.getMsg());
        }
        OrderMasterDetaDto orderMasterDetaDto = OrderMasterDetaDto.build( orderMaster );

        List<OrderDetail> allByOrderId = orderDetailService.findAllByOrderId( orderId );

        orderMasterDetaDto.setOrderDetailList( allByOrderId );



        return ResultResponse.success( orderMasterDetaDto);
    }

    @Override
    public ResultResponse cancel(String openId, String orderId) {
        //判断参数是否异常
        if(StringUtils.isBlank( openId )){
            return ResultResponse.fail( OrderEnums.OPDER_NOT_EXITS.getMsg());
        }
        if(StringUtils.isBlank( orderId )){
            return ResultResponse.fail( ResultEnums.PARAM_ERROR.getMsg()+":"+orderId);
        }

        Optional<OrderMaster> byId = orderMasterRepository.findById( orderId );
        OrderMaster orderMaster1 = byId.get();
        //订单不存在
        if(!byId.isPresent()){
            return ResultResponse.fail( OrderEnums.OPDER_NOT_EXITS.getMsg());
        }

        OrderMaster orderMaster = orderMasterRepository.findAllByBuyerOpenidAndOrderId( openId ,orderId);
        if(orderMaster==null){
            return ResultResponse.fail( OrderEnums.OPDER_NOT_EXITS.getMsg());
        }
        orderMaster.setOrderStatus( OrderEnums.CANCEL.getCode() );
        orderMaster.setUpdateTime( new Date() );

        orderMasterRepository.save( orderMaster );

        return ResultResponse.success();
    }
}

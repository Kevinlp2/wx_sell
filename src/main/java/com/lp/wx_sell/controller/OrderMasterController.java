package com.lp.wx_sell.controller;

import com.google.common.collect.Maps;
import com.lp.wx_sell.common.ResultResponse;
import com.lp.wx_sell.dto.OrderMasterDto;
import com.lp.wx_sell.service.OrderMasterService;
import com.lp.wx_sell.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liupeng
 * @date 2019/4/17 15:58
 */
@RestController
@RequestMapping("/buyer/order")
@Api(value = "订单相关接口",description = "完成订单的增删改查")
public class OrderMasterController {
    @Autowired
    private OrderMasterService orderMasterService;

    @PostMapping("/create")
    @ApiOperation( value = "创建订单接口",httpMethod = "POST",response = ResultResponse.class)
    public ResultResponse create(
            @Valid @ApiParam(name = "订单对象",value = "传入json格式",required = true)
                    OrderMasterDto orderMasterDto, BindingResult bindingResult){
        Map<String,String> map = Maps.newHashMap();
        if(bindingResult.hasErrors()){
            List<String> errList = bindingResult.getFieldErrors().stream().map( err -> err.getDefaultMessage() ).collect( Collectors.toList() );
            map.put( "参数校验错误", JsonUtil.object2string( errList ) );

            return ResultResponse.fail( map );
        }
        return orderMasterService.insertOrder( orderMasterDto );
    }

    @GetMapping("/list")
    @ApiOperation( value = "订单列表",httpMethod = "GET",response = ResultResponse.class)
    public ResultResponse list(@RequestParam("openid") String openid, @RequestParam("page")Integer page, @RequestParam("size") Integer size){

        return orderMasterService.findAllByPage( openid,page,size);
    }

    @GetMapping("/detail")
    @ApiOperation( value = "订单详情",httpMethod = "GET",response = ResultResponse.class)
    public ResultResponse detail(@RequestParam("openid") String openid, @RequestParam("orderid")String orderid){

        return orderMasterService.findDetail( openid,orderid );
    }

    @GetMapping("/cancel")
    @ApiOperation( value = "取消订单",httpMethod = "GET",response = ResultResponse.class)
    public ResultResponse cancel(@RequestParam("openid") String openid, @RequestParam("orderid")String orderid){

        return orderMasterService.cancel( openid,orderid );
    }
}

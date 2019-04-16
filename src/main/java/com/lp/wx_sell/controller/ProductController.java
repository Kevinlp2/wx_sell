package com.lp.wx_sell.controller;

import com.lp.wx_sell.common.ResultResponse;
import com.lp.wx_sell.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liupeng
 * @date 2019/4/16 15:56
 */
@RestController
@RequestMapping("buyer/product")
@Api(description = "商品信息接口") //使用swagger2的注解对类描述
public class ProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @GetMapping("list")
    @ApiOperation( value = "查询商品列表")
    public ResultResponse getlist(){
        return productInfoService.queryList();
    }
}

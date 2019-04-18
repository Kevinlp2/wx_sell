package com.lp.wx_sell.service;

import com.lp.wx_sell.common.ResultResponse;
import com.lp.wx_sell.entity.ProductInfo;

/**
 * @author Liupeng
 * @date 2019/4/16 16:22
 */
public interface ProductInfoService {
    ResultResponse queryList();
    //根据id查询商品
    ResultResponse<ProductInfo> queryById(String productId);

    //修改商品库存
    void updateProduct(ProductInfo productInfo);
}

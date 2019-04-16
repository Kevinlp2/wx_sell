package com.lp.wx_sell.service;

import com.lp.wx_sell.common.ResultResponse;
import com.lp.wx_sell.dto.ProductCategoryDto;

import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/16 16:13
 */
public interface ProductCategoryService {
    List<ProductCategoryDto> findAll();
}

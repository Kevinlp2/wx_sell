package com.lp.wx_sell.service.impl;

import com.lp.wx_sell.common.ResultResponse;
import com.lp.wx_sell.dto.ProductCategoryDto;
import com.lp.wx_sell.entity.ProductCategory;
import com.lp.wx_sell.repository.ProductCategoryRepository;
import com.lp.wx_sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liupeng
 * @date 2019/4/16 16:15
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategoryDto> findAll() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();

        List<ProductCategoryDto> productCategoryDtos
                = productCategoryList.stream().map( productCategory -> ProductCategoryDto.build( productCategory ) ).collect( Collectors.toList() );
        return  productCategoryDtos ;
    }
}

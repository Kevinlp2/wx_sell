package com.lp.wx_sell.service.impl;

import com.lp.wx_sell.common.ResultEnums;
import com.lp.wx_sell.common.ResultResponse;
import com.lp.wx_sell.dto.ProductCategoryDto;
import com.lp.wx_sell.dto.ProductInfoDto;
import com.lp.wx_sell.entity.ProductInfo;
import com.lp.wx_sell.repository.ProductInfoRepository;
import com.lp.wx_sell.service.ProductCategoryService;
import com.lp.wx_sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liupeng
 * @date 2019/4/16 16:23
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ResultResponse queryList() {
        List<ProductCategoryDto> productCategoryDtos = productCategoryService.findAll();
        if(CollectionUtils.isEmpty( productCategoryDtos )){
                return ResultResponse.fail();
        }
        //商品类别集合
        List<Integer> integerList = productCategoryDtos.stream().map( productCategoryDto -> productCategoryDto.getCategoryType() ).collect( Collectors.toList() );

        //根据商品类别集合查找商品
        List<ProductInfo> productInfos = productInfoRepository.findAllByProductStatusAndCategoryTypeIn( ResultEnums.PRODUCT_UP.getCode(), integerList );

        //多线程遍历 取出每个商品类目编号对应的 商品列表 设置进入类目中
        List<ProductCategoryDto> finalRseultList = productCategoryDtos.parallelStream().map( productCategoryDto -> {
            productCategoryDto.setProductInfoDtoList( productInfos.stream().
                    filter( productInfo -> productInfo.getCategoryType().equals(productCategoryDto.getCategoryType()) ).
                    map( productInfo -> ProductInfoDto.buid( productInfo ) ).collect( Collectors.toList() ) );
            return productCategoryDto;
        } ).collect( Collectors.toList() );
//        List<ProductCategorydto> finalResultList = categorydtoList.parallelStream().map(categorydto -> {
//            categorydto.setProductInfodtoList(productInfoList.stream().
//                    filter(productInfo -> productInfo.getCategoryType() == categorydto.getCategoryType()).map(productInfo ->
//                    ProductInfodto.build(productInfo)).collect(Collectors.toList()));
//            return categorydto;
//        }).collect(Collectors.toList());


        return ResultResponse.success(  finalRseultList);
    }
}

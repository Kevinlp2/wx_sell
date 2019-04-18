package com.lp.wx_sell.service.impl;

import com.lp.wx_sell.common.ResultEnums;
import com.lp.wx_sell.common.ResultResponse;
import com.lp.wx_sell.dto.ProductCategoryDto;
import com.lp.wx_sell.dto.ProductInfoDto;
import com.lp.wx_sell.entity.ProductInfo;
import com.lp.wx_sell.exception.CustomException;
import com.lp.wx_sell.repository.ProductInfoRepository;
import com.lp.wx_sell.service.ProductCategoryService;
import com.lp.wx_sell.service.ProductInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
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
        return ResultResponse.success(  finalRseultList);
    }

    @Override
    public ResultResponse<ProductInfo> queryById(String productId) {
        //判断参数是否异常
        if(StringUtils.isBlank( productId )){
            return ResultResponse.fail( ResultEnums.PARAM_ERROR.getMsg()+":"+productId );
        }
        Optional<ProductInfo> byId = productInfoRepository.findById( productId );
        if(!byId.isPresent()){
            throw new CustomException(ResultEnums.NOT_EXITS.getMsg() );
        }
        ProductInfo productInfo = byId.get();
        //判断商品是否下架
        if(productInfo.getProductStatus() == ResultEnums.PRODUCT_DOWN.getCode()){
            return ResultResponse.fail( ResultEnums.PRODUCT_DOWN.getMsg() );
        }
        return ResultResponse.success(productInfo);
    }

    @Override
    public void updateProduct(ProductInfo productInfo) {
        productInfoRepository.save( productInfo );
    }
}

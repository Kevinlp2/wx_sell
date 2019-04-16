package com.lp.wx_sell.repository;

import com.lp.wx_sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/16 15:27
 */

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findAllByProductStatusAndCategoryTypeIn(Integer status,List<Integer> categoryList);
}

package com.lp.wx_sell.repository;

import com.lp.wx_sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Liupeng
 * @date 2019/4/16 14:58
 */

//第一个参数表示实体类名称，第二个参数表示主键类型
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
}

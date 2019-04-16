package com.lp.wx_sell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lp.wx_sell.entity.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/16 15:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDto implements Serializable {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoDto> productInfoDtoList;

    public static ProductCategoryDto build(ProductCategory productCategory){
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        BeanUtils.copyProperties( productCategory,productCategoryDto );
        return productCategoryDto;
    }
}

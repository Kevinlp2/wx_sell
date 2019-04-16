package com.lp.wx_sell.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lp.wx_sell.entity.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Liupeng
 * @date 2019/4/16 15:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDto implements Serializable {
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;

    public static ProductInfoDto buid(ProductInfo productInfo){
        ProductInfoDto productInfoDto = new ProductInfoDto();
        BeanUtils.copyProperties( productInfo,productInfoDto );
        return productInfoDto;
    }

}

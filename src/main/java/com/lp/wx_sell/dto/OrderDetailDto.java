package com.lp.wx_sell.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Liupeng
 * @date 2019/4/17 14:10
 */
@Data
@ApiModel("订单项参数实体类")
public class OrderDetailDto  implements Serializable {
    @NotBlank(message = "商品id不能为空")
    @ApiModelProperty(value = "商品id",dataType = "String")
    private String productId;
    @NotNull(message = "商品数量不能为空")
    @Min( value = 1,message = "商品数量不能小于一件")
    @ApiModelProperty(value = "商品数量",dataType = "Integer")
    private Integer productQuantity;
}

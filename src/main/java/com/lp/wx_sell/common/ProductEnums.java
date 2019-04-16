package com.lp.wx_sell.common;

import lombok.Getter;

/**
 * @author Liupeng
 * @date 2019/4/16 15:48
 */
@Getter
public enum ProductEnums {
    PRODUCT_NOT_ENOUGH(1,"商品库存不足");

    private int code;
    private String msg;
    ProductEnums(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
}

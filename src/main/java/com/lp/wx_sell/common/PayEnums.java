package com.lp.wx_sell.common;

import lombok.Getter;

/**
 * @author Liupeng
 * @date 2019/4/16 15:42
 */
@Getter
public enum PayEnums {
    WAIT(0,"等待支付"),
    FINISH(1,"支付完成"),
    FAIL(2,"支付失败"),
    STATUS_ERROR(3,"订单状态异常");

    private int code;
    private String msg;
    PayEnums(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
}

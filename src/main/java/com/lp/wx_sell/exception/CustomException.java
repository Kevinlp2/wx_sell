package com.lp.wx_sell.exception;

import com.lp.wx_sell.common.ResultEnums;
import com.lp.wx_sell.common.ResultResponse;

/**
 * @author Liupeng
 * @date 2019/4/17 14:29
 */
public class CustomException extends RuntimeException {
    private int code;
    public CustomException() {
        super();
    }

    public CustomException(int code,String message) {
        super( message );
        this.code=code;
    }

    public CustomException(String message) {
        this(ResultEnums.FAIL.getCode(),message);
    }
}

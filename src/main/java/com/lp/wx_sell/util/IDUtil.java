package com.lp.wx_sell.util;

import java.util.UUID;

/**
 * @author Liupeng
 * @date 2019/4/17 14:26
 */

public class IDUtil {
    public static String createIdbyUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

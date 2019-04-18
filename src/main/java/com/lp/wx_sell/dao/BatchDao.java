package com.lp.wx_sell.dao;

import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/17 14:32
 */
public interface BatchDao<T> {
    void batchInsert(List<T> list);
}

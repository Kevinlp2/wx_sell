package com.lp.wx_sell.dao.impl;

import com.lp.wx_sell.dao.BatchDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/17 14:33
 */
public class BatchDaoImpl<T> implements BatchDao<T> {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void batchInsert(List<T> list) {
        int length = list.size();
        for (int i = 0; i <length ; i++) {
            em.persist( list.get( i ) );
            if(i%100 ==0 || i==length-1){
                em.flush();
                em.clear();
            }
        }
    }
}

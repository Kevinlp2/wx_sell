package com.lp.wx_sell;

import com.lp.wx_sell.entity.ProductCategory;
import com.lp.wx_sell.entity.ProductInfo;
import com.lp.wx_sell.repository.ProductCategoryRepository;
import com.lp.wx_sell.repository.ProductInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxSellApplicationTests {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Test
    public void contextLoads() {
//        List<ProductCategory> all = productCategoryRepository.findAll();
        List<ProductInfo> all = productInfoRepository.findAll();
        all.stream().forEach( System.out::println );
    }

}

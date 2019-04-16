package com.lp.wx_sell.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Liupeng
 * @date 2019/4/16 14:52
 */
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category")
@Entity
public class ProductCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//表示自增
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
}

package com.lp.wx_sell.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Liupeng
 * @date 2019/4/18 9:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PageBean<T> {
    // 当前页
    private Integer pageNum = 1;
    // 每页显示的总条数
    private Integer pageSize  ;
    // 总条数
    private Integer count;

    // 总页数
    private Integer totalPage;

    // 分页结果
    private List<T> items;
}

package com.steambuy.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandQueryByPageParameter {
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页大小
     */
    private Integer rows;
    /**
     * 排序字段
     */
    private String sortBy;
    /**
     * 是否降序
     */
    private Boolean desc;
    /**
     * 搜索关键词
     */
    private String key;
}


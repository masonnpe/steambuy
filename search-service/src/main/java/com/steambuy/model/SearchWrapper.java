package com.steambuy.model;

import com.steambuy.common.model.PageWrapper;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class SearchWrapper extends PageWrapper<Item> {

    /**
     * 分类的集合
     */
    private List<Category> categories;

    /**
     * 品牌的集合
     */
    private List<Brand> brands;

    /**
     * 规格参数的过滤条件
     */
    private List<Map<String,Object>> specs;

}

package com.steambuy.service;

import com.steambuy.mapper.CategoryMapper;
import com.steambuy.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoryByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> list = categoryMapper.selectByIdList(ids);
        return list.stream().map(Category::getName).collect(Collectors.toList());
    }
}

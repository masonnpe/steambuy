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
        Category t = new Category();
        t.setParentId(pid);
        return this.categoryMapper.select(t);
    }

    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> list = this.categoryMapper.selectByIdList(ids);
//        List<String> names = new ArrayList<>();
//        for (Category category : list) {
//            names.add(category.getName());
//        }
//        return names;
        return list.stream().map(category -> category.getName()).collect(Collectors.toList());
    }
}

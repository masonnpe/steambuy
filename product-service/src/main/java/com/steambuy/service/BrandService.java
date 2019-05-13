package com.steambuy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.steambuy.common.model.PageResult;
import com.steambuy.mapper.BrandMapper;
import com.steambuy.model.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BrandService {

    @Resource
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 分页
        PageHelper.startPage(page, rows);

        Example example = new Example(Brand.class);
        // 排序
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + (desc ? " DESC" : " ASC"));
        }
        // 查询
        if(StringUtils.isNotBlank(key)) {
            example.createCriteria().orLike("name", key)
                    .orEqualTo("letter", key.toUpperCase());
        }
        List<Brand> list = brandMapper.selectByExample(example);
        // 创建PageInfo
        PageInfo<Brand> info = new PageInfo<>(list);
        // 返回分页结果

       // PageInfo<Object> objectPageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(() -> brandMapper.selectAll());


        return new PageResult<>(info.getTotal(), info.getList());
    }

    @Transactional
    public void save(Brand brand, List<Long> ids) {
        // 新增品牌
        brandMapper.insert(brand);
        // 新增品牌和分类中间表
        for (Long cid : ids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }
}

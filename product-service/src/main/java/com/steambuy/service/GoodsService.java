package com.steambuy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.steambuy.common.model.PageResult;
import com.steambuy.mapper.BrandMapper;
import com.steambuy.mapper.SpuMapper;
import com.steambuy.model.Brand;
import com.steambuy.model.Spu;
import com.steambuy.model.SpuBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsService {

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private CategoryService categoryService;

    @Resource
    private BrandMapper brandMapper;

    public PageResult<SpuBo> querySpuPage(
            Integer page, Integer rows, String sortBy, Boolean desc, Boolean saleable, String key) {

        // 分页
        PageHelper.startPage(page, rows);

        Example example = new Example(Spu.class);
        // 排序
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + (desc ? " DESC" : " ASC"));
        }
        // 查询
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("title", "%" + key + "%");
        }
        // 是否上下架
        if (saleable != null) {
            example.createCriteria().andEqualTo("saleable", saleable);
        }

        List<Spu> list = this.spuMapper.selectByExample(example);
        // 创建PageInfo
        PageInfo<Spu> info = new PageInfo<>(list);

        List<SpuBo> spus = new ArrayList<>();
        // 给每个spu查询分类名称和品牌名称
        for (Spu spu : info.getList()) {
            SpuBo spuBo = new SpuBo();
            // 普通属性
            spuBo.setId(spu.getId());
            spuBo.setTitle(spu.getTitle());
            // 分类名称
            List<String> names = this.categoryService.queryNamesByIds(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(names, "/"));
            // 品牌名称
            Brand brand = this.brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuBo.setBname(brand.getName());

            spus.add(spuBo);
        }
        // 返回分页结果
        return new PageResult<>(info.getTotal(), spus);
    }
}

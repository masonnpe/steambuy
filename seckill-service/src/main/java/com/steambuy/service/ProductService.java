package com.steambuy.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.steambuy.mapper.ProductMapper;
import com.steambuy.model.SeckillProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    public PageInfo<SeckillProduct>  selectByPageNum(int pageNum,int pageSize){
        return PageHelper.startPage(pageNum, pageSize).setOrderBy("id desc").doSelectPageInfo(()-> this.productMapper.selectAll());
    }
}

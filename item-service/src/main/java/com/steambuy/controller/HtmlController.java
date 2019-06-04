package com.steambuy.controller;

import com.steambuy.bo.SpuBo;
import com.steambuy.service.BrandService;
import com.steambuy.service.CategoryService;
import com.steambuy.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class HtmlController {

    @Resource
    CategoryService categoryService;

    @Resource
    BrandService brandService;

    @Resource
    GoodsService goodsService;

    @RequestMapping("{name}.html")
    public String toHtml(@PathVariable String name){
        return name;
    }

    @RequestMapping(value = "item/{id}.html",produces = "text/html")
    public String itemDetailPage(@PathVariable("id") Long spuid){
        SpuBo spuBo = goodsService.queryGoodsById(spuid);


        return "item";
    }
}

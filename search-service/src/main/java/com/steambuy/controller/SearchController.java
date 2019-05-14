package com.steambuy.controller;

import com.steambuy.bo.SpuBo;
import com.steambuy.common.model.PageResult;
import com.steambuy.feign.ItemFeignClient;
import com.steambuy.model.Item;
import com.steambuy.model.SearchRequest;
import com.steambuy.model.SearchResult;
import com.steambuy.repository.ItemRepository;
import com.steambuy.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class SearchController implements InitializingBean {

    @Autowired
    SearchService searchService;
    @Autowired
    ElasticsearchTemplate template;
    @Resource
    ItemFeignClient itemFeignClient;
    @Resource
    ItemRepository itemRepository;

    @PostMapping("page")
    public ResponseEntity<PageResult<Item>> search(@RequestBody SearchRequest searchRequest){
        SearchResult<Item> result = this.searchService.search(searchRequest);
        if (result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(result);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(!template.createIndex(Item.class)){
            log.info("初始化创建Item索引失败");
        }
        if(!template.putMapping(Item.class)){
            log.info("初始化创建Item映射失败");
        }

        //加载数据
        List<SpuBo> list = new ArrayList<>();
        int page = 1;
        int row = 100;
        int size;
        do {
            //分页查询数据
            PageResult<SpuBo> result = this.itemFeignClient.querySpuByPage(page, row, null, true, null, true);
            List<SpuBo> spus = result.getItems();
            size = spus.size();
            page ++;
            list.addAll(spus);
        }while (size == 100);

        //创建Goods集合
        List<Item> goodsList = new ArrayList<>();
        //遍历spu
        for (SpuBo spu : list) {
            try {
                Item goods = this.searchService.buildItem(spu);
                goodsList.add(goods);
            } catch (IOException e) {
                System.out.println("查询失败：" + spu.getId());
            }
        }
        this.itemRepository.saveAll(goodsList);
    }
}

package com.steambuy.controller;

import com.steambuy.model.Item;
import com.steambuy.model.SearchRequest;
import com.steambuy.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SearchController implements InitializingBean {

    @Autowired
    SearchService searchService;
    @Autowired
    ElasticsearchTemplate template;

    @PostMapping("/page")
    public String search(@RequestBody SearchRequest searchRequest){
        searchService.searchByRequest(searchRequest);
        return "";
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if(!template.createIndex(Item.class)){
            log.info("初始化创建Item索引失败");
        }
        if(!template.putMapping(Item.class)){
            log.info("初始化创建Item映射失败");
        }

    }
}

package com.steambuy.service;

import com.steambuy.feign.ProductFeignClient;
import com.steambuy.model.Item;
import com.steambuy.model.SearchRequest;
import com.steambuy.model.Spu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    ElasticsearchTemplate template;
    @Autowired
    ProductFeignClient productFeignClient;

    public Item convertToItem(Spu spu){
        return new Item();
    }

    public void searchByRequest(SearchRequest searchRequest) {

    }

    public void createIndex(Long id) {

    }

    public void deleteIndex(Long id) {

    }
}

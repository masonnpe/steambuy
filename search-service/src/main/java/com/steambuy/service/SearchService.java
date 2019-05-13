package com.steambuy.service;

import com.steambuy.feign.CategoryFeignClient;
import com.steambuy.feign.ProductFeignClient;
import com.steambuy.model.Category;
import com.steambuy.model.Item;
import com.steambuy.model.SearchRequest;
import com.steambuy.model.Spu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchService {
    @Resource
    ElasticsearchTemplate template;
    @Resource
    CategoryFeignClient categoryFeignClient;

    public Item convertToItem(Spu spu){
        List<Long> cids = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<Category> categoryList = categoryFeignClient.queryCategoryByIds(cids).getBody();


        return new Item();
    }

    public void searchByRequest(SearchRequest searchRequest) {

    }

    public void createIndex(Long id) {

    }

    public void deleteIndex(Long id) {

    }
}

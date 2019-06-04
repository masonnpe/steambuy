package com.steambuy.repository;

import com.steambuy.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    List<Item> findByPriceBetween(double price1, double price2);
}

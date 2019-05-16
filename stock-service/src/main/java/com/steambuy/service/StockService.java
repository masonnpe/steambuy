package com.steambuy.service;

import com.steambuy.constants.RedisKeyConstants;
import com.steambuy.mapper.StockMapper;
import com.steambuy.model.Stock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StockService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private StockMapper stockMapper;

    public void updateStock(Stock stock){
        stockMapper.updateByPrimaryKey(stock);
    }

    public void removeRedisCache(Long id){
        redisTemplate.delete(RedisKeyConstants.ITEM_STOCK_KEY+id);
    }

    public Stock fingStockBySkuid(Long id){
        return stockMapper.selectByPrimaryKey(id);
    }

    public void setStockCache(Stock stock){
        redisTemplate.opsForValue().set(RedisKeyConstants.ITEM_STOCK_KEY+stock.getSkuId(),String.valueOf(stock.getStock()));
    }

    public Stock getStockCache(Long id){
        String s = redisTemplate.opsForValue().get(RedisKeyConstants.ITEM_STOCK_KEY + id);
        if(StringUtils.isNotBlank(s)){
            Long stocknum = Long.valueOf(s);
            return new Stock(id,stocknum);
        }
        return null;
    }
}

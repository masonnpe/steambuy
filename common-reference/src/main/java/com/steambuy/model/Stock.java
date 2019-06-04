package com.steambuy.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Table(name = "tb_stock")
public class Stock {

    @Id
    private Long skuId;
    /**
     * 秒杀可用库存
     */
    private Integer seckillStock;
    /**
     * 已秒杀数量
     */
    private Integer seckillTotal;
    /**
     * 正常库存
     */
    private Long stock;

    public Stock(Long skuId, Long stock) {
        this.skuId = skuId;
        this.stock = stock;
    }

    public Stock() {
    }
}
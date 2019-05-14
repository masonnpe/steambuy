package com.steambuy.bo;

import com.steambuy.model.Sku;
import com.steambuy.model.Spu;
import com.steambuy.model.SpuDetail;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
public class SpuBo extends Spu {
    /**
     * 商品分类名称
     */
    @Transient
    private String cname;
    /**
     * 品牌名称
     */
    @Transient
    private String bname;

    /**
     * 商品详情
     */
    @Transient
    private SpuDetail spuDetail;

    /**
     * sku列表
     */
    @Transient
    private List<Sku> skus;

    public SpuBo(Long brandId, Long cid1, Long cid2, Long cid3, String title, String subTitle, Boolean saleable, Boolean valid, Date createTime, Date lastUpdateTime) {
        super(brandId, cid1, cid2, cid3, title, subTitle, saleable, valid, createTime, lastUpdateTime);
    }

    public SpuBo() {
        super();
    }
}
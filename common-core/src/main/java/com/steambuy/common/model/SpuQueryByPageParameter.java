package com.steambuy.common.model;

import lombok.Data;

@Data
public class SpuQueryByPageParameter extends BrandQueryByPageParameter{
    /**
     * 是否上架
     */
    private Boolean saleable;

    public SpuQueryByPageParameter(Integer page, Integer rows, String sortBy, Boolean desc, String key, Boolean saleable) {
        super(page, rows, sortBy, desc, key);
        this.saleable = saleable;
    }

    public SpuQueryByPageParameter(Boolean saleable) {
        this.saleable = saleable;
    }
}


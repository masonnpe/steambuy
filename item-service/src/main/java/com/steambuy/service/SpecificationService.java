package com.steambuy.service;

import com.steambuy.mapper.SpecificationMapper;
import com.steambuy.model.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 98050
 * Time: 2018-08-14 15:26
 * Feature:
 */
@Service
public class SpecificationService {
    @Resource
    private SpecificationMapper specificationMapper;

    public Specification queryById(Long id) {
        return this.specificationMapper.selectByPrimaryKey(id);
    }

    public void saveSpecification(Specification specification) {
        this.specificationMapper.insert(specification);
    }

    public void updateSpecification(Specification specification) {
        this.specificationMapper.updateByPrimaryKeySelective(specification);
    }

    public void deleteSpecification(Specification specification) {
        this.specificationMapper.deleteByPrimaryKey(specification);
    }
}

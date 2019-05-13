package com.steambuy.service;

import com.steambuy.mapper.SpecificationMapper;
import com.steambuy.model.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpecificationService {

    @Resource
    private SpecificationMapper specificationMapper;

    public String querySpecById(Long id) {
        Specification specification = specificationMapper.selectByPrimaryKey(id);
        if(specification == null){
            return null;
        }
        return specification.getSpecifications();
    }
}

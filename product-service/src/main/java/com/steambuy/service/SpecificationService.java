package com.steambuy.service;

import com.steambuy.mapper.SpecificationMapper;
import com.steambuy.model.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: HuYi.Zhang
 * @create: 2018-06-01 10:39
 **/
@Service
public class SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    public String querySpecById(Long id) {
        Specification specification = this.specificationMapper.selectByPrimaryKey(id);
        if(specification == null){
            return null;
        }
        return specification.getSpecifications();
    }
}

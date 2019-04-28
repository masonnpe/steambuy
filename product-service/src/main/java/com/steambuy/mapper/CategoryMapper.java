package com.steambuy.mapper;

import com.steambuy.model.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: HuYi.Zhang
 * @create: 2018-05-28 09:59
 **/
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {
}

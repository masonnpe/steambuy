package com.steambuy.mapper;

import com.steambuy.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    User queryUser(@Param("username") String username, @Param("password") String password);
}

package com.steambuy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Length(min = 4,max = 15,message = "用户名只能在4~15位之间")
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    @Length(min = 6,max = 25,message = "密码只能在6~25位之间")
    private String password;

    /**
     * 电话
     */
    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号格式不正确")
    private String phone;

    /**
     * 创建时间
     */
    private Date created;

    private String email;
}

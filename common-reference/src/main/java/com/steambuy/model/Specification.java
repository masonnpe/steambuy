package com.steambuy.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 规格参数
 */
@Data
@Table(name = "tb_specification")
public class Specification {

    @Id
    private Long categoryId;
    private String specifications;
}
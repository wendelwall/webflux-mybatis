package com.wendel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "t_code_dict")
@Data
public class CodeDict {
    /**
     * 合作伙伴名称
     */
    @Column(name = "CATEGORY")
    @JsonIgnore
    private String category;

    /**
     * 编码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 排序
     */
    @JsonIgnore
    @Column(name = "SEQUENCE")
    private Integer sequence;

    /**
     * 父节点id
     */
    @JsonIgnore
    @Transient
    private String parentId;
}
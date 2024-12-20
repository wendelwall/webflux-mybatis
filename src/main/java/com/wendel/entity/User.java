package com.wendel.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "t_user")
@Data
public class User extends BaseEntity {
    /**
     * 用户名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 职位/称谓
     */
    @Column(name = "POSITION")
    private String position;

    /**
     * 手机号
     */
    @Column(name = "MOBILE")
    private String mobile;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 姓名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 状态（1-启用，0-禁用）
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 性别
     */
    @Column(name = "GENDER")
    private String gender;

    /**
     * 证件类型
     */
    @Column(name = "CARD_TYPE")
    private String cardType;

    /**
     * 证件号
     */
    @Column(name = "CARD_ID")
    private String cardId;

    /**
     * 生日
     */
    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;


}
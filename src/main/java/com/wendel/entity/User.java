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

    /**
     * 省份
     */
    @Column(name = "PROVINCE")
    private String province;

    /**
     * 省份名称
     */
    @Column(name = "PROVINCE_NAME")
    private String provinceName;

    /**
     * 市
     */
    @Column(name = "CITY")
    private String city;

    /**
     * 市名称
     */
    @Column(name = "CITY_NAME")
    private String cityName;

    /**
     * 行政区
     */
    @Column(name = "DISTRICT")
    private String district;

    /**
     * 行政区名称
     */
    @Column(name = "DISTRICT_NAME")
    private String districtName;

    /**
     * 详细地址
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 扩展字段1
     */
    @Column(name = "EXT_1")
    private String ext1;

    /**
     * 扩展字段2
     */
    @Column(name = "EXT_2")
    private String ext2;

    /**
     * 扩展字段3
     */
    @Column(name = "EXT_3")
    private String ext3;

    /**
     * 扩展字段4
     */
    @Column(name = "EXT_4")
    private String ext4;

    /**
     * 扩展字段5
     */
    @Column(name = "EXT_5")
    private String ext5;

    /**
     * 部门
     */
    @Column(name = "DEPARTMENT")
    private String department;
    /**
     * 是否推广人员：0是false，1是true
     */
    @Column(name = "IS_MARKETING")
    private String isMarketing;
    /**
     * 推广员代码
     */
    @Column(name = "MARKETING_CODE")
    private String marketingCode;
    /**
     * 批次号
     */
    @Column(name = "BATCH_ID")
    private String batchId;

}
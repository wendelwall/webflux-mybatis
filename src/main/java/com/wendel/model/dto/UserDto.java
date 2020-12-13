package com.wendel.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDto {

    /**
     * 记录编号
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 职位/称谓
     */
    private String position;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 状态
     */
    private String status;

    /**
     * 性别
     */
    private String gender;

    /**
     * 证件类型
     */
    private String cardType;

    /**
     * 证件号
     */
    private String cardId;

     /**
      * 生日
      */
     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate birthday;

     /**
      * 邮箱
      */
     private String email;

     /**
      * 省份
      */
     private String province;

     /**
      * 省份名称
      */
     private String provinceName;

     /**
      * 市
      */
     private String city;

     /**
      * 市名称
      */
     private String cityName;

     /**
      * 行政区
      */
     private String district;

     /**
      * 行政区名称
      */
     private String districtName;

     /**
      * 详细地址
      */
     private String address;

     /**
      * 扩展字段1
      */
     private String ext1;

     /**
      * 扩展字段2
      */
     private String ext2;

     /**
      * 扩展字段3
      */
     private String ext3;

     /**
      * 扩展字段4
      */
     private String ext4;

     /**
      * 扩展字段5
      */
     private String ext5;

     /**
      * 部门
      */
     private String department;
     /**
      * 是否推广人员：0是false，1是true
      */
     private String isMarketing;
     /**
      * 推广员代码
      */
     private String marketingCode;
     /**
      * 批次号
      */
     private String batchId;

     /**
      * 创建时间
      */
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private LocalDateTime insertTime;

     /**
      * 创建人
      */
     @JsonIgnore
     private String insertUser;

     /**
      * 更新时间
      */
     @JsonIgnore
     private LocalDateTime updateTime;

     /**
      * 更新人
      */
     @JsonIgnore
     private String updateUser;
     /**
      * 删除状态（0-未删除，1-已删除）
      */
     @JsonIgnore
     private Boolean deleteFlag;
}
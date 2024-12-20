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
      * 生日
      */
     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate birthday;

     /**
      * 邮箱
      */
     private String email;


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
     private String deleteFlag;
}
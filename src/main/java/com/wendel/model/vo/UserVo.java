package com.wendel.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/3/3 17:23
 */
@Data
public class UserVo {

    private String id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 角色
     */
    private String roleId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否渠道登录主账号（1-是，0-否）
     */
    private String mainLogin = "0";
}

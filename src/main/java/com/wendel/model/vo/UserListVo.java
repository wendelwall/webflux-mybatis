package com.wendel.model.vo;

import com.wendel.page.PageInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/3/3 15:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserListVo extends PageInfo{
    private String id;
    private String name;
    private String mobile;
    private String status;
    private String isMarketing;
    private String marketingCode;
}

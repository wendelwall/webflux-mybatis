package com.wendel.controller;

import com.wendel.entity.User;
import com.wendel.model.dto.UserDto;
import com.wendel.model.vo.UserListVo;
import com.wendel.model.vo.UserVo;
import com.wendel.service.UserService;
import com.wendel.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author: sunrise
 * @description: 
 * @copyright: Copyright 2020 riskeys Corporation. All rights reserved.
 * @Date: 2020-12-12 17:30:45
 */
@RestController
@RequestMapping(value = "/rest/channel/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 分页查询用户信息
     * @param vo
     * @return
     */
    @GetMapping
    public Flux<UserDto> getByPageList(@ModelAttribute @Validated UserListVo vo){
        return userService.getByPageList(vo);
    }

    /**
     * 用户创建
     * @param vo
     * @return
     */
    @PostMapping
    public Mono<Integer> create(@RequestBody @Valid UserVo vo){
        return userService.create(vo);
    }

    /**
     * 修改用户
     * @param vo
     * @return
     */
    @PutMapping
    public Mono<User> update(@RequestBody @Valid UserVo vo){
        return userService.update(vo);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}/id")
    public Mono<Result> delete(@PathVariable String id){
        return userService.delete(id);
    }
    
}

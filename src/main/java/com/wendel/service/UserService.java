package com.wendel.service;

import com.wendel.model.dto.UserDto;
import com.wendel.model.vo.UserListVo;
import com.wendel.model.vo.UserVo;
import com.wendel.utils.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: sunrise
 * @description: 
 * @copyright: Copyright 2020 riskeys Corporation. All rights reserved.
 * @Date: 2020-12-12 17:17:20
 */
public interface UserService {
    
    /**
     * 分页查询用户
     * @param vo
     * @return
     */
    Flux<UserDto> getByPageList(Mono<UserListVo> vo);

    /**
     * 创建用户
     * @param userVo
     * @return
     */
    Mono<Result<Void>> create(Mono<UserVo> userVo);

    /**
     * 修改用户
     * @param vo
     * @return
     */
    Mono<Result<Void>> update(Mono<UserVo> vo);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Mono<Result<Void>> delete(String id);

}

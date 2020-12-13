package com.wendel.service.impl;

import com.wendel.entity.User;
import com.wendel.mapper.UserMapper;
import com.wendel.model.dto.UserDto;
import com.wendel.model.vo.UserListVo;
import com.wendel.model.vo.UserVo;
import com.wendel.service.UserService;
import com.wendel.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: sunrise
 * @description:
 * @copyright: Copyright 2020 riskeys Corporation. All rights reserved.
 * @Date: 2020-12-12 17:17:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 分页查询用户
     * @param vo
     * @return
     */
    @Override
    public Flux<UserDto> getByPageList(Mono<UserListVo> vo) {
        return vo.flux().flatMap(p -> userMapper.getByPageList(p));
    }

    /**
     * 创建用户
     * @param userVo
     * @return
     */
    @Override
    public Mono<Result<Void>> create(Mono<UserVo> userVo) {
        return userVo.flatMap(vo -> {
            User user = new User();
            BeanUtils.copyProperties(vo, user);
            user.setStatus("1");
            if(StringUtils.isNotBlank(vo.getPassword())){
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String password = passwordEncoder.encode(vo.getPassword());
                user.setPassword(password);
            }
            user.buildForInsert();
            return userMapper.saveOrUpdate(user);
        }).doOnError(p -> Result.ofSystemError(p.getMessage()))
                .map(p -> Result.ofSuccess(null));

    }

    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @Override
    public Mono<Result<Void>> update(Mono<UserVo> userVo) {
        return userVo.zipWhen(p -> userMapper.getById(p.getId()), (vo, user) -> {
            user.setUserName(vo.getUserName());
            if(StringUtils.isNotBlank(vo.getPassword())){
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String password = passwordEncoder.encode(vo.getPassword());
                user.setPassword(password);
            }
            user.setMobile(vo.getMobile());
            user.setEmail(vo.getEmail());
            return user;
        }).flatMap(p -> userMapper.saveOrUpdate(p))
                .doOnError(p -> Result.ofSystemError(p.getMessage()))
                .map(p -> Result.ofSuccess(null));
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public Mono<Result<Void>> delete(String id) {
        return userMapper.deleteById(id)
                .doOnError(p -> Result.ofSystemError(p.getMessage()))
                .map(p -> Result.ofSuccess(null));
    }
}

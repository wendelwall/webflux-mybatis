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
    public Flux<UserDto> getByPageList(UserListVo vo) {
        return userMapper.getByPageList(vo);
    }

    /**
     * 创建用户
     * @param vo
     * @return
     */
    @Override
    public Mono<Integer> create(UserVo vo) {
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
    }

    @Override
    public Mono<User> update(UserVo vo) {
        return userMapper.getById(vo.getId()).doOnNext(p -> {
            p.setUserName(vo.getUserName());
            p.setPassword(vo.getPassword());
            p.setMobile(vo.getMobile());
            p.setEmail(vo.getEmail());
            userMapper.saveOrUpdate(p).subscribe();
        });
    }

    @Override
    public Mono<Result> delete(String id) {
        return userMapper.deleteById(id).doOnError(p -> Result.ofSystemError(p.getMessage())).map(p -> Result.ofSuccess(null));
    }
}

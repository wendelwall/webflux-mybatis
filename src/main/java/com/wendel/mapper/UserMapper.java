package com.wendel.mapper;

import com.wendel.entity.User;
import com.wendel.model.dto.UserDto;
import com.wendel.model.vo.UserListVo;
import org.apache.ibatis.annotations.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    Mono<User> findByUsername(@Param("userName") String userName, @Param("type") String type);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    Mono<User> getById(@Param("id") String id);

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    Flux<UserDto> getByPageList(@Param("vo") UserListVo vo);

    /**
     * 维护用户信息
     *
     * @param userVo
     * @return
     */
    Mono<Integer> saveOrUpdate(@Param("userVo") User userVo);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Mono<Integer> deleteById(@Param("id") String id);
}
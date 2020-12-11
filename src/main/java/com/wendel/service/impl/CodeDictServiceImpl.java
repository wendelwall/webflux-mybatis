package com.wendel.service.impl;

import com.wendel.entity.CodeDict;
import com.wendel.mapper.CodeDictReactiveMapper;
import com.wendel.model.vo.CodeDictVo;
import com.wendel.service.CodeDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/11/27 20:16
 */
@Slf4j
@Service
public class CodeDictServiceImpl implements CodeDictService {

    @Autowired
    private CodeDictReactiveMapper codeDictMapper;

//    ReactiveSqlSession reactiveSqlSession;
//
//    @PostConstruct
//    public void setUp() {
//        this.codeDictMapper = reactiveSqlSession.getMapper(CodeDictReactiveMapper.class);
//    }

    @Override
    public Flux<CodeDict> select(CodeDictVo vo) {
        return codeDictMapper.select(vo);
    }
}

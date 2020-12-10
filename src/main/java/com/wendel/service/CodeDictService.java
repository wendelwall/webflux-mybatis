package com.wendel.service;

import com.wendel.entity.CodeDict;
import com.wendel.model.vo.CodeDictVo;
import reactor.core.publisher.Flux;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/11/27 20:15
 */
public interface CodeDictService {

    Flux<CodeDict> select(CodeDictVo vo);
}

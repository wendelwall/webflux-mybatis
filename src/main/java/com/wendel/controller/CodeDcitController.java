package com.wendel.controller;

import com.wendel.entity.CodeDict;
import com.wendel.model.vo.CodeDictVo;
import com.wendel.service.CodeDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/11/27 20:25
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/dcit")
public class CodeDcitController {

    @Autowired
    CodeDictService codeDictService;

    @GetMapping
    public Flux<CodeDict> list(@ModelAttribute @Validated CodeDictVo vo){
        long startTime = System.currentTimeMillis();
        Flux<CodeDict> result = codeDictService.select(vo);
        log.info("查询时间：" + (System.currentTimeMillis() - startTime));
        return result;
    }

}

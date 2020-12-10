package com.wendel.mapper;

import com.wendel.entity.CodeDict;
import com.wendel.model.vo.CodeDictVo;
import org.apache.ibatis.annotations.Param;
import reactor.core.publisher.Flux;

public interface CodeDictReactiveMapper {

    Flux<CodeDict> select(@Param("vo") CodeDictVo vo);

}
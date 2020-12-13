package com.wendel.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.rmi.ServerException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2020 yowits Corporation. All rights reserved.
 * @create ：2020/12/12 23:04
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        return assembleError(request);
    }

    private Map<String, Object> assembleError(ServerRequest request) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        Throwable error = getError(request);
        Pair<HttpStatus, String> er = this.getErrorMsg(request);
        if (error instanceof ServerException) {
            errorAttributes.put("code", /*((ServerException) error).getCode().getCode()*/er.getFirst().value());
            errorAttributes.put("msg", er.getSecond());
            errorAttributes.put("data", null);
        } else {
            errorAttributes.put("code", er.getFirst().value());
            errorAttributes.put("msg", er.getSecond());
            errorAttributes.put("data", null);
        }
        return errorAttributes;
    }

    /**
     * 对象校验
     *
     * @param request
     * @return
     */
    public Pair<HttpStatus, String> getErrorMsg(ServerRequest request) {
        Throwable error = getError(request);
        if (!(error instanceof WebExchangeBindException)) {
            return Pair.of(HttpStatus.INTERNAL_SERVER_ERROR, "系统错误");
        }
        List<ObjectError> errors = ((WebExchangeBindException) error).getBindingResult().getAllErrors();
        if (!errors.isEmpty()) {
            return Pair.of(((WebExchangeBindException) error).getStatus(), errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";")));
        }
        return Pair.of(HttpStatus.INTERNAL_SERVER_ERROR, "系统错误");
    }
}

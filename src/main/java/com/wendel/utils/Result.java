package com.wendel.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @author ：wendel
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/5/6 14:42
 */
@Data
public class Result<T> implements Serializable {

    public static final String CODE_SUCCESS = "1";                //成功
    public static final String CODE_SYS_ERR = "0";                //系统错误

    public static final String SYS_SUCCESS_STRING = "成功";
    public static final String SYS_ERR_STRING = "系统错误";

    /**
     * 代码 0-失败，1-成功
     */
    private String code = CODE_SUCCESS;
    /**
     * 响应消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> ofSuccess(T data){
        Result<T> result = new Result();
        result.setCode(CODE_SUCCESS);
        result.setMsg(SYS_SUCCESS_STRING);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ofSuccess(T data, String msg){
        Result<T> result = new Result();
        result.setCode(CODE_SUCCESS);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ofSystemError(String msg) {
        Result<T> rslt = new Result<T>();
        rslt.setCode(CODE_SYS_ERR);
        rslt.setMsg(msg);
        return rslt;
    }

    /**
     * 用于安全的拆包
     * 当success 为true时可以获取正确的data对象[T]
     * 当success 为false时
     * 1.result.open(null)
     * 则只返回null对象，不会抛出异常
     * 2.result.open(p -> {
     * throw new BusinessException(result.getMessage(), this);
     * })
     * 抛出 XXXException
     *
     * @param failedConsumer 异常时，消费方法
     * @return
     */
    public T open(Consumer<Result<T>> failedConsumer) {
        if (CODE_SUCCESS.equals(this.code)) {
            return this.getData();
        } else if (failedConsumer != null) {
            failedConsumer.accept(this);
        }
        return null;
    }
}
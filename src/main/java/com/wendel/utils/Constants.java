package com.wendel.utils;

/**
 * @author ：wendel
 * @description ：
 * @copyright ：	Copyright 2019 riskeys Corporation. All rights reserved.
 * @create ：2019/4/29 13:59
 */
public class Constants {

    /**
     * 固定APPID
     */
    public static final String CODE_APPID  = "icloud";

    /**
     * 操作失败-msg
     */
    public static final String EOR_MSG = "操作失败：";

    /**
     * 成功code
     */
    public static final String SUCCESS = "1";
    /**
     * 成功-msg
     */
    public static final String SUCCESS_MSG = "成功";
    /**
     * 错误code
     */
    public static final String ERROR = "0";

    /**
     * 成功code
     */
    public static final String RETURN_CODE_SUCCESS = "0";
    /**
     * 错误code
     */
    public static final String RETURN_CODE_ERROR = "1";

    public static int DEFAULT_PAGE_INDEX = 1;
    public static int DEFAULT_PAGE_SIZE = 20;

    /**
     * 滑动验证码成功标识
     */
    public static final Integer API_CODE_SLIDE_SUCCESS = 100;

    /**
     * 核心异步核保状态
     */
    public static final String FLAG_ASYNCHRONOUS = "10000";

    /**
     * 默认渠道编码
     */
    public static final String DEFAULT_APP_CODE = "10000";

    /**
     * 扫码支付
     */
    public static final String QR_PAY = "扫码支付";

    /**
     * 收款商户key
     */
    public static final String PAY_PAYEE = "pay-payee";
    /**
     * 收款商户secret
     */
    public static final String PAY_SECRET = "pay-secret";
    /**
     * 收款商户程序标识，根据不同的业务程序区分
     */
    public static final String PAY_APPID = "pay-appid";

    //待核保 默认
    public static final String FLOW_STATUS_TOORDER  = "1";
    //已核保
    public static final String FLOW_STATUS_ORDER = "2";
    //待出单
    public static final String FLOW_STATUS_TOPOLICY  = "3";
    //已出单
    public static final String FLOW_STATUS_POLICY  = "4";

    /**
     * 成功字符串
     */
    public static final String CODE_SUCCESS  = "SUCCESS";
    /**
     * 失败字符串
     */
    public static final String CODE_FAILURE  = "FAILURE";

    /**
     * 日志队列名
     */
    public static final String CODE_LOG_TOPIC  = "riskeys-log";

    /**
     * 推广方式-PC端
     */
    public static final String CODE_EXTENSION_TYPE_1  = "1";
    /**
     * 推广方式-移动端
     */
    public static final String CODE_EXTENSION_TYPE_2  = "2";
}

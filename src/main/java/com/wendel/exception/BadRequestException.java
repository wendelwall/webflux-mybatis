package com.wendel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/***
 * BadRequest请求异常:400
 * @author admin
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {


	/***
	 * 构造函数
	 */
    public BadRequestException() {
        super();
    }

    /***
     * 构造函数
     * @param errCode 错误编码
     * @param srcClass 出现异常的对象
     */
    public BadRequestException(String errCode, Object srcClass) {
        super(errCode, srcClass);
    }
    /***
     * 构造函数
     * @param throwable 抛出的异常对象
     * @param srcClass 出现异常的对象
     */
    public BadRequestException(Throwable throwable, Object srcClass) {
        super(throwable, srcClass);
    }
    /***
     * 构造函数
     * @param errCode 错误编码
     * @param throwable 抛出的异常对象
     * @param srcClass 出现异常的对象
     */
    public BadRequestException(String errCode, Throwable throwable, Object srcClass) {
        super(errCode, throwable, srcClass);
    }
    /***
     * 构造函数
     * @param messages 错误消息对象
     * @param srcClass 出现异常的对象
     */
//    public BadRequestException(MessageVo messages, Object srcClass) {
//    	super();
//    	setSrcClass(srcClass);
//    	setMessageVo(messages);
//    }
    /**
     * 构造函数
     * @param result 绑定的结果对象
     * @param srcClass 出现异常的对象
     */
    public BadRequestException(BindingResult result, Object srcClass) {
    	super();
    	setSrcClass(srcClass);
    	if(result.hasErrors()){
            List<ObjectError>  list = result.getAllErrors();
            for(ObjectError error:list){
            	if (error instanceof FieldError) {
            		FieldError ferr = (FieldError) error;
            		setMessage(ferr.getDefaultMessage(), ferr.getField());
            	} else {
            		setMessage(error.getDefaultMessage(), "");
            	}
            }
    	}
    }
    /***
     * 构造函数
     * @param errCode 错误编码
     * @param srcClass 出现异常的对象
     * @param values 占位符信息
     */
    public BadRequestException(String errCode, Object srcClass, Object... values) {
    	super();
    	setSrcClass(srcClass);
   		setMessage(errCode, values);
    }
    
    /**
     * 构造函数
     * @param errCode 错误编码
     * @param srcClass 出现异常的对象
     * @param field 占位字段名
     */
    public BadRequestException(String errCode, Object srcClass, String field) {
    	super();
    	setSrcClass(srcClass);
   		setMessage(errCode, field);
    }
}

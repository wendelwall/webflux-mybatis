package com.wendel.exception;


import com.wendel.utils.JacksonUtil;
import com.wendel.utils.MessageUtil;
import org.springframework.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Collectors;

/**
 * 基础异常
 * @author admin
 *
 */
public abstract class BaseException extends RuntimeException {

	public static final String ERR_9999 = "error.9999"; //system error
	public static final String ERR_9998 = "error.9998"; //doesn't login
	public static final String ERR_9997 = "error.9997"; //no permission
	public static final String ERR_9996 = "error.9996"; //no found

	/***
	 * 当status为3xx, 4xx, 5xx的时候返回错误消息对象
	 */
	private MessageVo messages = null;
	
	/***
	 * 错误编码
	 */
    private String errorCode = null;

	/***
	 * 异常对象
	 */
	private Throwable originalException = null;
	
	/**
	 * 出现异常的对象
	 */
	private Object srcClass;
	
	/**
	 * 构造函数
	 */
    public BaseException() {
    }
    
	/**
	 * 构造函数
	 * @param errCode 错误编码
	 * @param message 错误消息对象
	 * @param srcClass 出现异常的对象
	 */
	public BaseException(String errCode, String message, Object srcClass) {
		super(message);
		setMessage(errCode, "", message);
		this.srcClass = srcClass;
	}
	
	/***
	 * 构造函数
	 * @param errCode 错误编码
	 * @param srcClass 出现异常的对象
	 */
	public BaseException(String errCode, Object srcClass) {
		String message = MessageUtil.getMessage(errCode);
		setMessage(errCode, "", message);
		this.srcClass = srcClass;
	}

	/**
	 * 构造函数
	 * @param errCode 错误编码
	 * @param e 异常对象
	 * @param srcClass 出现异常的对象
	 */
	public BaseException(String errCode, Throwable e, Object srcClass) {
		this(errCode, MessageUtil.getMessage(errCode), srcClass);
		originalException = e;
	}

	/**
	 * 构造函数
	 * @param srcClass  出现异常的对象
	 * @param e 异常对象
	 */
	public BaseException(Throwable e, Object srcClass) {
		this(HttpStatus.SERVICE_UNAVAILABLE.name(), srcClass);
		originalException = e;
	}

    /**
     * 获取错误消息对象
     * @return 消息对象
     */
    public MessageVo getMessageList() {
    	return messages;
    }
    /**
     * 获取异常对象
     * @return 异常对象
     */
    public Throwable getOriginalException() {
    	return originalException;
    }
    /***
     * 设置错误消息对象
     * @param messageCode 错误消息编码
     * @param field 占位字段名
     */
    public void setMessage(String messageCode, String field) {
        this.errorCode = messageCode;
    	MessageVo messageas = getMessageVo();
    	String objName = MessageUtil.getMessage(field);
    	if (objName != null) {
    		messageas.addMessageObj(messageCode, MessageUtil.getMessage(messageCode, objName), field);
    	} else {
    		messageas.addMessageObj(messageCode, MessageUtil.getMessage(messageCode), field);
    	}
    }
    /**
     * 设置错误消息对象
     * @param messageCode 错误消息编码
     * @param values 占位符信息
     */
    public void setMessage(String messageCode, Object... values) {
        this.errorCode = messageCode;
    	MessageVo messageas = getMessageVo();
   		messageas.addMessageObj(messageCode, MessageUtil.getMessage(messageCode, values), null);
    }

    /***
     * 设置错误消息对象
     * @param messageCode 错误消息编码
     * @param field 占位字段名
     * @param message 错误消息信息
     */
    public void setMessage(String messageCode, String field, String message) {
    	MessageVo messageas = getMessageVo();
    	messageas.addMessageObj(messageCode, message, field);
    	this.errorCode = messageCode;
    }
    /**
     * 获取错误消息对象
     * @return 错误消息对象
     */
    private MessageVo getMessageVo() {
    	if (messages == null)
    		messages = new MessageVo();
		return messages;

    }
    /***
     * 设置错误信息对象
     * @param messages 错误信息对象
     */
    protected void setMessageVo(MessageVo messages) {
    	this.messages = messages;
    }
   /***
    * 设置出现异常的对象
    * @param srcClass 出现异常的对象
    */
    protected void setSrcClass(Object srcClass) {
    	this.srcClass = srcClass;
    }
    /***
     * 出现异常的对象
     * @return 出现异常的对象
     */
    public Object getSrcClass() {
    	return this.srcClass;
    }
    
	/**
	 * 获取消息字符串
	 * @return 消息字符串
	 */
	public String getMessage() {
		StringBuffer buf = new StringBuffer();
		if (messages != null) {
			return messages.getReasons().stream().map(MessageObj::getMessage).collect(Collectors.joining(";"));
		}
		return buf.toString();
	}
	/**
	 * 异常实例转String，返回消息字符串
	 * @return 消息字符串
	 */
	public String toString() {
		return getMessage();
	}

    /**
     * 根据错误对象获取堆栈信息
     * @param t 异常对象
     * @return 异常堆栈信息
     */
	public static String get(Throwable t) {
		if (t == null) {
			return "NULL";
		}
		StringBuffer b = new StringBuffer();
		b.append(t.getMessage());
		b.append("\n");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		t.printStackTrace(ps);
		b.append(baos.toString());
		return b.toString();
	}
	/***
	 * @return 返回错误编码
	 */
    public String getErrorCode() {
        return errorCode;
    }
    /***
     * 获取json格式的消息
     * @return 返回json格式的消息
     */
	public String getJsonMessage() {
		if (getMessageList() == null)
			return null;
		return JacksonUtil.toJSon(getMessageList());
	}
}

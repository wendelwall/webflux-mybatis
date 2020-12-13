package com.wendel.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 功能：错误消息对象
 * 备注：
 */
public class MessageObj {
    @JsonIgnore
    private String msg_id;//消息的编号(在properties中定义的ID)
    @JsonIgnore
    private String field;//消息对应的界面字段名
    private String message;//消息内容(多国语言的消息文字)

    public MessageObj() {
    }

    public MessageObj(String message_id, String message) {
        this.msg_id = message_id;
        this.message = message;
    }

    public MessageObj(String message_id, String message, String message_obj) {
        this.msg_id = message_id;
        this.field = message_obj;
        this.message = message;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

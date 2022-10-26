package com.sidummy.sidumb.model.dto;

public class DefaultResponse<T> {

    private String messages;

    private T data;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

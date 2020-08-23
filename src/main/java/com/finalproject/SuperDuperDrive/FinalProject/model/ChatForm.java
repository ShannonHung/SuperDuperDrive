package com.finalproject.SuperDuperDrive.FinalProject.model;

//ChatForm的目的在接收html傳過來的資料 是原始資料
public class ChatForm {
    private String username;
    private String messageText;
    private String messageType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}

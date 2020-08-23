package com.finalproject.SuperDuperDrive.FinalProject.model;

//將ChatForm的資料 根據type在MessageService做轉換 重新存入字串
public class MessageForm {
    private String text;

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
}

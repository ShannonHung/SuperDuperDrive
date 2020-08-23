package com.finalproject.SuperDuperDrive.FinalProject.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageListService {
    private List<String> messages;

    public MessageListService(){
        this.messages = new ArrayList<>();
    }
    public void addMessage(String message){
        messages.add(message);
    }
    public List<String> getMessages(){
        //複製一個全新的ArrayList去回傳 而非使用裡面的List<String> messages
        return new ArrayList<>(this.messages);
    }
}

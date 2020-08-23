package com.finalproject.SuperDuperDrive.FinalProject.service;

import com.finalproject.SuperDuperDrive.FinalProject.mapper.MessageMapper;
import com.finalproject.SuperDuperDrive.FinalProject.model.ChatForm;
import com.finalproject.SuperDuperDrive.FinalProject.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    //當spring建立起來的時候 就new出新物件
    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating MessageService Bean");
    }

    //根據ChatForm.type做文字轉換重新換String並且存入ChatMessage
    public void addMessage(ChatForm chatForm) {
        System.out.println("MessageService get in ");
        ChatMessage newMessage = new ChatMessage();

        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say":
                newMessage.setMessageText(chatForm.getMessageText());
                break;
            case "Shout":
                newMessage.setMessageText(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                newMessage.setMessageText(chatForm.getMessageText().toLowerCase());
                break;
        }
        System.out.println("到insert之前都正常" + chatForm.getUsername());

        messageMapper.insert(newMessage);
    }
    public List<ChatMessage> getChatMessages(){
        return messageMapper.getMessage();
    }

}

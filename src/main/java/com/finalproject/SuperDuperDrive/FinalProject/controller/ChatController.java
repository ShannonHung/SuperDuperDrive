package com.finalproject.SuperDuperDrive.FinalProject.controller;

import com.finalproject.SuperDuperDrive.FinalProject.model.ChatForm;
import com.finalproject.SuperDuperDrive.FinalProject.service.MessageService;
import com.finalproject.SuperDuperDrive.FinalProject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    //if spring recieve "GET" request, return chat.html
    @GetMapping
    public String getChatPage(@ModelAttribute ChatForm chatForm, Model model){
        //print all of the messages
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "chat";
    }

    @PostMapping
    public String postChatPage(Authentication  authentication, @ModelAttribute("chatForm") ChatForm chatForm, Model model){
        chatForm.setUsername(authentication.getName());
        System.out.println("ChatController.java " + authentication.getName());
        //把 name, text, type傳進去 會新增資料
        this.messageService.addMessage(chatForm);
        System.out.println("after add message");

        //clean the context in chatForm we just type in html
        chatForm.setMessageText("");
        model.addAttribute("chatMessages", this.messageService.getChatMessages());
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes(){
        return new String[]{"Say", "Shout", "Whisper"};
    }

}

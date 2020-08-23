package com.finalproject.SuperDuperDrive.FinalProject.controller;

import com.finalproject.SuperDuperDrive.FinalProject.model.MessageForm;
import com.finalproject.SuperDuperDrive.FinalProject.model.User;
import com.finalproject.SuperDuperDrive.FinalProject.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController {
    private NoteService noteService;
    private UserService userService;
    private FileService fileService;
    private CredentialService credentialService;

    public HomeController(UserService userService, NoteService noteService,FileService fileService,CredentialService credentialService){
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService= credentialService;
    }

    @GetMapping()
    public String getHomePage(Authentication authentication, Model model){
        User user = userService.getUser(authentication.getName());

        //show Notes list from the database through NoteService
        model.addAttribute("Notes", noteService.getNotes(user.getUserId()));

        //show Files List from the database through FileService
        model.addAttribute("Files", fileService.getFiles(user.getUserId()));

        //show Credentials List from the database through CredentialService
        model.addAttribute("userCredentials", credentialService.getCredentials(user.getUserId()));
        return "home";
    }

}

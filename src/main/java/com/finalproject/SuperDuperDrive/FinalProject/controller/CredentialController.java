package com.finalproject.SuperDuperDrive.FinalProject.controller;

import com.finalproject.SuperDuperDrive.FinalProject.model.Credential;
import com.finalproject.SuperDuperDrive.FinalProject.model.Note;
import com.finalproject.SuperDuperDrive.FinalProject.model.User;
import com.finalproject.SuperDuperDrive.FinalProject.service.CredentialService;
import com.finalproject.SuperDuperDrive.FinalProject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class CredentialController {
    UserService userService;
    CredentialService credentialService;
    public CredentialController(UserService userService, CredentialService credentialService){
        this.userService= userService;
        this.credentialService= credentialService;
    }

    @PostMapping("/credential/add")
    public String addCredential(Credential credential, Model model, Authentication authentication){

        if(credential.getCredentialId() != null){
            return this.UpdateCredential(credential, model);
        }else{
            User user = userService.getUser(authentication.getName());
            credential.setUserId(user.getUserId());
            Boolean Addresult = credentialService.addCredential(credential);
            if(Addresult){
                model.addAttribute("SuccessAction", true);
            }else{
                model.addAttribute("ErrorAction", true);
            }
        }
        return "result";
    }
    private String UpdateCredential(Credential cred, Model model){
        boolean updateResult = credentialService.updateCred(cred);
        if(updateResult){
            model.addAttribute("SuccessAction", true);
        }else{
            model.addAttribute("ErrorAction", true);
        }
        return "result";
    }

    @GetMapping("cred/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") int credentialId, Model model){
        Boolean DeleteResult = credentialService.deleteCredential(credentialId);
        if(DeleteResult){
            model.addAttribute("SuccessAction", true);
        }else{
            model.addAttribute("ErrorAction", true);
        }
        return "result";
    }
}

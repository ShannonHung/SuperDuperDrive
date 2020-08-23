package com.finalproject.SuperDuperDrive.FinalProject.controller;

import com.finalproject.SuperDuperDrive.FinalProject.model.User;
import com.finalproject.SuperDuperDrive.FinalProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    //you need UserService to Create a new User into the database
    private final UserService userService;
    public SignupController(UserService userService){
        this.userService = userService;
    }

    //if you recieve "get" request spring will help you get to the singup page
    @GetMapping()
    public String signupView(){
        return "signup";
    }

    //if spring recieve "post" request, spring will
    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("這是user " + user.getUsername() + user.getUserId() + user.getSalt());
        String signupError = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            //session在跳转页面后马上移除对象，这种方式是隐藏了参数，链接地址上不会暴露参数
            redirectAttributes.addFlashAttribute("signup", "You successfully signed up!");
            model.addAttribute("signupSuccess", true);
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}

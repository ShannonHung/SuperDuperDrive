package com.finalproject.SuperDuperDrive.FinalProject.controller;

import com.finalproject.SuperDuperDrive.FinalProject.model.Note;
import com.finalproject.SuperDuperDrive.FinalProject.model.User;
import com.finalproject.SuperDuperDrive.FinalProject.service.MessageService;
import com.finalproject.SuperDuperDrive.FinalProject.service.NoteService;
import com.finalproject.SuperDuperDrive.FinalProject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    NoteService noteService;
    UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    //we will get Note which included notetitle, notedescription
    //but we don't have userid, so we need Authentication in order to know it
    //there are two kind of situation /1. add new note /2. update the note
    @PostMapping("/note/add")
    public String AddNote(Authentication authentication, Note note, Model model){
        System.out.println("note.getNoteid():"+note.getNoteid() + note.getNoteTitle() + note.getNoteDescription());
        //if the model Note had noteid, it is mean that the note exist in the database
        if(note.getNoteid() != null){
            return this.UpdateNote(note, model);
        }

        //see the result of adding the note
        boolean AddResult;
        //get user information
        User user = this.userService.getUser(authentication.getName());
        AddResult = this.noteService.addNote(note, user.getUserId());

        if(AddResult){
            //show the message:Your changes were successfully saved.
            model.addAttribute("SuccessAction", true);
        }else{
            model.addAttribute("ErrorAction", true);
        }
        return "result";
    }
    private String UpdateNote(Note note, Model model){
        boolean updateResult = noteService.updateNote(note);
        if(updateResult){
            model.addAttribute("SuccessAction", true);
        }else{
            model.addAttribute("ErrorAction", true);
        }
        return "result";
    }

    @GetMapping("/note/delete/{noteid}")
    public String deletNote(@PathVariable("noteid")int noteid, Note note, Model model){
        if(this.noteService.deleteNote(noteid)){
            model.addAttribute("SuccessAction", true);
        }else{
            model.addAttribute("ErrorAction", true);
        }
        return "result";
    }
}

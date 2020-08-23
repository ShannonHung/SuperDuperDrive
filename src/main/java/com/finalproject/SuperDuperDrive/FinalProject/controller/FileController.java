package com.finalproject.SuperDuperDrive.FinalProject.controller;

import com.finalproject.SuperDuperDrive.FinalProject.model.File;
import com.finalproject.SuperDuperDrive.FinalProject.model.User;
import com.finalproject.SuperDuperDrive.FinalProject.service.FileService;
import com.finalproject.SuperDuperDrive.FinalProject.service.UserService;
import com.google.common.net.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class FileController {
    private FileService fileService;
    private UserService userService;
    public FileController(FileService fileService,UserService userService){
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/file/upload")
    public String fileUpload(Authentication authentication,@RequestParam("fileUpload")  MultipartFile file, Model model){
        String Result = "";
        User user = userService.getUser(authentication.getName());

        //make sure that sth is in the file, or you cannot put empty file into database
        System.out.println("fileService.fileExist(file.getOriginalFilename())" + fileService.fileExist(file.getOriginalFilename()));

        if(file.getOriginalFilename().isEmpty()) {
            Result = "Empty";
        }else if (fileService.fileExist(file.getOriginalFilename())!= null){
            Result = "SameFile";
        }else{
            if(fileService.addFile(file, user.getUserId())) Result = "Success";
            else{
                Result = "Fail";
            }
        }
        System.out.println("Result: " + Result);
        switch (Result){
            case "Empty":
                model.addAttribute("EmptyFile" , true);
                System.out.println("here is empty");
                break;
            case "SameFile":
                model.addAttribute("SameFileName", true);
                System.out.println("here is same");
                break;
            case "Fail":
                model.addAttribute("ErrorAction", true);
                System.out.println("here is error");
                break;
            case "Success":
                model.addAttribute("SuccessAction", true);
                break;
        }

        return "result";
    }

    @GetMapping("/file/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") int fileId, Model model){
        System.out.println("filedelete called");
        boolean deleteResult = fileService.deleteFile(fileId);
        if(deleteResult){
            model.addAttribute("SuccessAction", true);
        }else{
            model.addAttribute("ErrorAction", false);
        }
        return "result";
    }

    @GetMapping("/file/download/{fileId}")
    public ResponseEntity<byte[]> downFile(@PathVariable("fileId") int fileId){
        File file = fileService.getFile(fileId);
        String filename = file.getFileName();
        //Create a builder with the status set to OK.
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename +"\"")
                .body(file.getFileData());
    }
}

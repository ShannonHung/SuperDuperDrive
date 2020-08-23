package com.finalproject.SuperDuperDrive.FinalProject.service;

import com.finalproject.SuperDuperDrive.FinalProject.mapper.FileMapper;
import com.finalproject.SuperDuperDrive.FinalProject.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    FileMapper fileMapper;
    public FileService(FileMapper fileMapper){
        this.fileMapper = fileMapper;
    }
    public boolean addFile(MultipartFile multipartFile, int userId){
        try {
            File file = new File(null,
                    multipartFile.getOriginalFilename(),
                    multipartFile.getContentType(),
                    multipartFile.getSize(),
                    userId,
                    multipartFile.getBytes());
            System.out.println(
                    "This is what multiparfile have: "+ "\n" +
                            "multipartFile.getOriginalFilename()"+
                            multipartFile.getOriginalFilename() +"\n" +
                            "multipartFile.getName()"+
                            multipartFile.getName()+"\n" +
                            "multipartFile.getContentType()"+
                            multipartFile.getContentType()+"\n" +
                            "multipartFile.getInputStream()"+
                            multipartFile.getInputStream()+"\n" +
                            "multipartFile.getBytes()"+
                            multipartFile.getBytes()
            );
            int fileResult = this.fileMapper.insert(file);
            if(fileResult > 0) return true;
            else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<File> getFiles(int userId){
        return fileMapper.getFiles(userId);
    }

    public Boolean deleteFile(int fileId){
        return fileMapper.delete(fileId);
    }

    public File getFile(int fileId){
        return fileMapper.getFile(fileId);
    }

    public File fileExist(String fileName){
        return fileMapper.fileExist(fileName);
    }
}

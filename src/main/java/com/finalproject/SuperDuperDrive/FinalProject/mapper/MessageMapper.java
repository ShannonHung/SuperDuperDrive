package com.finalproject.SuperDuperDrive.FinalProject.mapper;

import com.finalproject.SuperDuperDrive.FinalProject.model.ChatMessage;
import com.finalproject.SuperDuperDrive.FinalProject.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getMessage();

    //這裡的#{messageText}會去對應到從MessageService裡面傳進來的ChatMessage的屬性messageText
    @Insert("INSERT INTO MESSAGES (username, messagetext) VALUES(#{username}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(ChatMessage chatMessage);
}

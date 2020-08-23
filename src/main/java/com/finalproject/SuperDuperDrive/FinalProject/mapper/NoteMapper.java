package com.finalproject.SuperDuperDrive.FinalProject.mapper;

import com.finalproject.SuperDuperDrive.FinalProject.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    /*
    CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);
     */
    //add new Note
    @Insert("INSERT INTO notes (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);

    //show userid's notes
    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getNotes(int userId);

    //edit notes
    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteid}")
    int update(String noteTitle, String noteDescription, int noteid);

    //delete notes
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    boolean delete(int noteid);

}

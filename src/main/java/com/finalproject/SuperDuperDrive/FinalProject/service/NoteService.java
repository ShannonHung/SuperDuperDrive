package com.finalproject.SuperDuperDrive.FinalProject.service;

import com.finalproject.SuperDuperDrive.FinalProject.mapper.NoteMapper;
import com.finalproject.SuperDuperDrive.FinalProject.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper){
        this.noteMapper = noteMapper;
    }

    public boolean addNote(Note note, int userId)
    {
        //Page would not appear userid, you should get by yourself
        note.setUserId(userId);
        //after inserting the note, it will return noteid
        int noteid = noteMapper.insert(note);
        //if insert method return -1 or negative num, insert fail
        if(noteid > 0) return true;
        else{
            return false;
        }
    }

    public List<Note> getNotes(int userId){
        return noteMapper.getNotes(userId);
    }

    public boolean deleteNote(int noteid){
        return noteMapper.delete(noteid);
    }

    public boolean updateNote(Note note){
        System.out.println("UpdateNote from NoteService was called");
        int result = noteMapper.update(note.getNoteTitle(), note.getNoteDescription(), note.getNoteid());
        if(result > 0) return true;
        else{
            return false;
        }
    }
}

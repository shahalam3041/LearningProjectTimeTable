package com.example.shahalam.learningprojecttimetable;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM "+ Constants.NOTE_TABLE_NAME)
    List<Note> getNotes();

    @Insert
    long insertNote(Note note);

    @Update
    void updateNote(Note repos);

    @Delete
    void deleteNote(Note note);

    @Delete
    void deleteNotes(Note... note);
}

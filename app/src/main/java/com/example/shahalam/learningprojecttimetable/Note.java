package com.example.shahalam.learningprojecttimetable;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = Constants.NOTE_TABLE_NAME)
public class Note {
    @PrimaryKey(autoGenerate = true)
    private long note_id;
    String title;
    String content;
    Date date;



    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = new Date(System.currentTimeMillis());

    }

    public long getNote_id() {
        return note_id;
    }

    public void setNote_id(long note_id) {
        this.note_id = note_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

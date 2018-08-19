package com.example.shahalam.learningprojecttimetable;

public class ClassLecture {

    String teacherName, courseName, roomNo;

    public ClassLecture(String teacherName, String courseName, String roomNo) {
        this.teacherName = teacherName;
        this.courseName = courseName;
        this.roomNo = roomNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}

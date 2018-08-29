package com.example.shahalam.learningprojecttimetable;

import java.util.List;

public interface DayFragmentCommunicator {

    void sendDataToDayFragment(List<ClassLecture> classLectureList);
}

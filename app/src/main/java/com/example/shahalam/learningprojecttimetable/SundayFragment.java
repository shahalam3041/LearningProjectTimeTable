package com.example.shahalam.learningprojecttimetable;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SundayFragment extends Fragment {

    RecyclerView classRecyclerView;
    List<ClassLecture> sundayClassLectureList = new ArrayList<>();
    ClassRecyclerViewAdapter sundayRVAdapter = new ClassRecyclerViewAdapter(sundayClassLectureList,getActivity());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_day_fragment, container,false);
        classRecyclerView = view.findViewById(R.id.classRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((ClassFragment)getActivity()).passValue(new DayFragmentCommunicator() {
            @Override
            public void sendDataToDayFragment(List<ClassLecture> classLectureList) {
                upDateRecyclerView(classLectureList);
            }
        });
    }

    public void upDateRecyclerView(List<ClassLecture> classLectureList) {
        classRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        sundayRVAdapter = new ClassRecyclerViewAdapter(sundayClassLectureList, getActivity());
        classRecyclerView.setAdapter(sundayRVAdapter);
        sundayRVAdapter.notifyDataSetChanged();

    }
}

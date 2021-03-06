package com.example.shahalam.learningprojecttimetable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassFragment extends Fragment {

    DayFragmentCommunicator fragmentCommunicator;
    List<ClassLecture> sundayLectureList;
    ClassRecyclerViewAdapter classRecyclerViewAdapter;
    RecyclerView classRecyclerView;
    TabLayout tabLayout;
    FloatingActionButton fab;
    ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_class,container,false);
        tabLayout = view.findViewById(R.id.tabLayout);
        fab = view.findViewById(R.id.fab_in_class);
        viewPager = view.findViewById(R.id.viewPager);

        // setup viewPager and TabLayout
        ClassPagerAdapter myPagerAdapter = new ClassPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fabPosition = viewPager.getCurrentItem();
                switch (fabPosition) {
                    case 0:{        // add new lecture to sunday fragment
                        classRecyclerViewAdapter = new ClassRecyclerViewAdapter(sundayLectureList,getActivity());
                        ClassLecture newLecture = new ClassLecture("Ahosan Habib","ML","WB-241");
                        sundayLectureList.add(newLecture);
                        fragmentCommunicator.sendDataToDayFragment(sundayLectureList);
                    }
                }
            }
        });

        return view;
    }

    public void passValue(DayFragmentCommunicator dayFragmentCommunicator) {
        this.fragmentCommunicator = dayFragmentCommunicator;
    }
}


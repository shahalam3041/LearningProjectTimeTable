package com.example.shahalam.learningprojecttimetable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ClassRecyclerViewAdapter extends RecyclerView.Adapter<ClassRecyclerViewAdapter.ClassViewHolder> {

    List<ClassLecture> lectureList;
    Context context;

    public ClassRecyclerViewAdapter(List<ClassLecture> lectureList, Context context) {
        this.lectureList = lectureList;
        this.context = context;
    }

    @NonNull
    @Override
    public ClassRecyclerViewAdapter.ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_class,parent,false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassRecyclerViewAdapter.ClassViewHolder holder, int position) {
        holder.teacherName.setText(lectureList.get(position).getTeacherName());
        holder.courseName.setText(lectureList.get(position).getCourseName());
        holder.roomNo.setText(lectureList.get(position).getRoomNo());

    }

    @Override
    public int getItemCount() {
        return lectureList.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {

        TextView teacherName, courseName, roomNo;

        public ClassViewHolder(View itemView) {
            super(itemView);

            teacherName = itemView.findViewById(R.id.teacherName);
            courseName = itemView.findViewById(R.id.courseName);
            roomNo = itemView.findViewById(R.id.roomNo);
        }
    }
}

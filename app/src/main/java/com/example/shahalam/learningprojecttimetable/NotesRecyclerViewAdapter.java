package com.example.shahalam.learningprojecttimetable;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.NoteViewHolder> {

    List<NoteRecyclerViewModelClass> noteList;

    public NotesRecyclerViewAdapter(List<NoteRecyclerViewModelClass> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NotesRecyclerViewAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerViewAdapter.NoteViewHolder holder, int position) {
        holder.noteTitle.setText(noteList.get(position).getTitle());
        holder.noteBody.setText(noteList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle,noteBody;
        public NoteViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitleID);
            noteBody = itemView.findViewById(R.id.noteBodyID);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    noteList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), noteList.size());
                    notifyDataSetChanged();

                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int p=getLayoutPosition();
                    Toast.makeText(view.getContext(), "Recycle Click" + p +"  ", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}

package com.example.shahalam.learningprojecttimetable;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {

    FloatingActionButton newNoteFab;
    List<Note> noteList = new ArrayList<>();
    NoteDatabase noteDatabase;
    RecyclerView noteRecyclerView;
    NotesAdapter notesAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note,container,false);

        noteRecyclerView = view.findViewById(R.id.recycler_view_notes);
        newNoteFab = view.findViewById(R.id.fab_in_note);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            EventBus.getDefault().register(this);
        }

        noteDatabase  = NoteDatabase.getInstance(getActivity());
        noteList = noteDatabase.getNoteDao().getNotes();
        notesAdapter = new NotesAdapter(noteList, getActivity());

        newNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText titleValue, bodyValue;
                Button save, discard;

                View dialogView = getLayoutInflater().inflate(R.layout.new_note_dialog,null);

                titleValue = dialogView.findViewById(R.id.title_editText_newNote_dialog);
                bodyValue = dialogView.findViewById(R.id.body_editText_newNote_dialog);
                save = dialogView.findViewById(R.id.save_new_note_dialog_btn);
                discard = dialogView.findViewById(R.id.discard_new_note_dialog_btn);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

                dialogBuilder.setView(dialogView);
                final AlertDialog dialog = dialogBuilder.create();
                dialog.show();

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = titleValue.getText().toString();
                        String body = bodyValue.getText().toString();

                        if ( !title.equals("") &&  !body.equals("")) {

                            Note note = new Note(title,body);
                            noteList.add(note);
                            noteDatabase.getNoteDao().insertNote(note);
                            notesAdapter.notifyDataSetChanged();
                            dialog.cancel();
                        } else {
                            Toast.makeText(getContext(),"Note or Title is empty !!!",Toast.LENGTH_LONG ).show();
                        }
                    }
                });
                discard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        noteRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        noteRecyclerView.setAdapter(notesAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String data) {
        if(data.equals("refresh_data")) {
            notesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}



package com.example.shahalam.learningprojecttimetable;

import android.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {

    FloatingActionButton newNoteFab;
    List<NoteRecyclerViewModelClass> noteList = new ArrayList<>();
    RecyclerView noteRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note,container,false);
        final NotesRecyclerViewAdapter notesRecyclerViewAdapter = new NotesRecyclerViewAdapter(noteList);
        noteRecyclerView = view.findViewById(R.id.recycler_view_notes);
        newNoteFab = view.findViewById(R.id.fab_in_note);

        NoteRecyclerViewModelClass note1 = new NoteRecyclerViewModelClass("Steggared","Add top margin only for the first item to avoid double space between items if (parent. .... Also, set item offset value as padding for its RecyclerView , and specify .... getSpanCount(); } else if (mgr instanceof StaggeredGridLayoutManager) ");
        NoteRecyclerViewModelClass note2 = new NoteRecyclerViewModelClass("Equal Column","Equal column spacing for Android RecyclerView GridLayoutManager by using ... should be half size of the actual value you want to add as space between items.");
        NoteRecyclerViewModelClass note3 = new NoteRecyclerViewModelClass("Set Space"," Advantages of using layout manager either List, Grid or Staggered. ... Gallery of images is set but there is no spacing in between grid items.");
        NoteRecyclerViewModelClass note4 = new NoteRecyclerViewModelClass("Create the Authorization Request"," AdCreate the AuthorizationServiceConfiguration object in the AuthorizeListener::onClick method which declares the authorization and token endpoints of the OAuth server you wish to authorize with. In our example, we will use Google, but this will work with any compliant OAuth server.");
        NoteRecyclerViewModelClass note5 = new NoteRecyclerViewModelClass("Open the starter app"," The project won't build right away, as we have some boilerplate for AppAuth just to preserve the state and connect some buttons. You'll need to add the AppAuth gradle dependency in the next step before it can build.s.");
        NoteRecyclerViewModelClass note6 = new NoteRecyclerViewModelClass(" Overview","In this codelab, you'll learn how to use enable Single Sign-on (SSO) with Chrome Custom Tabs via the AppAuth library, and optionally push managed configuration to provide a user login hint.");
        NoteRecyclerViewModelClass note7 = new NoteRecyclerViewModelClass("Welcome to Codelabs!"," Google Developers CodeLabs provide a guided, tutorial, hands-on coding experience. Most codelabs will step you through the process of building a small application, or adding a new feature to an existing application. They cover a wide range of topics such as Android Wear, Google Compute Engine, Project Tango, and Google APIs on iOS");
        noteList.add(note1);
        noteList.add(note2);
        noteList.add(note3);
        noteList.add(note4);
        noteList.add(note5);
        noteList.add(note6);
        noteList.add(note7);

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

                            NoteRecyclerViewModelClass note = new NoteRecyclerViewModelClass(title,body);
                            noteList.add(note);
                            notesRecyclerViewAdapter.notifyDataSetChanged();
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
        noteRecyclerView.setAdapter(notesRecyclerViewAdapter);
        return view;
    }
}

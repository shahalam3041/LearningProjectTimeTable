package com.example.shahalam.learningprojecttimetable;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static android.content.ContentValues.TAG;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    List<Note> noteList;
    NoteDatabase noteDatabase;
    Context context;

    public NotesAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteViewHolder holder, int position) {
        holder.noteTitle.setText(noteList.get(position).getTitle());
        holder.noteBody.setText(noteList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteBody;

        Note note;

        public NoteViewHolder(View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.noteTitleID);
            noteBody = itemView.findViewById(R.id.noteBodyID);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final AlertDialog CRURdialog;       // dialog for delete and edit operation
                    Button delete, edit, cancel;
                    View noteCRUDView = LayoutInflater.from(context).inflate(R.layout.crud_dialog, null);

                    delete = noteCRUDView.findViewById(R.id.delete);
                    edit = noteCRUDView.findViewById(R.id.edit);
                    cancel = noteCRUDView.findViewById(R.id.cancel);

                    AlertDialog.Builder noteCRUDBuilder = new AlertDialog.Builder(context);
                    noteCRUDBuilder.setView(noteCRUDView);
                    CRURdialog = noteCRUDBuilder.create();
                    CRURdialog.show();

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            note = noteList.get(getAdapterPosition());

                            noteDatabase = NoteDatabase.getInstance(context);
                            noteDatabase.getNoteDao().deleteNote(note);

                            noteList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(), noteList.size());
                            notifyDataSetChanged();
                            CRURdialog.cancel();     // close 'edit or delete" dialog and will be open new note dialog
                        }
                    });

                    edit.setOnClickListener(new View.OnClickListener() {

                        EditText titleValue, bodyValue;
                        Button save, discard;

                        NoteDatabase noteDatabase;
                        NotesAdapter notesAdapter;

                        @Override
                        public void onClick(View v) {
                            CRURdialog.cancel();     // close the previous dialog which show delete or edit option

                            View newNoteDialogView = LayoutInflater.from(context).inflate(R.layout.new_note_dialog, null);

                            AlertDialog.Builder newNoteDialogBuilder = new AlertDialog.Builder(context);
                            newNoteDialogBuilder.setView(newNoteDialogView);
                            final AlertDialog NewNoteDialog = newNoteDialogBuilder.create();
                            NewNoteDialog.show();

                            titleValue = newNoteDialogView.findViewById(R.id.title_editText_newNote_dialog);
                            bodyValue = newNoteDialogView.findViewById(R.id.body_editText_newNote_dialog);
                            save = newNoteDialogView.findViewById(R.id.save_new_note_dialog_btn);
                            discard = newNoteDialogView.findViewById(R.id.discard_new_note_dialog_btn);

                            note = noteList.get(getAdapterPosition());
                            titleValue.setText(note.getTitle());
                            bodyValue.setText(note.getContent());

                            Log.d(TAG, "onClick: before save button pressed 119" + note.getTitle());
                            save.setOnClickListener(new View.OnClickListener() {


                                @Override
                                public void onClick(View v) {

                                    String title = titleValue.getText().toString();
                                    String body = bodyValue.getText().toString();

                                    if (!title.equals("") && !body.equals("")) {
                                        noteDatabase = NoteDatabase.getInstance(context);
                                        Note editedNote = noteList.get(getAdapterPosition());
                                        editedNote.setTitle(title);
                                        editedNote.setContent(body);
                                        noteDatabase.getNoteDao().updateNote(editedNote);
                                        EventBus.getDefault().post("refresh_data");

                                    } else {
                                        Toast.makeText(context, "Note or Title is empty !!!", Toast.LENGTH_LONG).show();
                                    }
                                    NewNoteDialog.cancel();
                                }
                            });

                            discard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    NewNoteDialog.cancel();
                                }
                            });
                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CRURdialog.cancel();
                        }
                    });

                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int p = getLayoutPosition();
                    Toast.makeText(view.getContext(), "Press Long click to Delete or Edit", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}

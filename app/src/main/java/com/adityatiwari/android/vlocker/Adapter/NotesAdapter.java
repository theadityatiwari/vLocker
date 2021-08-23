package com.adityatiwari.android.vlocker.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.adityatiwari.android.vlocker.Activity.LoginActivity;
import com.adityatiwari.android.vlocker.Activity.MainActivity;
import com.adityatiwari.android.vlocker.Activity.UpdateNoteActivity;
import com.adityatiwari.android.vlocker.Entity.Notes;
import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.Tabs.NotesFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    NotesFragment notesFragment;
    List<Notes> notes;
    List<Notes> allNotesItem;
    private BiometricPrompt.PromptInfo promptInfo;


    public NotesAdapter(NotesFragment notesFragment, List<Notes> notes) {
        this.notesFragment = notesFragment;
        this.notes = notes;
//        allNotesItem = new ArrayList<>(notes);
    }
//    public void SearchNotes(List<Notes> filteredNames){
//        this.notes = filteredNames;
//        notifyDataSetChanged();
//
//    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater
                .from(notesFragment.getContext())
                .inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.NotesViewHolder holder, int position) {
        Notes note = notes.get(position);

        switch (note.notesPriority) {
            case "3":
                holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "1":
                holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
                break;
        }
        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.date.setText(note.notesDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(notesFragment.getActivity(), UpdateNoteActivity.class);
                intent.putExtra("id", note.id);
                intent.putExtra("title", note.notesTitle);
                intent.putExtra("subtitle", note.notesSubtitle);
                intent.putExtra("notes", note.notes);
                intent.putExtra("priority", note.notesPriority);
                notesFragment.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, date;
        View notesPriority;

        public NotesViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notes_title);
            subtitle = itemView.findViewById(R.id.notes_subtitle);
            date = itemView.findViewById(R.id.notes_date);
            notesPriority = itemView.findViewById(R.id.notes_priority_in_item);
        }
    }
}

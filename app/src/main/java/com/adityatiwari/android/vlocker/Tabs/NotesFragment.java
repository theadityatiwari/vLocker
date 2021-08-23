package com.adityatiwari.android.vlocker.Tabs;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.adityatiwari.android.vlocker.Activity.InsertNoteActivity;
import com.adityatiwari.android.vlocker.Adapter.NotesAdapter;
import com.adityatiwari.android.vlocker.DataViewModel;
import com.adityatiwari.android.vlocker.Entity.Notes;
import com.adityatiwari.android.vlocker.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NotesFragment extends Fragment {

    ExtendedFloatingActionButton mAddNoteBtn;
    DataViewModel dataViewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter adapter;
    View emptyView;
    TextView noFilter, lowToHigh, highToLow;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        noFilter = rootView.findViewById(R.id.no_filter);
        lowToHigh = rootView.findViewById(R.id.low_to_high);
        highToLow = rootView.findViewById(R.id.high_to_low);
        emptyView = rootView.findViewById(R.id.empty_view);



        noFilter.setBackgroundResource(R.drawable.filter_selected_bg);

        noFilter.setOnClickListener(v -> {
            highToLow.setBackgroundResource(R.drawable.filter_unselected_bg);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_bg);
            noFilter.setBackgroundResource(R.drawable.filter_selected_bg);
            dataViewModel.getAllNotes.observe(getViewLifecycleOwner(),notes -> {
                notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new NotesAdapter(NotesFragment.this,notes);
                notesRecyclerView.setAdapter(adapter);
                if (notes.isEmpty()){
                    notesRecyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    notesRecyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
            });
        });

        lowToHigh.setOnClickListener(v -> {
            highToLow.setBackgroundResource(R.drawable.filter_unselected_bg);
            lowToHigh.setBackgroundResource(R.drawable.filter_selected_bg);
            noFilter.setBackgroundResource(R.drawable.filter_unselected_bg);
            dataViewModel.lowToHigh.observe(getViewLifecycleOwner(),notes -> {
                notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new NotesAdapter(NotesFragment.this,notes);
                notesRecyclerView.setAdapter(adapter);
                if (notes.isEmpty()){
                    notesRecyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    notesRecyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
            });
        });

        highToLow.setOnClickListener(v -> {
            highToLow.setBackgroundResource(R.drawable.filter_selected_bg);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_bg);
            noFilter.setBackgroundResource(R.drawable.filter_unselected_bg);
            dataViewModel.highToLow.observe(getViewLifecycleOwner(),notes -> {
                notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new NotesAdapter(NotesFragment.this,notes);
                notesRecyclerView.setAdapter(adapter);

                if (notes.isEmpty()){
                    notesRecyclerView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                } else {
                    notesRecyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }
            });
        });



        notesRecyclerView = rootView.findViewById(R.id.notes_recyclerView);

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        mAddNoteBtn = rootView.findViewById(R.id.add_notes_fab_btn);

        mAddNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertNoteActivity.class);
                startActivity(intent);
            }
        });
        dataViewModel.getAllNotes.observe(getViewLifecycleOwner(),notes -> {
                notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new NotesAdapter(NotesFragment.this,notes);
                notesRecyclerView.setAdapter(adapter);
            if (notes.isEmpty()){
                notesRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                notesRecyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
        });



        // Inflate the layout for this fragment
        return rootView;
    }

}

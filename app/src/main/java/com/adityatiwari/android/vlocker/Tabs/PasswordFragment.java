package com.adityatiwari.android.vlocker.Tabs;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adityatiwari.android.vlocker.Activity.InsertNoteActivity;
import com.adityatiwari.android.vlocker.Activity.InsertPasswordActivity;
import com.adityatiwari.android.vlocker.Adapter.NotesAdapter;
import com.adityatiwari.android.vlocker.Adapter.PasswordAdapter;
import com.adityatiwari.android.vlocker.DataViewModel;
import com.adityatiwari.android.vlocker.Entity.Password;
import com.adityatiwari.android.vlocker.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PasswordFragment extends Fragment {

    ExtendedFloatingActionButton mAddPasswordBtn;
    DataViewModel dataViewModel;
    RecyclerView passwordRecyclerView;
    View emptyView;
    PasswordAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_password, container, false);
        mAddPasswordBtn = rootView.findViewById(R.id.add_password_fab_btn);
        passwordRecyclerView = rootView.findViewById(R.id.password_recyclerView);
        emptyView = rootView.findViewById(R.id.empty_view);

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        mAddPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertPasswordActivity.class);
                startActivity(intent);
            }
        });


        dataViewModel.getAllPassword.observe(getViewLifecycleOwner(),passwords -> {
            passwordRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new PasswordAdapter(PasswordFragment.this,passwords);
            passwordRecyclerView.setAdapter(adapter);
            if (passwords.isEmpty()){
                passwordRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                passwordRecyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
}
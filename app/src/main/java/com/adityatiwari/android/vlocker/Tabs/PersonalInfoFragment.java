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

import com.adityatiwari.android.vlocker.Activity.InsertPasswordActivity;
import com.adityatiwari.android.vlocker.Activity.InsertPersonalInfoActivity;
import com.adityatiwari.android.vlocker.Adapter.NotesAdapter;
import com.adityatiwari.android.vlocker.Adapter.PersonalInfoAdapter;
import com.adityatiwari.android.vlocker.DataViewModel;
import com.adityatiwari.android.vlocker.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class PersonalInfoFragment extends Fragment {

    ExtendedFloatingActionButton mAddPersonalInfoBtn;
    DataViewModel dataViewModel;
    View emptyView;
    RecyclerView personalInfoRecyclerView;
    PersonalInfoAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_personal_info, container, false);

        personalInfoRecyclerView = rootView.findViewById(R.id.personal_info_recyclerView);
        emptyView = rootView.findViewById(R.id.empty_view);
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        mAddPersonalInfoBtn = rootView.findViewById(R.id.add_personal_info_fab_btn);

        mAddPersonalInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertPersonalInfoActivity.class);
                startActivity(intent);
            }
        });

        dataViewModel.getAllPersonalInfo.observe(getViewLifecycleOwner(),personalInfos -> {
            personalInfoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new PersonalInfoAdapter(PersonalInfoFragment.this,personalInfos);
            personalInfoRecyclerView.setAdapter(adapter);
            if (personalInfos.isEmpty()){
                personalInfoRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                personalInfoRecyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }
}
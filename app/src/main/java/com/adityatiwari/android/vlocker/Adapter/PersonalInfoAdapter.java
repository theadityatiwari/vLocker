package com.adityatiwari.android.vlocker.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityatiwari.android.vlocker.Activity.UpdateNoteActivity;
import com.adityatiwari.android.vlocker.Activity.UpdatePersonalInfoActivity;
import com.adityatiwari.android.vlocker.Entity.PersonalInfo;
import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.Tabs.PersonalInfoFragment;

import java.util.List;

public class PersonalInfoAdapter extends RecyclerView.Adapter<PersonalInfoAdapter.PersonalInfoViewHolder> {

    PersonalInfoFragment personalInfoFragment;
    List<PersonalInfo> personalInfos;

    public PersonalInfoAdapter (PersonalInfoFragment personalInfoFragment,List<PersonalInfo> personalInfos){
        this.personalInfoFragment = personalInfoFragment;
        this.personalInfos = personalInfos;
    }

    @Override
    public PersonalInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonalInfoViewHolder(LayoutInflater
                .from(personalInfoFragment.getContext())
                .inflate(R.layout.item_personal_info,parent,false));
    }

    @Override
    public void onBindViewHolder( PersonalInfoViewHolder holder, int position) {
        PersonalInfo personalInfo = personalInfos.get(position);

        holder.personName.setText(personalInfo.name);
        holder.contactNo.setText(personalInfo.phone);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personalInfoFragment.getActivity(), UpdatePersonalInfoActivity.class);
                intent.putExtra("id",personalInfo.id);
                intent.putExtra("name",personalInfo.name);
                intent.putExtra("phone",personalInfo.phone);
                intent.putExtra("email",personalInfo.email);
                intent.putExtra("address",personalInfo.address);
                personalInfoFragment.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return personalInfos.size();
    }

    public static class PersonalInfoViewHolder extends RecyclerView.ViewHolder{
        TextView personName, contactNo;
        public PersonalInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.person_name);
            contactNo = itemView.findViewById(R.id.contact_no);
        }
    }
}

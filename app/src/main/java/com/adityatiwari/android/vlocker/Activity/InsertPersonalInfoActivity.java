package com.adityatiwari.android.vlocker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.adityatiwari.android.vlocker.DataViewModel;
import com.adityatiwari.android.vlocker.Entity.Notes;
import com.adityatiwari.android.vlocker.Entity.PersonalInfo;
import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.databinding.ActivityInsertPersonalInfoBinding;

import java.util.Date;

public class InsertPersonalInfoActivity extends AppCompatActivity {


    ActivityInsertPersonalInfoBinding binding;
    String personName, contactNo, email, address;
    DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertPersonalInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);



        binding.donePersonalInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personName = binding.nameEditText.getText().toString();
                contactNo = binding.phoneEditText.getText().toString();
                email = binding.emailEditText.getText().toString();
                address = binding.addressEditText.getText().toString();
                if(TextUtils.isEmpty(binding.nameEditText.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    binding.nameEditText.setError("This field is Mandatory");

                }else{
                    CreatePersonalInfo(personName,contactNo,email,address);
                }
            }
        });
    }

    private void CreatePersonalInfo(String personName, String contactNo, String email, String address) {



        PersonalInfo personalInfo1 = new PersonalInfo();
        personalInfo1.name = personName;
        personalInfo1.phone = contactNo;
        personalInfo1.email = email;
        personalInfo1.address = address;


        dataViewModel.insertPersonalInfos(personalInfo1);

        Toast.makeText(this,"Person Info Added Successfully",Toast.LENGTH_SHORT).show();

        finish();
    }
}
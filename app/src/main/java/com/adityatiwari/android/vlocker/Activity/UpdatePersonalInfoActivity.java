package com.adityatiwari.android.vlocker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adityatiwari.android.vlocker.DataViewModel;
import com.adityatiwari.android.vlocker.Entity.Password;
import com.adityatiwari.android.vlocker.Entity.PersonalInfo;
import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.databinding.ActivityUpdatePersonalInfoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class UpdatePersonalInfoActivity extends AppCompatActivity {


    ActivityUpdatePersonalInfoBinding binding;
    DataViewModel dataViewModel;
    String personName,contactNo,email,address;
    String sPersonName, sContact, sEmail,sAddress;
    int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePersonalInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        iid = getIntent().getIntExtra("id",0);
        sPersonName = getIntent().getStringExtra("name");
        sContact = getIntent().getStringExtra("phone");
        sEmail = getIntent().getStringExtra("email");
        sAddress = getIntent().getStringExtra("address");

        binding.udNameEditText.setText(sPersonName);
        binding.udPhoneEditText.setText(sContact);
        binding.udEmailEditText.setText(sEmail);
        binding.udAddressEditText.setText(sAddress);


        binding.donePersonalInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personName = binding.udNameEditText.getText().toString();
                contactNo = binding.udPhoneEditText.getText().toString();
                email = binding.udEmailEditText.getText().toString();
                address = binding.udAddressEditText.getText().toString();

                if(TextUtils.isEmpty(binding.udNameEditText.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    binding.udNameEditText.setError("This field is Mandatory");

                }else{
                    UpdatePersonalInfo(personName,contactNo,email,address);
                }
            }
        });




    }


    private void UpdatePersonalInfo(String personName, String contactNo, String email, String address) {



        PersonalInfo personalInfo1 = new PersonalInfo();
        personalInfo1.name = personName;
        personalInfo1.phone = contactNo;
        personalInfo1.email = email;
        personalInfo1.address = address;


        dataViewModel.updatePersonalInfos(personalInfo1);

        Toast.makeText(this,"Person Info Updated Successfully",Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_btn){
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdatePersonalInfoActivity.this,R.style.BottomSheetStyle);

            View view = LayoutInflater.from(UpdatePersonalInfoActivity.this)
                    .inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottom_sheet));
            sheetDialog.setContentView(view);
            sheetDialog.show();

            TextView yes, no;
            yes = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataViewModel.deletePersonalInfos(iid);
                    Toast.makeText(getApplicationContext(),"Person Info deleted Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sheetDialog.dismiss();
                }
            });

        }

        return true;
    }
}
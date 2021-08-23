package com.adityatiwari.android.vlocker.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adityatiwari.android.vlocker.DataViewModel;
import com.adityatiwari.android.vlocker.Entity.Password;
import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.databinding.ActivityInsertPasswordBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertPasswordActivity extends AppCompatActivity implements TextWatcher {

    ActivityInsertPasswordBinding binding;
    String website,username,password;
    EditText userNameEditText,passwordEditText;
    DataViewModel dataViewModel;
    ImageView passEyeImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        userNameEditText = (EditText)findViewById(R.id.editTextTextUserName);
        passwordEditText = (EditText)findViewById(R.id.editTextTextPassword);
        passwordEditText.addTextChangedListener(this);

        updatePasswordStrengthView(binding.editTextTextPassword.getText().toString());

        binding.donePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                website = binding.editTextTextWebsite.getText().toString();
                password = binding.editTextTextPassword.getText().toString();
                username = binding.editTextTextUserName.getText().toString();

                if(TextUtils.isEmpty(binding.editTextTextWebsite.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    binding.editTextTextWebsite.setError("This field is mandatory");
//                    Toast.makeText(getApplicationContext(),"Website Section is Mandatory",Toast.LENGTH_SHORT).show();

                }else{
                    CreatePassword(username,password,website);
                }
            }
        });

        binding.usernameCopyTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setClipboard(getApplicationContext(),userNameEditText.getText().toString());
            }
        });

        binding.passwordCopyTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClipboard(getApplicationContext(),passwordEditText.getText().toString());
            }
        });
    }

    private void CreatePassword(String username,String password, String website) {
        Password password1 = new Password();
        password1.website = website;
        password1.password = password;
        password1.username = username;
        dataViewModel.insertPasswords(password1);

        Toast.makeText(this,"Password Added Successfully",Toast.LENGTH_SHORT).show();

        finish();

    }

    private void setClipboard(Context context, String text){
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(),"Copied",Toast.LENGTH_SHORT).show();
    }

    public void ShowHidePass(View view) {

        passEyeImg = (ImageView) findViewById(R.id.show_pass_btn);
        if (view.getId() == R.id.show_pass_btn) {

            if (passwordEditText.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                passEyeImg.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Show Password
                passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                passEyeImg.setImageResource(R.drawable.ic_baseline_visibility_24);

                //Hide Password
                passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
    private void updatePasswordStrengthView(String password) {

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        TextView strengthView = (TextView) findViewById(R.id.strength_level_txt);
        progressBar.setProgress(0);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.not_entered), PorterDuff.Mode.SRC_IN);
        if (password.length() == 0) {
            strengthView.setText("Not Entered");
            strengthView.setTextColor(getResources().getColor(R.color.not_entered));
            progressBar.setProgress(0);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.not_entered), PorterDuff.Mode.SRC_IN);
        } else if (password.length() < 5) {
            strengthView.setText("WEAK");
            strengthView.setTextColor(getResources().getColor(R.color.easy));
            progressBar.setProgress(20);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.easy), PorterDuff.Mode.SRC_IN);
        } else if (password.length() < 8) {
            strengthView.setText("NOT SAFE");
            strengthView.setTextColor(getResources().getColor(R.color.medium));
            progressBar.setProgress(45);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.medium), PorterDuff.Mode.SRC_IN);
        } else if (password.length() < 14) {
            strengthView.setText("SAFE");
            strengthView.setTextColor(getResources().getColor(R.color.strong));
            progressBar.setProgress(70);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.strong), PorterDuff.Mode.SRC_IN);
        } else if(password.length() <19 ) {
            strengthView.setText("SUPER SAFE");
            strengthView.setTextColor(getResources().getColor(R.color.strongest));
            progressBar.setProgress(95);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.strongest), PorterDuff.Mode.SRC_IN);
        }else {
            strengthView.setText("Password Max Length Reached");
            strengthView.setTextColor(getResources().getColor(R.color.max_limit));
            progressBar.setProgress(100);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.max_limit), PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        updatePasswordStrengthView(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
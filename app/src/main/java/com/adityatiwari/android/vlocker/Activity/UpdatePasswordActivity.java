package com.adityatiwari.android.vlocker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adityatiwari.android.vlocker.DataViewModel;
import com.adityatiwari.android.vlocker.Entity.Password;
import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.databinding.ActivityUpdatePasswordBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class UpdatePasswordActivity extends AppCompatActivity implements TextWatcher {

    ActivityUpdatePasswordBinding binding;
    TextView userNameText,passwordText;
    String website,username,password;
    String sUsername, sPassword, sWebsite;
    int iid;
    EditText userNameEditText,passwordEditText;
    DataViewModel dataViewModel;
    ImageView passEyeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userNameText = (TextView)findViewById(R.id.usernameCopyTextview);
        passwordText = (TextView)findViewById(R.id.passwordCopyTextview);



        iid = getIntent().getIntExtra("id",0);
        sUsername = getIntent().getStringExtra("username");
        sPassword = getIntent().getStringExtra("password");
        sWebsite = getIntent().getStringExtra("website");
        updatePasswordStrengthView(sPassword);

        binding.udPasswordToggleChar.setText(String.valueOf(sWebsite.charAt(0)));
        binding.udPasswordHeaderWebsiteTxt.setText(sWebsite);
        binding.udEditTextTextUserName.setText(sUsername);
        binding.udEditTextTextPassword.setText(sPassword);
        binding.udEditTextTextWebsite.setText(sWebsite);
        binding.udEditTextTextPassword.addTextChangedListener(this);




        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        //these two are clickable copy textview
        userNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClipboard(getApplicationContext(),binding.udEditTextTextUserName.getText().toString());
            }
        });

        passwordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClipboard(getApplicationContext(),binding.udEditTextTextPassword.getText().toString());
            }
        });

        binding.udPasswordFabBtn.setOnClickListener(v -> {
            website = binding.udEditTextTextWebsite.getText().toString();
            password = binding.udEditTextTextPassword.getText().toString();
            username = binding.udEditTextTextUserName.getText().toString();


            if(TextUtils.isEmpty(binding.udEditTextTextWebsite.getText())){
                /**
                 *   You can Toast a message here that the Username is Empty
                 **/
                binding.udEditTextTextWebsite.setError("This field is mandatory");
//                    Toast.makeText(getApplicationContext(),"Website Section is Mandatory",Toast.LENGTH_SHORT).show();

            }else{
                UpdatePassword(username,password,website);
            }
        });

    }

    private void UpdatePassword(String username,String password, String website) {
        Password password1 = new Password();
        password1.id = iid;
        password1.website = website;
        password1.password = password;
        password1.username = username;
        dataViewModel.updatePasswords(password1);

        Toast.makeText(this,"Password Updated Successfully",Toast.LENGTH_SHORT).show();

        finish();

    }

    private void setClipboard(Context context, String text){
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(),"Copied",Toast.LENGTH_SHORT).show();
    }

    public void ShowHidePass(View view) {

        if (view.getId() == R.id.show_pass_btn) {

            if (binding.udEditTextTextPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                binding.showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Show Password
                binding.udEditTextTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                binding.showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24);

                //Hide Password
                binding.udEditTextTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_btn){
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdatePasswordActivity.this,R.style.BottomSheetStyle);

            View view = LayoutInflater.from(UpdatePasswordActivity.this)
                    .inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottom_sheet));
            sheetDialog.setContentView(view);
            sheetDialog.show();

            TextView yes, no;
            yes = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataViewModel.deletePasswords(iid);
                    Toast.makeText(getApplicationContext(),"Password deleted Successfully",Toast.LENGTH_SHORT).show();
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        updatePasswordStrengthView(s.toString());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        updatePasswordStrengthView(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
        updatePasswordStrengthView(s.toString());
    }
}
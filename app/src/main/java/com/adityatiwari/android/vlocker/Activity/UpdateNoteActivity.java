package com.adityatiwari.android.vlocker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adityatiwari.android.vlocker.DataViewModel;
import com.adityatiwari.android.vlocker.Entity.Notes;
import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.databinding.ActivityUpdateNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {


    ActivityUpdateNoteBinding binding;
    DataViewModel dataViewModel;
    String priority = "1";
    String sTitle, sSubtitle, sPriority, sNotes;
    int iid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        iid = getIntent().getIntExtra("id",0);
        sTitle = getIntent().getStringExtra("title");
        sSubtitle = getIntent().getStringExtra("subtitle");
        sNotes = getIntent().getStringExtra("notes");
        sPriority = getIntent().getStringExtra("priority");

        binding.udTitleEdtTxt.setText(sTitle);
        binding.udSubtitleEdtTxt.setText(sSubtitle);
        binding.udNoteEdtTxt.setText(sNotes);

        switch (sPriority) {
            case "1":
                binding.redCircle.setImageResource(R.drawable.ic_baseline_done_24);
                break;
            case "2":
                binding.yellowCircle.setImageResource(R.drawable.ic_baseline_done_24);
                break;
            case "3":
                binding.greenCircle.setImageResource(R.drawable.ic_baseline_done_24);
                break;
        }




        binding.greenCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.greenCircle.setImageResource(R.drawable.ic_baseline_done_24);
                binding.yellowCircle.setImageResource(0);
                binding.redCircle.setImageResource(0);
                priority = "3";
            }
        });

        binding.yellowCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.greenCircle.setImageResource(0);
                binding.yellowCircle.setImageResource(R.drawable.ic_baseline_done_24);
                binding.redCircle.setImageResource(0);
                priority = "2";
            }
        });

        binding.redCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.greenCircle.setImageResource(0);
                binding.yellowCircle.setImageResource(0);
                binding.redCircle.setImageResource(R.drawable.ic_baseline_done_24);
                priority = "1";
            }
        });

        binding.udDoneNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.udTitleEdtTxt.getText().toString();
                String subtitle = binding.udSubtitleEdtTxt.getText().toString();
                String notes = binding.udNoteEdtTxt.getText().toString();



                if(TextUtils.isEmpty(binding.udTitleEdtTxt.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    binding.udTitleEdtTxt.setError("This field is Mandatory");

                }else{
                    UpdateNotes(title,subtitle,notes);
                }
            }
        });
    }

    private void UpdateNotes(String title, String subtitle, String notes) {


        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());


        Notes notes2 = new Notes();
        notes2.id = iid;
        notes2.notesTitle = title;
        notes2.notesSubtitle = subtitle;
        notes2.notes = notes;
        notes2.notesDate = sequence.toString();
        notes2.notesPriority = priority;
        dataViewModel.updateNote(notes2);

        Toast.makeText(this,"Notes Updated Successfully",Toast.LENGTH_SHORT).show();

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
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNoteActivity.this,R.style.BottomSheetStyle);

            View view = LayoutInflater.from(UpdateNoteActivity.this)
                    .inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottom_sheet));
            sheetDialog.setContentView(view);
            sheetDialog.show();

            TextView yes, no;
            yes = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataViewModel.deleteNote(iid);
                    Toast.makeText(getApplicationContext(),"Notes deleted Successfully",Toast.LENGTH_SHORT).show();
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
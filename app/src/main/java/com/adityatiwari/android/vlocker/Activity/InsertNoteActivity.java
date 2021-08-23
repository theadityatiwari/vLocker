package com.adityatiwari.android.vlocker.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.adityatiwari.android.vlocker.DataViewModel;
import com.adityatiwari.android.vlocker.Entity.Notes;
import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.databinding.ActivityInsertNoteBinding;

import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {

   ActivityInsertNoteBinding binding;
   String title, subtitle, notes;
   DataViewModel dataViewModel;
   String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

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


        binding.doneNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = binding.titleEditText.getText().toString();
                subtitle = binding.subtitleEditText.getText().toString();
                notes = binding.noteEditText.getText().toString();

                if(TextUtils.isEmpty(binding.titleEditText.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    binding.titleEditText.setError("This field is Mandatory");


                }else{
                    CreateNotes(title,subtitle,notes);
                }



            }
        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {


        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());


        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesDate = sequence.toString();
        notes1.notesPriority = priority;
        dataViewModel.insertNote(notes1);

        Toast.makeText(this,"Notes Created Successfully",Toast.LENGTH_SHORT).show();

        finish();
    }
}
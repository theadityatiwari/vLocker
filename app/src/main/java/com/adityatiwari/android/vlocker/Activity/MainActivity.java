package com.adityatiwari.android.vlocker.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.Tabs.FragmentAdapter;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    // Use the FloatingActionButton for all the add person
    // and add alarm
    FloatingActionButton mPersonalInfo, mAddNote, mPasswordFab;

    // Use the ExtendedFloatingActionButton to handle the
    // parent FAB
    ExtendedFloatingActionButton mAddFab;

    // These TextViews are taken to make visible and
    // invisible along with FABs except parent FAB's action
    // name
    TextView addPersonInfoTextView, addNotesActionTextView, addPasswordActionText;

    // to check whether sub FABs are visible or not
    Boolean isAllFabsVisible;
    com.google.android.material.tabs.TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_password));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_notes));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_personal_info));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                               @Override
                                               public void onTabSelected(TabLayout.Tab tab) {
                                                   pager2.setCurrentItem(tab.getPosition());
                                               }

                                               @Override
                                               public void onTabUnselected(TabLayout.Tab tab) {

                                               }

                                               @Override
                                               public void onTabReselected(TabLayout.Tab tab) {

                                               }
        });


                pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        tabLayout.selectTab(tabLayout.getTabAt(position));
                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dots_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent rateIntent,sharingIntent,privacyPolicyIntent;

        switch (item.getItemId()){

            case R.id.rate_app:
                String url = "https://play.google.com/store/apps/details?id=com.adityatiwari.android.vlocker&hl=en";
                rateIntent = new Intent(Intent.ACTION_VIEW);
                rateIntent.setData(Uri.parse(url));
                startActivity(rateIntent);
                break;

            case R.id.share:
                sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareSubText = "Explore Bhopal with BHOPAL TRAVEL GUIDE";
                String shareBodyText = "https://play.google.com/store/apps/details?id=com.adityatiwari.android.vlocker&hl=en";
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubText);
                startActivity(Intent.createChooser(sharingIntent, "Share Using"));
                break;

            case R.id.privacy_policy:
                String url2 = "https://bhopaltravelguide.blogspot.com/2021/07/vlocker-privacy-policy.html";
                privacyPolicyIntent = new Intent(Intent.ACTION_VIEW);
                privacyPolicyIntent.setData(Uri.parse(url2));
                startActivity(privacyPolicyIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
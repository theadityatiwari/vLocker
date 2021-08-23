package com.adityatiwari.android.vlocker.Tabs;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter{

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                PasswordFragment tab1 = new PasswordFragment();
                return tab1;
            case 1:
                NotesFragment tab2 = new NotesFragment();
                return tab2;
            case 2:
                PersonalInfoFragment tab3 = new PersonalInfoFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


//    int mNumOfTabs;
//    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
//        super(fm);
//        this.mNumOfTabs = NumOfTabs;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                AllItemFragment tab1 = new AllItemFragment();
//                return tab1;
//            case 1:
//                PasswordFragment tab2 = new PasswordFragment();
//                return tab2;
//            case 2:
//                NotesFragment tab3 = new NotesFragment();
//                return tab3;
//            case 3:
//                PersonalInfoFragment tab4 = new PersonalInfoFragment();
//                return tab4;
//            default:
//                return null;
//    }
//
//    @Override
//    public int getCount() {
//        return mNumOfTabs;
//    }
}


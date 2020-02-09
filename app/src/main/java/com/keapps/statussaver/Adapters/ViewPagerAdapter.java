package com.keapps.statussaver.Adapters;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.keapps.statussaver.Fragments.ImageFragment;
import com.keapps.statussaver.Fragments.SavedFragment;
import com.keapps.statussaver.Fragments.VideoFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private VideoFragment videoFragment;
    private ImageFragment imageFragment;
    private SavedFragment savedFragment;


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        imageFragment= new ImageFragment();
        videoFragment = new VideoFragment();
        savedFragment= new SavedFragment();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0){
            return imageFragment;
        }
        else if ( position == 1) {
            return videoFragment;
        }
        else
            return savedFragment;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0){
            return "Images";

        }
        else if (position == 1){

        return "Videos";}
        else return  "Saved";
    }

    @Override
    public int getCount() {
        return 3;
    }
}

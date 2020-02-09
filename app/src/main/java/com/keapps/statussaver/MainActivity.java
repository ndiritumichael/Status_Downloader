package com.keapps.statussaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.keapps.statussaver.Adapters.ViewPagerAdapter;
import com.keapps.statussaver.Fragments.ImageFragment;
import com.keapps.statussaver.Fragments.PictureView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ImageFragment.OnSendMessageListener {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.frameView)
    FrameLayout frameLayout;
    @BindView(R.id.appBar)
    AppBarLayout appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onMessageSend(String filePath) {
        Fragment myFragment = new PictureView();
        Bundle bundle = new Bundle();
        bundle.putString("key", filePath);
        myFragment.setArguments(bundle);
        appBarLayout.setVisibility(View.INVISIBLE);

        getSupportFragmentManager().beginTransaction().add(R.id.frameView, myFragment).addToBackStack("null").commit();
    }
}

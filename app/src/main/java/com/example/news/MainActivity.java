package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem mHome,mHealth,mSports,mScience,mTechnology,mEntertainment;
    PagerAdapter pagerAdapter;
    Toolbar mtoolBar;

    String api = "a6fe1a51b43744a4a56140af39bb55cc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtoolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolBar);

        mHome = findViewById(R.id.home);
        mHealth = findViewById(R.id.health);
        mSports = findViewById(R.id.sports);
        mTechnology = findViewById(R.id.technology);
        mEntertainment = findViewById(R.id.entertainment);

        ViewPager viewPager = findViewById(R.id.fragmentContainer);
        tabLayout = findViewById(R.id.include);

        pagerAdapter = new PageAdapter(getSupportFragmentManager(),6);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0 || tab.getPosition()==1 || tab.getPosition()==2 || tab.getPosition()==3 || tab.getPosition()==4 || tab.getPosition()==5){
                        pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        deleteCache(this);
    }
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
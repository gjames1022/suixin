package com.example.administrator.broadcastbestpractice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;
    private MusicsFragment mMusicsFragment;
    private SchedulesFragment mSchedulesFragment;
    private FavirotesFragment mFavirotesFragment;
    private int mCurrentItem = 0;
    private  Toolbar mToolbarTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        mToolbarTb = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbarTb);//隐藏actionBar
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu_layout,menu);
        return true;
    }


    private void initView(){
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mFavirotesFragment = new FavirotesFragment();
        mMusicsFragment = new MusicsFragment();
        mSchedulesFragment = new SchedulesFragment();
        List<Fragment> _fragments = new ArrayList<>();
        _fragments.add(mFavirotesFragment);
        _fragments.add(mSchedulesFragment);
        _fragments.add(mMusicsFragment);
        mViewPager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(),_fragments));
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData(){

        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                if (mCurrentItem != 0){
                                    mViewPager.setCurrentItem(0);
                                    mCurrentItem = 0;
                                }
                                break;
                            case R.id.action_schedules:
                                if (mCurrentItem != 1){
                                    mViewPager.setCurrentItem(1);
                                    mCurrentItem = 1;
                                }
                                break;
                            case R.id.action_music:
                                if (mCurrentItem != 2){
                                    mViewPager.setCurrentItem(2);
                                    mCurrentItem = 2;
                                }
                                break;
                            default:
                                if (mCurrentItem != 2){
                                    mViewPager.setCurrentItem(2);
                                    mCurrentItem = 2;
                                }
                                break;
                        }
                        return false;
                    }
                });
    }

    public class FragmentsPagerAdapter extends FragmentPagerAdapter{

        private FragmentManager mFragmentManager;
        private List<Fragment> mFragments;
        public FragmentsPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            mFragmentManager = fm;
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}

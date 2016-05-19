package com.personal.cyj.activivity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.personal.cyj.adapter.PagerAdapter;
import com.personal.cyj.cyjapplication.R;
import com.personal.cyj.fragment.HomeFragment;
import com.personal.cyj.fragment.ThreeFragment;
import com.personal.cyj.fragment.TwoFragment;
import com.personal.cyj.view.MyRadioButton;
import com.personal.cyj.viewgroup.DragLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyRadioButton mRadioHome,mRadioFind,mRadioMy;
    private RadioGroup mGroup;
    private ViewPager pager;
    private  ArrayList<Fragment> list;
    private DragLayout mDrayLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        boundListener();
    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
        mGroup = (RadioGroup) findViewById(R.id.group);
        mRadioHome = (MyRadioButton) findViewById(R.id.radio_home);
        mRadioFind = (MyRadioButton) findViewById(R.id.radio_find);
        mRadioMy = (MyRadioButton) findViewById(R.id.radio_my);
        mDrayLayout = (DragLayout) findViewById(R.id.draglayout);
    }
    private void initData(){
        list = new ArrayList<Fragment>();
        list.add(new HomeFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());
    }

    private void boundListener() {
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_home:
                        checkItem(0);
                        pager.setCurrentItem(0);
                        break;
                    case R.id.radio_find:
                        checkItem(1);
                        pager.setCurrentItem(1);
                        break;
                    case R.id.radio_my:
                        checkItem(2);
                        pager.setCurrentItem(2);
                        break;
                }
            }
        });

        pager.setAdapter(new PagerAdapter(getSupportFragmentManager(), list));
        pager.setCurrentItem(0);
        mRadioHome.isCheck(true);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                checkItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void checkItem(int position){
        initRadioState();
        switch (position) {
            case 0:
                mRadioHome.isCheck(true);
                mDrayLayout.isSliding(true);
                break;
            case 1:
                mRadioFind.isCheck(true);
                mDrayLayout.isSliding(false);
                break;
            case 2:
                mRadioMy.isCheck(true);
                mDrayLayout.isSliding(false);
                break;
        }
    }

    private void initRadioState(){
        mRadioHome.isCheck(false);
        mRadioFind.isCheck(false);
        mRadioMy.isCheck(false);
    }
}

package com.personal.cyj.cyjapplication;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.personal.cyj.adapter.PagerAdapter;
import com.personal.cyj.fragment.HomeFragment;
import com.personal.cyj.fragment.ThreeFragment;
import com.personal.cyj.fragment.TwoFragment;
import com.personal.cyj.view.MyRadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyRadioButton mRadioHome,mRadioFind,mRadioMy;
    RadioGroup mGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mGroup = (RadioGroup) findViewById(R.id.group);
        mRadioHome = (MyRadioButton) findViewById(R.id.radio_home);
        mRadioFind = (MyRadioButton) findViewById(R.id.radio_find);
        mRadioMy = (MyRadioButton) findViewById(R.id.radio_my);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        list.add(new HomeFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());

        pager.setAdapter(new PagerAdapter(getSupportFragmentManager(), list));
        pager.setCurrentItem(0);
        mRadioHome.isCheck(true);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initRadioState();
                switch (position) {
                    case 0:
                        mRadioHome.isCheck(true);
                        break;
                    case 1:
                        mRadioFind.isCheck(true);
                        break;
                    case 2:
                        mRadioMy.isCheck(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initRadioState(){
        mRadioHome.isCheck(false);
        mRadioFind.isCheck(false);
        mRadioMy.isCheck(false);
    }
}

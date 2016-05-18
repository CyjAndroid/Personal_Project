package com.personal.cyj.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.personal.cyj.cyjapplication.R;

/**
 * Created by Cyj on 2016/5/18.
 */
public class ThreeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        TextView t = (TextView)v.findViewById(R.id.home_text);
        t.setText("three");
        return v;
    }
}

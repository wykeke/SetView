package com.example.setview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RingChart ringChart;
    List<ViewData> viewDataList = new ArrayList<>();
    SetViewDemo setViewDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView imageView = findViewById(R.id.imgView);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout);
        setViewDemo = new SetViewDemo(this);
        frameLayout.addView(setViewDemo);
        ObjectAnimator animator = ObjectAnimator.ofInt(setViewDemo,"color",0xffff0000,0xff00ff00);
        animator.setEvaluator(new ArgbEvaluator()); //主动设置为ArgbEvaluator
        animator.setDuration(5000);
        animator.start();
//        ringChart = new RingChart(this);
//        frameLayout.addView(ringChart);
//
//        initData();
//        ringChart.setData(viewDataList);
    }

    public void initData(){
        viewDataList.add(new ViewData("饮食",800));
        viewDataList.add(new ViewData("出行",300));
        viewDataList.add(new ViewData("购物",500));
        viewDataList.add(new ViewData("娱乐",200));
        viewDataList.add(new ViewData("住房",200));
        viewDataList.add(new ViewData("其他",100));
    }
}
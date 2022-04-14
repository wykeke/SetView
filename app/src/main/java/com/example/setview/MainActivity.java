package com.example.setview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RingChart ringChart;
    List<ViewData> viewDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout);
        ringChart = new RingChart(this);
        frameLayout.addView(ringChart);

        initData();
        ringChart.setData(viewDataList);
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
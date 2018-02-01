package com.example.administrator.myapplication_zhexian;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private WeatherChartView chartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new WeatherChartView(this).setTempDay(new int[]{100,200});
        chartView = (WeatherChartView) findViewById(R.id.line_char);
        // 设置白天温度曲线
        chartView.setTempDay(new int[]{10,20,10,30,10,15});
        // 设置夜间温度曲线
        chartView .setTempNight(new int[]{5,10,10,20,10,15});
        chartView .invalidate();
    }

}

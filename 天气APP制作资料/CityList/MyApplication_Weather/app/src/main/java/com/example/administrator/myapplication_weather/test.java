package com.example.administrator.myapplication_weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class test extends Activity {

    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mInflater = LayoutInflater.from(this);
        initData();
        initView();
    }

    private void initData()
    {
        mImgIds = new int[] {
                R.drawable.title_city,
                R.drawable.title_city,
                R.drawable.title_city,
                R.drawable.title_city,
                R.drawable.title_city
        };
    }

    private void initView()
    {
        mGallery = (LinearLayout) findViewById(R.id.id_gallery);

        for (int i = 0; i < mImgIds.length; i++)
        {

            View view = mInflater.inflate(R.layout.activity_index_gallery_item, mGallery, false);
            ImageView img = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
            img.setImageResource(mImgIds[i]);
            TextView txt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);
            txt.setText("some info ");
            mGallery.addView(view);
        }
    }

}

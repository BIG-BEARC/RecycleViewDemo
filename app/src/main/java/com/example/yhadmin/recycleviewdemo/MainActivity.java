package com.example.yhadmin.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.example.yhadmin.recycleviewdemo.list.CommonAdapter;
import com.example.yhadmin.recycleviewdemo.list.ViewHolder;
import com.example.yhadmin.recycleviewdemo.modle.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
{

    private RecyclerView mRecycleView;
    private List<String> data = new ArrayList<String>(Arrays.asList("Hello",
                                                                      "World", "Welcome"));

    private List<Bean> mDatas = new ArrayList<>();
    private View mEmptyView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = ((ListView) findViewById(R.id.id_listview_list));
        mEmptyView = findViewById(R.id.id_empty_view);
        mListView.setAdapter(new CommonAdapter<String>(this,data,R.layout.my_item) {
            @Override
            public void convert(ViewHolder viewHolder, String item) {
                viewHolder.setText(R.id.id_tv_title,item);
            }
        });
    }
}

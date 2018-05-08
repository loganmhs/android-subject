package com.example.mhs.memorandum;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();

        Fruit durian=new Fruit("durian","榴莲");
        durian.save();
    }
}
//实现了fragment中使用recyclerView,下一步实现recyclerView的点击
//实现了recyclerView的点击监听，和碎片之间的通信
//不知道是否连接上了数据库，有待检测
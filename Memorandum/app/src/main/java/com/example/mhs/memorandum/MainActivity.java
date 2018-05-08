package com.example.mhs.memorandum;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.function,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Add:
                Toast.makeText(this,"add affairs?",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                startActivity(intent);
                break;
                default:
        }
        return true;
    }
}
//实现了fragment中使用recyclerView,下一步实现recyclerView的点击
//实现了recyclerView的点击监听，和碎片之间的通信
//不知道是否连接上了数据库，有待检测
//将程序中的水果意义的代码改成备忘录意义的
//修改后的代码意义变了，全部为备忘录意义，并且代码执行无误，接下来实现增删改查功能。
//实现了往数据库中添项，接下来实现删，改，查。改，横屏后对焦点处的项编辑；删，去另一个活动，另一个activity用复选框实现删除，查，对全文实现模糊查询。
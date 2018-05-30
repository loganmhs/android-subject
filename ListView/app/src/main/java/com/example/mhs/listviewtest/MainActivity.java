package com.example.mhs.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Student>studentList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStudents();
        StudentAdapter adapter=new StudentAdapter(MainActivity.this,R.layout.student_item,studentList);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initStudents(){
        for(int i=0;i<10;i++){
            Student zhao=new Student("zhao","1505","2015000300","male","10010");
            studentList.add(zhao);
            Student qian=new Student("qian","1505","2015000301","female","10011");
            studentList.add(qian);
            Student sun=new Student("sun","1506","2015000302","male","10012");
            studentList.add(sun);
            Student li=new Student("li","1506","2015000303","female","10013");
            studentList.add(li);
        }
    }
}

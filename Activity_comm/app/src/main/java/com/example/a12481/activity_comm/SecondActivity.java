package com.example.a12481.activity_comm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Button button1,button2;
        button1 =(Button)findViewById(R.id.button1);
        button2 =(Button)findViewById(R.id.button2);
        final EditText text1 = (EditText)findViewById(R.id.info);
        Intent intent = getIntent();
        final String data = intent.getStringExtra("1to2");

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text1.setText(data);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SecondActivity.this,MainActivity.class);
                intent.putExtra("2to1",text1.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}

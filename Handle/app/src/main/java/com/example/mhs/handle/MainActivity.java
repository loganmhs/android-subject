package com.example.mhs.handle;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final  int UPDATE_TEXT=1;
    private TextView textView;
    private Button button;
    private Button button1;

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){

        public void handleMessage(Message message){
            switch (message.what){
                case UPDATE_TEXT:
                    textView.setText("Nice to meet you!");
                    break;
                case 2:
                    textView.setText("How are you?");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        textView=(TextView)findViewById(R.id.Text);
        button=(Button)findViewById(R.id.change);
        button1=(Button)findViewById(R.id.change1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=2;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }


}

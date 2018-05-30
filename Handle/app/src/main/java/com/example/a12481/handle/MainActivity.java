package com.example.a12481.handle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.view.textclassifier.TextClassification;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView,TextView1,TextView2,TextView3;
    private Handler mHandlerTest1;
    private Handler mHandlerTest2;
    private int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        mTextView = (TextView) findViewById(R.id.textView);
        TextView1 = (TextView) findViewById(R.id.textView2);
        TextView2 = (TextView) findViewById(R.id.textView3);
        TextView3 = (TextView) findViewById(R.id.textView4);
        //1 子线程发送消息给本身
        new Thread(){
        public void run(){
                Looper.prepare();
                mHandlerTest1=new HandlerTest1(Looper.myLooper());
                Message message = new Message();
                message.obj = "子线程发送的消息Hi~Hi";
                mHandlerTest1.sendMessage(message);
                Looper.loop();
            };
        }.start();

    }

    private class HandlerTest1 extends Handler {

        private HandlerTest1(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("子线程收到:" + msg.obj);
            //2  收到消息后可再发消息到主线程
            mHandlerTest2=new HandlerTest2(getMainLooper());
            Message message = new Message();
            message.obj = "O(∩_∩)O";
            mHandlerTest2.sendMessage(message);
            TextView3.setText("发送给主线程的内容:" + message.obj);
            TextView2.setText("收到主线程的内容:" + msg.obj);
        }
    }

    private class HandlerTest2 extends Handler {

        private HandlerTest2(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText("在主线程中,收到子线程发来消息:" + msg.obj);

            //3  收到消息后再发消息到子线程
            if (counter==0) {
                Message message = new Message();
                message.obj = "主线程发送的消息Xi~Xi";
                mHandlerTest1.sendMessage(message);
                counter++;
                TextView1.setText("主线程发送的消息:" + message.obj);
            }

        }
    }

}

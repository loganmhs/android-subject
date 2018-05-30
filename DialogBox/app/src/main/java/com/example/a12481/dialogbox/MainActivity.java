package com.example.a12481.dialogbox;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button, button1;
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(this);
        button1 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button3:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("输入UserId和Password");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                dialog.show();
                break;
            case R.id.button4:

                final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_dialog_custom,null);
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                dialog1.setView(view);

                dialog1.setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText text1 = (EditText)view.findViewById(R.id.text_name);
                        EditText text2 = (EditText)view.findViewById(R.id.text_password);
                        String string1=text1.getText().toString();
                        String string2 =text2.getText().toString();
                        cheak(string1,string2);

                    }
                });

                dialog1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog1.show();

                break;
                default:
                    break;
        }
    }
    public void cheak(String user,String paw){
        String s1 = new String("abc");
        String s2 = new String("123");
        if(user.equals(s1)==true&&paw.equals(s2)==true)
        {
            Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
        }
    }
}

//Toast.makeText(context, "things happened", Toast.LENGTH_SHORT).show();
package com.example.mhs.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;


public class EditActivity extends AppCompatActivity {//需不需要继承自这个？
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        final EditText editText1=(EditText)findViewById(R.id.titleInfo);
        final EditText editText2=(EditText)findViewById(R.id.contentInfo);
        editText2.setOnKeyListener(new View.OnKeyListener() {
           @Override
           public boolean onKey(View v, int keyCode, KeyEvent event) {
               if(keyCode==event.KEYCODE_ENTER){
                   String []infos=new String[2];
                   infos[0]=editText1.getText().toString().trim();
                   infos[1]=editText2.getText().toString().trim();

                   Summary summary=new Summary(infos[0],infos[1]);
                   summary.save();
                   Intent intent=new Intent(EditActivity.this,MainActivity.class);
                   startActivity(intent);
               }
               return false;
           }
       });
    }
}

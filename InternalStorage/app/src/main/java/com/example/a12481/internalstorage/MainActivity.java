package com.example.a12481.internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText text1,text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1,button2;
        button1 =(Button)findViewById(R.id.button1);
        text1 = (EditText)findViewById(R.id.info);
        text2 = (EditText)findViewById(R.id.info2);
        String str1 = text1.getText().toString();
        String str2 = load();
        if(!TextUtils.isEmpty(str2)){
            text2.setText(str2);
            text2.setSelection(str2.length());
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show();
        }
        saveText(str1);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText = text1.getText().toString();
        saveText(inputText);
    }
    public void saveText(String text1) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data",Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(text1);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(writer !=null)
                {
                    writer.close();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine())!=null){
                content.append(line);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(reader !=null){
                try{
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return  content.toString();
    }
}

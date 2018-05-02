package com.example.mhs.caculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Conversion extends AppCompatActivity {
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Intent intent=new Intent(Conversion.this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion);
        ActivityCollector.addActivity(this);

        final EditText editText1=(EditText)findViewById(R.id.seconds);//定义编辑框
        final EditText editText2=(EditText)findViewById(R.id.hours);//定义编辑框

        final EditText editText3=(EditText)findViewById(R.id.input);//定义编辑框
        final EditText editText4=(EditText)findViewById(R.id.output);//定义编辑框


        final RadioGroup rgIn=(RadioGroup)findViewById(R.id.inputRgroup);
        final RadioGroup rgOut=(RadioGroup)findViewById(R.id.outputRgroup);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str;
                Double num;
                str=String.valueOf(s);
                if(str.isEmpty())
                    num=0.0;
                else
                    num=Double.valueOf(str);
                num=num/3600;
                editText2.setText(num.toString());
            }
        });

        editText3.addTextChangedListener(new TextWatcher() {//为单选框按钮设置监听器不好使，不知道为什么，只能对编辑框设置监听器
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int inputID=rgIn.getCheckedRadioButtonId();
                int outputID=rgOut.getCheckedRadioButtonId();
                int num;//存放十进制数字
                try{
                    switch (inputID){
                        case R.id.decimal:
                            num=Integer.valueOf(editText3.getText().toString());
                            switch (outputID){
                                case R.id.decimal1:
                                    editText4.setText(editText3.getText().toString());
                                    break;
                                case R.id.binary1:
                                    editText4.setText(Integer.toBinaryString(num));
                                    break;
                                case R.id.octonary1:
                                    editText4.setText(Integer.toOctalString(num));
                                    break;
                                case R.id.hex1:
                                    editText4.setText(Integer.toHexString(num));
                                    break;
                                default:
                            }
                            break;
                        case R.id.binary:
                            String bin=editText3.getText().toString();
                            num=Integer.valueOf(bin,2);
                            switch (outputID){
                                case R.id.decimal1:
                                    editText4.setText(String.valueOf(num));
                                    break;
                                case R.id.binary1:
                                    editText4.setText(bin);
                                    break;
                                case R.id.octonary1:
                                    editText4.setText(Integer.toOctalString(num));
                                    break;
                                case R.id.hex1:
                                    editText4.setText(Integer.toHexString(num));
                                    break;
                                default:
                            }
                            break;
                        case R.id.octonary:
                            String oct=editText3.getText().toString();
                            num=Integer.valueOf(oct,8);
                            switch (outputID){
                                case R.id.decimal1:
                                    editText4.setText(String.valueOf(num));
                                    break;
                                case R.id.binary1:
                                    editText4.setText(Integer.toBinaryString(num));
                                    break;
                                case R.id.octonary1:
                                    editText4.setText(oct);
                                    break;
                                case R.id.hex1:
                                    editText4.setText(Integer.toHexString(num));
                                    break;
                                default:
                            }
                            break;
                        case R.id.hex:
                            String Hex=editText3.getText().toString();
                            num=Integer.valueOf(Hex,16);
                            switch (outputID){
                                case R.id.decimal1:
                                    editText4.setText(String.valueOf(num));
                                    break;
                                case R.id.binary1:
                                    editText4.setText(Integer.toBinaryString(num));
                                    break;
                                case R.id.octonary1:
                                    editText4.setText(Integer.toOctalString(num));
                                    break;
                                case R.id.hex1:
                                    editText4.setText(Hex);
                                    break;
                                default:
                            }
                            break;
                        default:
                    }
                }catch (Exception e){
                    Toast.makeText(Conversion.this,"error!input again",Toast.LENGTH_SHORT).show();
                    editText4.setText("");
                    return;
                }

            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}

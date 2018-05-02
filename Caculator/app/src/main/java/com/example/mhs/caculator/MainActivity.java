package com.example.mhs.caculator;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.function.DoubleUnaryOperator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ui);
        ActivityCollector.addActivity(this);

        int [] name=new int[]{R.id.leftBracket,R.id.rightBracket,R.id.powerSym,R.id.divide,
                R.id.mutiply,R.id.seven,R.id.eight,R.id.nine, R.id.substract,R.id.four,R.id.five,
                R.id.six,R.id.add,R.id.one,R.id.two, R.id.three,R.id.percent,R.id.zero,R.id.dot,
                R.id.sin,R.id.cos,R.id.C,R.id.Delete,R.id.equal};//24个按钮ID，其中前19个点击立即显示按钮字符
        Button []key=new Button[24];//声明24个按钮，前19个同上
        final EditText editText1=(EditText)findViewById(R.id.showpan1);//定义编辑框

        int i=0;
        for(i=0;i<24;i++){//定义24个按钮
            key[i]=(Button)findViewById(name[i]);
        }

        for(i=0;i<19;i++) {//为前19个按钮注册监听器
            if(i==16)continue;;//跳过百分号和幂次运算符的监听器注册
            key[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(editText1.getText().toString().trim().equals("0")){//滤掉初始0
                        editText1.setText("");
                    }
                    String text;
                    text=editText1.getText().toString();
                    if(((Button)view).getText().toString().trim().equals("x^y")){
                        text+="^";
                    }
                    else {
                        text += ((Button) view).getText().toString();//将当前view强制转换成button
                    }
                    editText1.setText(text);
                }
            });
        }
        key[16].setOnClickListener(new View.OnClickListener() {//为百分号注册监听器
            @Override
            public void onClick(View v) {
                String str=editText1.getText().toString();
                int i=0;
                try{
                    for(i=str.length()-1;i>=0;i--){
                        if(!(str.charAt(i)>='0'&&str.charAt(i)<='9'||str.charAt(i)=='.'))
                            break;
                    }
                    Double num=Double.valueOf(str.substring(i+1,str.length()));
                    num/=100;
                    //if(i==-1)i=0;
                    str=str.substring(0,i+1);
                    str+=num.toString();
                    editText1.setText(str);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"error！input again",Toast.LENGTH_SHORT).show();
                    editText1.setText("0");
                }
            }
        });

        key[19].setOnClickListener(new View.OnClickListener() {//为sin函数注册监听器
            @Override
            public void onClick(View v) {
                String str=editText1.getText().toString();
                int i=0;
                try{
                    for(i=str.length()-1;i>=0;i--){
                        if(!(str.charAt(i)>='0'&&str.charAt(i)<='9'||str.charAt(i)=='.'))
                            break;
                    }
                    Double num=Double.valueOf(str.substring(i+1,str.length()));
                    num=Math.sin(Math.toRadians(num));
                    str=str.substring(0,i+1);
                    str+=num.toString();
                    editText1.setText(str);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"error！input again",Toast.LENGTH_SHORT).show();
                    editText1.setText("0");
                }
            }
        });

        key[20].setOnClickListener(new View.OnClickListener() {//为cos函数注册监听器
            @Override
            public void onClick(View v) {
                String str=editText1.getText().toString();
                int i=0;
                try{
                    for(i=str.length()-1;i>=0;i--){
                        if(!(str.charAt(i)>='0'&&str.charAt(i)<='9'||str.charAt(i)=='.'))
                            break;
                    }
                    Double num=Double.valueOf(str.substring(i+1,str.length()));
                    num=Math.cos(Math.toRadians(num));
                    str=str.substring(0,i+1);
                    str+=num.toString();
                    editText1.setText(str);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"error！input again",Toast.LENGTH_SHORT).show();
                    editText1.setText("0");
                }
            }
        });

        key[21].setOnClickListener(new View.OnClickListener() {//为C注册监听器
            @Override
            public void onClick(View view) {
                editText1.setText("0");
            }
        });

        key[22].setOnClickListener(new View.OnClickListener() {//为删除一位的按钮注册监听器
            @Override
            public void onClick(View view) {
                String text=editText1.getText().toString().trim();
                if(text.length()==1){
                    text="0";
                }
                else
                    text=text.substring(0,text.length()-1);
                editText1.setText(text);
            }
        });

        key[23].setOnClickListener(new View.OnClickListener() {//为等号注册监听器
            @Override
            public void onClick(View view) {
                String expr=editText1.getText().toString().trim();
                expr+='#';
                editText1.setText(caculate(expr).toString());
            }
        });
    }

    public int IsOpt(char ch){
        if(ch=='+'||ch=='-'||ch=='*'||ch=='/')
            return 1;
        else
            return 0;
    }//判断一个字符是不是运算符

    public static String [][] lexical_analyzer(String expr){
        String [][]lexicon=new String [100][2];

        int k=0;//记录lexicon数组元素的个数
        int i=0;
        char ch=expr.charAt(i);
        while(ch!='#'){
            if(ch==' '){
                i++;
                continue;
            }
            if(ch>='0'&&ch<='9'){
                int j=i;
                while((expr.charAt(j)>='0'&&expr.charAt(j)<='9')||expr.charAt(j)=='.'){
                    j++;
                }
                lexicon[k][0]="1";
                lexicon[k][1]=expr.substring(i,j);
                k++;
                i=j;
            }
            else if(ch=='('){
                lexicon[k][0]="3";
                lexicon[k][1]="(";
                k++;
                i++;
            }
            else if(ch==')'){
                lexicon[k][0]="3";
                lexicon[k][1]=")";
                k++;
                i++;
            }
            else if(ch=='+'){
                lexicon[k][0]="2";
                lexicon[k][1]="+";
                k++;
                i++;
            }
            else if(ch=='-'){
                lexicon[k][0]="2";
                lexicon[k][1]="-";
                k++;
                i++;
            }
            else if(ch=='*'){
                lexicon[k][0]="2";
                lexicon[k][1]="*";
                k++;
                i++;
            }
            else if(ch=='/'){
                lexicon[k][0]="2";
                lexicon[k][1]="/";
                k++;
                i++;
            }
            else if(ch=='%') {
                lexicon[k][0]="2";
                lexicon[k][1]="%";
                k++;
                i++;
            }
            else if(ch=='^'){
                lexicon[k][0]="2";
                lexicon[k][1]="^";
                k++;
                i++;
            }
            else{
                lexicon[0][0]="0";
                return lexicon;
            }
            ch=expr.charAt(i);
        }
        lexicon[k][0]="0";
        return lexicon;
    }

    public void simpleCaculate(Stack<Double>opn, Stack<Character>opt){
        try{
            char ch=opt.pop();
            Double num1=opn.pop();
            Double num2=opn.pop();
            if(ch=='+'){
                num1=num2+num1;
                opn.push(num1);
            }
            else if(ch=='-') {
                num1 = num2 - num1;
                opn.push(num1);
            }
            else if(ch=='*') {
                num1 = num2 * num1;
                opn.push(num1);
            }
            else if(ch=='/') {
                if(num1==0){
                    return;
                }
                num1 = num2/num1;//分母为零的错误处理
                opn.push(num1);
            }
            else if(ch=='^'){
                num1=Math.pow(num2,num1);
                opn.push(num1);
            }
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"error!input again",Toast.LENGTH_SHORT).show();
            return;
        }

    }//记得在函数外将运算符压栈

    public Double caculate(String expr){
        //1数字，2运算符，3分界符
        String [][]lexicon=new String[100][2];
        for(int m=0;m<100;m++){
            lexicon[m][0]="0";
        }
        Log.d("MainActivity", "caculate: "+expr);
        lexicon=lexical_analyzer(expr);
        if(lexicon[0][0]=="0"){
            Toast.makeText(MainActivity.this,"error!input again",Toast.LENGTH_SHORT).show();
            return 0.0;
        }

        for(int i=0;!lexicon[i][0].equals("0");i++){
            Log.d("lexical", "caculate: "+lexicon[i][0]+" "+lexicon[i][1]);
        }//打印分词结果

        Stack <Double> opn=new Stack<>();
        Stack <Character> opt=new Stack<>();
        int i=0;//分词结果的循环控制变量
        opt.push('#');//栈的栈底元素

        try {
            while(lexicon[i][0]!="0"){
                if(lexicon[i][0]=="1"){
                    opn.push(Double.valueOf(lexicon[i][1]));
                }
                else if(lexicon[i][0]=="2"){//如果当前是运算符
                    if(lexicon[i][1]=="+"||lexicon[i][1]=="-"){//如果当前运算符是加减
                        if(IsOpt(opt.peek())==0){
                            opt.push(lexicon[i][1].charAt(0));
                        }
                        else {
                            simpleCaculate(opn,opt);
                            opt.push(lexicon[i][1].charAt(0));
                        }
                    }
                    else if(lexicon[i][1]=="*"||lexicon[i][1]=="/"||lexicon[i][1]=="^"){
                        if(lexicon[i+1][1]=="("||lexicon[i+1][1]==")"){
                            opt.push(lexicon[i][1].charAt(0));
                        }
                        else{
                            opt.push(lexicon[i][1].charAt(0));
                            opn.push(Double.valueOf(lexicon[++i][1]));
                            simpleCaculate(opn,opt);
                        }

                    }
                }
                else if(lexicon[i][0]=="3"){
                    if(lexicon[i][1]=="("){
                        opt.push(lexicon[i][1].charAt(0));
                    }
                    else if(lexicon[i][1]==")"){
                        if(IsOpt(opt.peek())==1){
                            simpleCaculate(opn,opt);
                            opt.pop();
                        }
                        else{
                            opt.pop();
                        }
                    }
                }
                i++;
            }

            if(opt.peek()!='#')
                simpleCaculate(opn,opt);

        }catch (Exception e){
            Toast.makeText(MainActivity.this, "error！input again", Toast.LENGTH_SHORT).show();
            return 0.0;
        }
        if(!opn.empty()) {
            if (opn.size()!=1) {
                Toast.makeText(MainActivity.this, "error!input again", Toast.LENGTH_SHORT).show();
                return 0.0;
            }
            return opn.pop();
        }
        else {
            Toast.makeText(MainActivity.this, "error!input again", Toast.LENGTH_SHORT).show();
            return 0.0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Help:
                Toast.makeText(MainActivity.this,"This is the help information!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Exit:
                Toast.makeText(MainActivity.this,"exit the caculator",Toast.LENGTH_SHORT).show();
                ActivityCollector.finishAll();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            Intent intent=new Intent(MainActivity.this,Conversion.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
//改善幂次运算的符号

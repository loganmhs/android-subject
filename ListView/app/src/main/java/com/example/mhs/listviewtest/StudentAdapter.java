package com.example.mhs.listviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private  int resourceId;
    public StudentAdapter(Context context, int textViewResourceId, List<Student>objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Student student=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView cla=(TextView)view.findViewById(R.id.cla);
        TextView num=(TextView)view.findViewById(R.id.num);
        TextView sex=(TextView)view.findViewById(R.id.sex);
        TextView phone=(TextView)view.findViewById(R.id.phone);
        name.setText(student.getName());
        cla.setText(student.getCla());
        num.setText(student.getNum());
        sex.setText(student.getSex());
        phone.setText(student.getPhone());
        return view;
    }
}

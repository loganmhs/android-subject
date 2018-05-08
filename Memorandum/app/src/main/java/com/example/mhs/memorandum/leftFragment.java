package com.example.mhs.memorandum;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class leftFragment extends Fragment{

    private List<Fruit> fruitList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.left_fragment,container,false);

        initFruits();
        final RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.Title);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        final FruitAdapter adapter=new FruitAdapter(fruitList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemclick(View view, int position) {
                MainActivity mainActivity=(MainActivity)getActivity();
                EditText editText=(EditText)mainActivity.findViewById(R.id.content);
                editText.setText(adapter.getFruit().getName()+adapter.getFruit().getCNname());
                //Toast.makeText(view.getContext(),adapter.getFruit().getCNname(),Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);


        return view;
    }

    private void initFruits(){
        for(int i=0;i<4;i++){
            Fruit apple=new Fruit("apple","苹果");
            fruitList.add(apple);
            Fruit banana=new Fruit("banana","香蕉");
            fruitList.add(banana);
            Fruit orange=new Fruit("orange","橘子");
            fruitList.add(orange);
            Fruit watermelon=new Fruit("watermelon","西瓜");
            fruitList.add(watermelon);
            Fruit pear=new Fruit("pear","梨子");
            fruitList.add(pear);
            Fruit grape=new Fruit("grape","葡萄");
            fruitList.add(grape);
            Fruit pineapple=new Fruit("pineapple","凤梨");
            fruitList.add(pineapple);
            Fruit strawberry=new Fruit("strawberry","草莓");
            fruitList.add(strawberry);
            Fruit cherry=new Fruit("cherry","樱桃");
            fruitList.add(cherry);
            Fruit mango=new Fruit("mango","芒果");
            fruitList.add(mango);
        }
    }
}
//recycle日View的item监听事件怎么定义，怎么实现能在监听处使用findviewbyid？
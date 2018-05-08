package com.example.mhs.memorandum;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ListIterator;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit>mFruitList;
    private OnItemClickListener onItemClickListener;//声明一个接口的引用
    private  Fruit fruit;

    public Fruit getFruit() {
        return fruit;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fruitName;
        TextView CNname;
        View fruitView;

        public ViewHolder(View itemView) {
            super(itemView);
            fruitName=(TextView)itemView.findViewById(R.id.fruit_name);
            CNname=(TextView)itemView.findViewById(R.id.CNname);
            fruitView=itemView;
        }
    }
    public FruitAdapter(List<Fruit>fruitList){
        mFruitList=fruitList;
    }

    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);

        holder.fruitView.setOnClickListener(new View.OnClickListener() {//设置子项的点击事件
            @Override
            public void onClick(View v) {
                int positon=holder.getAdapterPosition();
                fruit=mFruitList.get(positon);
               // Toast.makeText(v.getContext(),fruit.getName()+fruit.getCNname(),Toast.LENGTH_SHORT).show();
                onItemClickListener.OnItemclick(holder.itemView,holder.getLayoutPosition());
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit=mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        holder.CNname.setText(fruit.getCNname());

    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

}

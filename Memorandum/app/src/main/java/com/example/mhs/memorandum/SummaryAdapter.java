package com.example.mhs.memorandum;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {

    private List<Summary>mSummaryList;
    private OnItemClickListener onItemClickListener;//声明一个接口的引用
    private  Summary summary;

    public Summary getSummary() {
        return summary;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView summaryName;
        View summaryView;

        public ViewHolder(View itemView) {
            super(itemView);
            summaryName=(TextView) itemView.findViewById(R.id.SummaryName);
            summaryView=itemView;
        }
    }
    public SummaryAdapter(List<Summary>summaryList){
        mSummaryList=summaryList;
    }


    @Override
    public SummaryAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.summary_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);

        holder.summaryView.setOnClickListener(new View.OnClickListener() {//设置子项的点击事件
            @Override
            public void onClick(View v) {
                int positon=holder.getAdapterPosition();
                summary=mSummaryList.get(positon);
                onItemClickListener.OnItemclick(holder.itemView,holder.getLayoutPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(SummaryAdapter.ViewHolder holder, int position) {
        Summary summary=mSummaryList.get(position);
        holder.summaryName.setText(summary.getSummaryName());
    }

    @Override
    public int getItemCount() {
        return mSummaryList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

}

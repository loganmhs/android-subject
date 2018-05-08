package com.example.mhs.memorandum;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class leftFragment extends Fragment{

    private List<Summary> summaryList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.left_fragment,container,false);

        initSummary();
        final RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.Title);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        final SummaryAdapter adapter=new SummaryAdapter(summaryList);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemclick(View view, int position) {
                MainActivity mainActivity=(MainActivity)getActivity();
                EditText editText=(EditText)mainActivity.findViewById(R.id.content);
                editText.setText(adapter.getSummary().getContent());
            }
        });

        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initSummary(){
        for(int i=0;i<1;i++){
            List<Summary>summaries= DataSupport.findAll(Summary.class);
            for(Summary summary:summaries){
                summaryList.add(summary);
            }
        }
    }
}
//recycle日View的item监听事件怎么定义，怎么实现能在监听处使用findviewbyid？
package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.Maintainence.BogeyActivity;
import com.kjsce.train.cia.Entity.TrainSearchListItem;
import com.kjsce.train.cia.R;

import java.util.List;

public class TrainSearchListAdapter extends RecyclerView.Adapter<TrainSearchListAdapter.ViewHolder> {

    private List<TrainSearchListItem> trainSearchListItems;
    private Context context;

    public TrainSearchListAdapter(List<TrainSearchListItem> trainSearchListItems, Context context) {
        this.trainSearchListItems = trainSearchListItems;
        this.context = context;
    }
    @Override
    public TrainSearchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.train_search_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrainSearchListAdapter.ViewHolder holder, int position) {
        TrainSearchListItem trainSearchListItem = trainSearchListItems.get(position);
        holder.trainNo.setText(trainSearchListItem.gettNo());
        holder.trainName.setText(trainSearchListItem.gettName());
        final String trainNo=trainSearchListItem.gettNo();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent to open next activity on Item click

                Intent i =new Intent(context, BogeyActivity.class);
                i.putExtra("TrainNo",trainNo);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainSearchListItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView trainNo;
        public TextView trainName;

        public ViewHolder(View itemView) {
            super(itemView);
            trainNo=itemView.findViewById(R.id.train_no);
            trainName=itemView.findViewById(R.id.train_name);
        }
    }
}

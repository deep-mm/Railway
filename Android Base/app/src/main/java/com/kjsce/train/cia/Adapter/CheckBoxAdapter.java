package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;


public class CheckBoxAdapter extends RecyclerView.Adapter<com.kjsce.train.cia.Adapter.CheckBoxAdapter.ViewHolder>{
    private final ArrayList<UserEntity> Mvalues;
    private Boolean isChecked;
    Context context;

    public CheckBoxAdapter() {
        Mvalues = null;

    }

    public CheckBoxAdapter(ArrayList mvalues,Context c) {
        Mvalues = mvalues;
        context = c;

    }

    @Override
    public com.kjsce.train.cia.Adapter.CheckBoxAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_check_boxes, parent, false);
        return new com.kjsce.train.cia.Adapter.CheckBoxAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(final com.kjsce.train.cia.Adapter.CheckBoxAdapter.ViewHolder holder, final int position) {

        holder.check_box.setText(Mvalues.get(position).getName()+" ( "+Mvalues.get(position).getDesignation()+" )");

        SharedData sd = new SharedData(context);
        holder.check_box.setChecked(false);

        holder.check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.check_box.isChecked())
                    holder.check_box.setChecked(false);

                else
                    holder.check_box.setChecked(true);

                isChecked = holder.check_box.isChecked();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final CheckedTextView check_box;

        public ViewHolder(View itemView) {
            super(itemView);
            check_box = (CheckedTextView)itemView.findViewById(R.id.textView);
            check_box.setChecked(false);
        }
    }
}
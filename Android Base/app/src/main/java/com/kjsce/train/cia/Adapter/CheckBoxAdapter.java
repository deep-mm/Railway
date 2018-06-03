package com.kjsce.train.cia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entity.CardFiles;
import com.kjsce.train.cia.Entity.StoreCard;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;


public class CheckBoxAdapter extends RecyclerView.Adapter<com.kjsce.train.cia.Adapter.CheckBoxAdapter.ViewHolder>{
    private final ArrayList<String> Mvalues;
    private Boolean isChecked;
    private List<Boolean> type_list;

    Context context;
    int flag =1;
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

        holder.check_box.setText(Mvalues.get(position));

        SharedData sd = new SharedData(context);

        type_list = sd.getTypeList();
        if(type_list!=null) {
            holder.check_box.setChecked(type_list.get(position));
        }

        holder.check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.check_box.isChecked())
                    holder.check_box.setChecked(false);

                else
                    holder.check_box.setChecked(true);

                isChecked = holder.check_box.isChecked();

                type_list = sd.getTypeList();
                if(type_list==null) {
                    type_list = new ArrayList<Boolean>();
                    for (int i = 0; i < 8; i++)
                        type_list.add(false);
                }


                    System.out.println("zzzzz" + type_list);
                    type_list.set(position, isChecked);

                sd.setTypeList(type_list);
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
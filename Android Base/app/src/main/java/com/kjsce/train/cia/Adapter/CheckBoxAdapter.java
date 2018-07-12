package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.kjsce.train.cia.Activity.NotifyContacts;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entities.UserCheckBox;
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;


public class CheckBoxAdapter extends RecyclerView.Adapter<com.kjsce.train.cia.Adapter.CheckBoxAdapter.ViewHolder>{
    private final List<UserCheckBox> Mvalues;
    private Boolean isChecked;
    private List<UserEntity> checkedList;
    Context context;

    public CheckBoxAdapter() {
        Mvalues = null;

    }

    public CheckBoxAdapter(List<UserCheckBox> mvalues,Context c) {
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

        UserEntity userEntity = Mvalues.get(position).getUserEntity();
        holder.check_box.setText(userEntity.getName()+" ( "+userEntity.getDesignation()+" )");

        SharedData sharedData = new SharedData(context);
        checkedList = sharedData.getUserEntityList();

        if(contains(userEntity.getMobileNumber()))
            holder.check_box.setChecked(true);

        else
            holder.check_box.setChecked(false);

        checkEmpty();

        holder.check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.check_box.isChecked())
                    holder.check_box.setChecked(false);

                else
                    holder.check_box.setChecked(true);

                isChecked = holder.check_box.isChecked();
                Mvalues.get(position).setChecked(isChecked);

                if(!isChecked && contains(userEntity.getMobileNumber())){
                    checkedList.remove(getPosition(userEntity.getMobileNumber()));
                    sharedData.setUserEntityList(checkedList);
                }

                if(isChecked && !contains(userEntity.getMobileNumber())){
                    checkedList.add(userEntity);
                    sharedData.setUserEntityList(checkedList);
                }

                checkEmpty();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public void checkEmpty(){
        if(NotifyContacts.from.equalsIgnoreCase("manageUsers")) {
            if (checkedList.isEmpty()) {
                NotifyContacts.removeButton.setVisibility(View.INVISIBLE);
                NotifyContacts.addButton.setVisibility(View.VISIBLE);
            } else {
                NotifyContacts.removeButton.setVisibility(View.VISIBLE);
                NotifyContacts.addButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    public int getPosition(String mobile){
        for(int i=0;i<checkedList.size();i++){
            if(checkedList.get(i).getMobileNumber().equalsIgnoreCase(mobile))
                return i;
        }
        return 0;
    }

    public boolean contains(String mobile){
        for(int i=0;i<checkedList.size();i++){
            if(checkedList.get(i).getMobileNumber().equalsIgnoreCase(mobile))
                return true;
        }
        return false;
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
package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kjsce.train.cia.Activity.Maintainence.BogeyReportActivity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

/**
 * Created by Ashwini on 27-03-2018.
 */

public class MaintainenceGridAdapter extends RecyclerView.Adapter<MaintainenceGridAdapter.ViewHolder> {
    ArrayList<String> coach;
    Context context;

    public MaintainenceGridAdapter(Context context, ArrayList<String> coach) {
        super();
        this.context = context;
        this.coach = coach;
    }

    @Override
    public MaintainenceGridAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.griditem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MaintainenceGridAdapter.ViewHolder viewHolder, int i) {

        viewHolder.coach.setText(coach.get(i));

        viewHolder.setClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                context.startActivity(new Intent(context, BogeyReportActivity.class));
                Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return coach.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView coach;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            coach = (TextView) itemView.findViewById(R.id.txtCoach);
            itemView.setOnClickListener(this);
        }


        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;

        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view,getPosition());

        }
    }


}

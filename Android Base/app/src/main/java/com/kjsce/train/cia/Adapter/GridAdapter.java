package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.Inspection.InspectionBogeyReportActivity;
import com.kjsce.train.cia.Listeners.ItemClickListener;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

/**
 * Created by Ashwini on 27-03-2018.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    ArrayList<String> coach;
    Context context;

    public GridAdapter(Context context, ArrayList<String> coach) {
        super();
        this.context = context;
        this.coach = coach;
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.griditem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final GridAdapter.ViewHolder viewHolder, int i) {

        viewHolder.coach.setText(coach.get(i));
        int position = i;

        viewHolder.setClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(context, InspectionBogeyReportActivity.class);
                i.putExtra("coach",viewHolder.coach.getText().toString());
                i.putExtra("coach_index",position);
                context.startActivity(i);
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

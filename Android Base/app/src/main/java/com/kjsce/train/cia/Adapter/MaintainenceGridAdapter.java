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
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entity.BogeyEntity;
import com.kjsce.train.cia.Entity.Report.DetailedReport;
import com.kjsce.train.cia.Listeners.ItemClickListener;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashwini on 27-03-2018.
 */

public class MaintainenceGridAdapter extends RecyclerView.Adapter<MaintainenceGridAdapter.ViewHolder> {
    ArrayList<String> coach;
    Context context;
    DetailedReport detailedReport;
    List<BogeyEntity> bogeyEntities;
    SharedData sd;

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
                sd = new SharedData(context);

                detailedReport = sd.getDetailedReport();
                bogeyEntities = detailedReport.getBogeyEntityList();
                sd.setBogieEntity(bogeyEntities);

                sd.setBogie(bogeyEntities.get(i).getBogeyNumber());
                sd.setBogeyEntityobject(bogeyEntities.get(i));
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

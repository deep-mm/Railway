package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.Maintainence.BogeyActivity;
import com.kjsce.train.cia.Entity.MaintainenceCardFiles;
import com.kjsce.train.cia.Entity.Report.DetailedReport;
import com.kjsce.train.cia.Entity.Report.GeneralReport;
import com.kjsce.train.cia.Listeners.ItemClickListener;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhaval on 30-04-2018.
 */

public class MaintainenceBogeyReportAdapter extends RecyclerView.Adapter<MaintainenceBogeyReportAdapter.ViewHolder> {

    private final List<DetailedReport> Mvalues;
    private final List<GeneralReport> reportvalues;

    Context context;

    public MaintainenceBogeyReportAdapter() {
        Mvalues = null;
        reportvalues = null;
    }

    public MaintainenceBogeyReportAdapter(List<DetailedReport> mvalues,List<GeneralReport> reportvalues, Context c) {
        this.Mvalues = mvalues;
        this.reportvalues = reportvalues;
        context = c;

    }

    @Override
    public MaintainenceBogeyReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_total_inspection, parent, false);
        return new MaintainenceBogeyReportAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(MaintainenceBogeyReportAdapter.ViewHolder holder, final int position) {


       holder.train.setText(Mvalues.get(position).getTrainNumber()+" "+Mvalues.get(position).getTrainName());
       holder.in_date.setText(Mvalues.get(position).getDateTime());
        String Name = Mvalues.get(position).getTrainName();
        String traind[] = new String[2];
        traind[0] = Mvalues.get(position).getTrainNumber();
        traind[1] = Mvalues.get(position).getTrainName();
        holder.setClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                Intent i =new Intent(context, BogeyActivity.class);
                i.putExtra("TrainNo",traind[0]);
                i.putExtra("TrainName",traind[1]);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        final TextView train,in_date;
        private ItemClickListener clickListener;


        public ViewHolder(View itemView) {
            super(itemView);



            train = (TextView)itemView.findViewById(R.id.train);
            in_date = (TextView)itemView.findViewById(R.id.in_date);

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

package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.opengl.Matrix;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kjsce.train.cia.Activity.Inspection.InspectionBogeyReportActivity;
import com.kjsce.train.cia.Activity.Inspection.InspectionTrainReportActivity;
import com.kjsce.train.cia.Activity.Maintainence.MaintainenceTrainDetailsActivity;
import com.kjsce.train.cia.Entity.CardFiles;
import com.kjsce.train.cia.Entity.MaintainenceCardFiles;
import com.kjsce.train.cia.Entity.StoreCard;
import com.kjsce.train.cia.Listeners.ItemClickListener;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

/**
 * Created by Dhaval on 30-04-2018.
 */

public class MaintainenceBogeyReportAdapter extends RecyclerView.Adapter<MaintainenceBogeyReportAdapter.ViewHolder> {

    private final ArrayList<MaintainenceCardFiles> Mvalues;
    private final ArrayList<MaintainenceCardFiles> reportvalues;

    Context context;

    public MaintainenceBogeyReportAdapter() {
        Mvalues = null;
        reportvalues = null;
    }

    public MaintainenceBogeyReportAdapter(ArrayList mvalues,ArrayList reportvalues, Context c) {
        Mvalues = mvalues;
        this.reportvalues = reportvalues;
        context = c;

    }

    @Override
    public MaintainenceBogeyReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bogey_report_cards, parent, false);
        return new MaintainenceBogeyReportAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(MaintainenceBogeyReportAdapter.ViewHolder holder, final int position) {
        holder.setClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(context,InspectionTrainReportActivity.class);
                i.putExtra("from","fromTotalInspection");
                context.startActivity(i);
            }
        });

        holder.train.setText(Mvalues.get(position).getTrain_name());
        holder.in_date.setText(Mvalues.get(position).getDate().toString());
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
            train = (TextView)itemView.findViewById(R.id.train_name);
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

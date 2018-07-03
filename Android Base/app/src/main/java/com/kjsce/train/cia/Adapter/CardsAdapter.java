package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.CardDetails;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.dift.ui.SwipeToAction;


public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder>{
    private final ArrayList<IndexEntryEntity> Mvalues;
    Context context;
    SharedData sharedData;

    public CardsAdapter() {
        Mvalues = null;

    }

    public CardsAdapter(ArrayList mvalues, Context c) {
        Mvalues = mvalues;
        context = c;

    }

    @Override
    public CardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card, parent, false);
        return new CardsAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder (final CardsAdapter.ViewHolder holder, final int position) {

        sharedData = new SharedData(context);
        holder.subType.setText(Mvalues.get(position).getSubtype());
        StringBuffer stringBuffer = new StringBuffer(Mvalues.get(position).getId());
        String date = stringBuffer.substring(0,15);
        String name = stringBuffer.substring(15);
        holder.lastUpdated.setText(name);
        holder.date.setText(getDate(date));
        IndexEntryEntity indexEntryEntity = Mvalues.get(position);
        ViewHolder vh = (ViewHolder) holder;
        vh.data = indexEntryEntity;

        //TODO: Replace with lastUpdated once done
    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends SwipeToAction.ViewHolder<IndexEntryEntity>
    {
        TextView subType, lastUpdated, date;

        public ViewHolder(View itemView) {
            super(itemView);
            subType = (TextView) itemView.findViewById(R.id.sub_type_text);
            lastUpdated = (TextView) itemView.findViewById(R.id.last_updated_text);
            date = (TextView) itemView.findViewById(R.id.notification_date);
        }
    }

    public String getDate(String date){
        SimpleDateFormat spf=new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("dd/MM/yyyy | HH:mm");
        date = spf.format(newDate);
        return date;
    }
}
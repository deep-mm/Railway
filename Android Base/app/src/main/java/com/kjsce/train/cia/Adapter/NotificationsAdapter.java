package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.dift.ui.SwipeToAction;


public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder>{
    private final List<UserNotificationEntity> Mvalues;
    Context context;
    SharedData sharedData;

    public NotificationsAdapter() {
        Mvalues = null;

    }

    public NotificationsAdapter(List mvalues, Context c) {
        Mvalues = mvalues;
        context = c;

    }

    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notification, parent, false);
        return new NotificationsAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder (final NotificationsAdapter.ViewHolder holder, final int position) {

        sharedData = new SharedData(context);
        UserNotificationEntity userNotificationEntity = Mvalues.get(position);
        holder.notificationText.setText(userNotificationEntity.getSender()+" has generated a report of train: "+userNotificationEntity.getTrainNumber());
        holder.notificationDate.setText(getDate(userNotificationEntity.getDateTime()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*sharedData.setTrain(userNotificationEntity.getTrainNumber());
                Intent intent*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends SwipeToAction.ViewHolder<DetailsAdapter>
    {
        TextView notificationDate, notificationText;

        public ViewHolder(View itemView) {
            super(itemView);
            notificationDate = (TextView) itemView.findViewById(R.id.notification_date);
            notificationText = (TextView) itemView.findViewById(R.id.notification_text);
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
        spf= new SimpleDateFormat("dd/MM/yyyy | hh:mm");
        date = spf.format(newDate);
        return date;
    }
}
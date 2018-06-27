package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
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
        holder.notificationText.setText(Mvalues.get(position).getTrainNumber());
        //TODO: Create proper sentence for above
        holder.notificationDate.setText(Mvalues.get(position).getDateTime());
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
}
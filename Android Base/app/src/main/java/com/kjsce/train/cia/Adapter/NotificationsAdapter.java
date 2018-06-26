package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

import co.dift.ui.SwipeToAction;


public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder>{
    private final ArrayList<DetailedCard> Mvalues;
    Context context;
    SharedData sharedData;

    public NotificationsAdapter() {
        Mvalues = null;

    }

    public NotificationsAdapter(ArrayList mvalues, Context c) {
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
        holder.notificationText.setText(Mvalues.get(position).getType());
        holder.notificationDate.setText(Mvalues.get(position).getSubmittedBy());
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
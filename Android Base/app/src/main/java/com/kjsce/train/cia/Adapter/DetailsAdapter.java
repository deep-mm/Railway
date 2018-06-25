package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.R;

import java.util.ArrayList;


public class DetailsAdapter extends RecyclerView.Adapter<com.kjsce.train.cia.Adapter.DetailsAdapter.ViewHolder>{
    private final ArrayList<String> Mvalues;
    Context context;

    public DetailsAdapter() {
        Mvalues = null;

    }

    public DetailsAdapter(ArrayList mvalues,Context c) {
        Mvalues = mvalues;
        context = c;

    }

    @Override
    public com.kjsce.train.cia.Adapter.DetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_details, parent, false);
        return new com.kjsce.train.cia.Adapter.DetailsAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(final com.kjsce.train.cia.Adapter.DetailsAdapter.ViewHolder holder, final int position) {

        SharedData sd = new SharedData(context);

    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,content_text;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content_text = (TextView) itemView.findViewById(R.id.content_text);
        }
    }
}
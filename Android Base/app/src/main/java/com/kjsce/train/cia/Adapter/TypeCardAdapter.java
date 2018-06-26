package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.kjsce.train.cia.Activity.CardsActivity;
import com.kjsce.train.cia.Activity.Details;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;


public class TypeCardAdapter extends RecyclerView.Adapter<TypeCardAdapter.ViewHolder>{
    private final ArrayList<String> Mvalues;
    Context context;
    SharedData sharedData;

    public TypeCardAdapter() {
        Mvalues = null;

    }

    public TypeCardAdapter(ArrayList mvalues, Context c) {
        Mvalues = mvalues;
        context = c;

    }

    @Override
    public TypeCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_type_card, parent, false);
        return new TypeCardAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(final TypeCardAdapter.ViewHolder holder, final int position) {

        sharedData = new SharedData(context);
        holder.type_text.setText(Mvalues.get(position));

        holder.rippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedData.setType(Mvalues.get(position));
                Intent intent = new Intent(context, CardsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView type_text;
        final RippleView rippleView;

        public ViewHolder(View itemView) {
            super(itemView);
            type_text = (TextView) itemView.findViewById(R.id.type_text);
            rippleView = (RippleView) itemView.findViewById(R.id.ripple);
        }
    }
}
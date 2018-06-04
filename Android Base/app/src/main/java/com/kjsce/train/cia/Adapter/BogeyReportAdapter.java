package com.kjsce.train.cia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.kjsce.train.cia.Activity.Inspection.InpectionTrainDetailsActivity;
import com.kjsce.train.cia.Activity.Maintainence.MaintainenceTrainDetailsActivity;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.Entity.CardFiles;
import com.kjsce.train.cia.Entity.StoreCard;
import com.kjsce.train.cia.Listeners.ItemClickListener;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhaval on 26-03-2018.
 */

public class BogeyReportAdapter extends RecyclerView.Adapter<BogeyReportAdapter.ViewHolder>{
    private final List<DetailedCard> Mvalues;
    private ArrayList<String> Spinner_list = null;
    public int SELECT_PICTURE = 100;
    public StoreCard storeCard = new StoreCard();
    ArrayAdapter<String> spinner_adapter;
    Context context;
    String type,comment;
    String comment_text;
    boolean flag;

    public BogeyReportAdapter() {
        Mvalues = null;

    }

    public BogeyReportAdapter(List<DetailedCard> mvalues, ArrayList<String> spinner_list, Context c,boolean type,String comment) {
                Mvalues = mvalues;
                Spinner_list =spinner_list;
                context = c;
                flag = type;
                this.comment = comment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bogey_cards, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(BogeyReportAdapter.ViewHolder holder, final int position) {


            holder.in_comment.setFocusable(flag);

        holder.setClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                //Nothing
            }
        });


        //textwatchers i.e storeCard contains all the information of all the cards store them in database
        holder.in_comment.setText(Mvalues.get(position).getComment());
        holder.in_comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                comment_text = s.toString();
                storeCard.setIn_comment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(flag)
        {
            holder.in_type_text.setVisibility(View.GONE);
        }
        else
        {
            holder.in_type.setVisibility(View.GONE);
            type = Spinner_list.get(0);
            comment_text = Mvalues.get(position).getComment();
        }

        holder.in_type.setSelection(getIndex(holder.in_type,Mvalues.get(position).getType()));
        holder.in_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String typevalue = parent.getItemAtPosition(position).toString();
                if (!typevalue.equals(null)) {
                    type = typevalue;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0)
                {
                    notifyDataSetChanged();
                    Mvalues.remove(position);
                }
                else
                {
                    Mvalues.remove(position);
                    notifyItemRemoved(position);}
            }
        });

        holder.more_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    Intent intent = new Intent(context, InpectionTrainDetailsActivity.class);
                    intent.putExtra("type", type);
                    intent.putExtra("comment", comment_text);
                    intent.putExtra("position",position);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, MaintainenceTrainDetailsActivity.class);
                    intent.putExtra("type", type);
                    intent.putExtra("comment", comment_text);
                    intent.putExtra("position",position);
                    context.startActivity(intent);
                }
            }
        });


    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        final TextView comment,type,more_detail,in_type_text;
        final EditText in_comment;
        final Spinner in_type;
        final ImageButton remove;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            comment = (TextView)itemView.findViewById(R.id.comment);
            type = (TextView)itemView.findViewById(R.id.type);
            remove = (ImageButton)itemView.findViewById(R.id.remove);
            more_detail = (TextView) itemView.findViewById(R.id.more_detail);

            in_comment = (EditText)itemView.findViewById(R.id.in_comment);
            in_type = (Spinner) itemView.findViewById(R.id.in_type);
            spinner_adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,Spinner_list);
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            in_type.setAdapter(spinner_adapter);
            in_type_text = (TextView)itemView.findViewById(R.id.in_type_text);
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
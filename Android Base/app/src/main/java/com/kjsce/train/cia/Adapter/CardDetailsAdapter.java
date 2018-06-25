package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.Details;
import com.kjsce.train.cia.Activity.ImageShow;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.R;

import java.util.ArrayList;


public class CardDetailsAdapter extends RecyclerView.Adapter<com.kjsce.train.cia.Adapter.CardDetailsAdapter.ViewHolder>{
    private final ArrayList<DetailedCard> Mvalues;
    private String type,side;
    Context context;
    private SharedData sharedData;

    public CardDetailsAdapter() {
        Mvalues = null;
    }

    public CardDetailsAdapter(ArrayList mvalues,Context c) {
        Mvalues = mvalues;
        context = c;
    }

    @Override
    public com.kjsce.train.cia.Adapter.CardDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_detail, parent, false);
        return new com.kjsce.train.cia.Adapter.CardDetailsAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(final com.kjsce.train.cia.Adapter.CardDetailsAdapter.ViewHolder holder, final int position) {

        sharedData = new SharedData(context);
        side = "left";
        type = "audio";
        //TODO: Determine type and side of the card

        if(side.equalsIgnoreCase("left")) {
            holder.left.setVisibility(View.VISIBLE);
            holder.right.setVisibility(View.GONE);

            if(type.equalsIgnoreCase("audio")){
                holder.audio_left.setVisibility(View.VISIBLE);
                holder.image_left.setVisibility(View.GONE);
                holder.text_left.setVisibility(View.GONE);

                holder.audio_text_left.setText(Mvalues.get(position).getComment());
            }

            else if(type.equalsIgnoreCase("image")){
                holder.audio_left.setVisibility(View.GONE);
                holder.image_left.setVisibility(View.VISIBLE);
                holder.text_left.setVisibility(View.GONE);
            }

            else{
                holder.audio_left.setVisibility(View.GONE);
                holder.image_left.setVisibility(View.GONE);
                holder.text_left.setVisibility(View.VISIBLE);
            }

            holder.name_text_left.setText("");
            holder.time_text_left.setText("");
        }

        else {
            holder.left.setVisibility(View.GONE);
            holder.right.setVisibility(View.VISIBLE);

            if(type.equalsIgnoreCase("audio")){
                holder.audio_right.setVisibility(View.VISIBLE);
                holder.image_right.setVisibility(View.GONE);
                holder.text_right.setVisibility(View.GONE);

                holder.audio_text_right.setText(Mvalues.get(position).getComment());
            }

            else if(type.equalsIgnoreCase("image")){
                holder.audio_right.setVisibility(View.GONE);
                holder.image_right.setVisibility(View.VISIBLE);
                holder.text_right.setVisibility(View.GONE);
            }

            else{
                holder.audio_right.setVisibility(View.GONE);
                holder.image_right.setVisibility(View.GONE);
                holder.text_right.setVisibility(View.VISIBLE);
            }

            holder.name_text_right.setText("");
            holder.time_text_right.setText("");
        }

        holder.image_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageClicked();
            }
        });

        holder.image_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageClicked();
            }
        });

        holder.image_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClicked();
                return true;
            }
        });

        holder.image_right.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClicked();
                return true;
            }
        });

        holder.text_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClicked();
                return true;
            }
        });

        holder.text_right.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClicked();
                return true;
            }
        });

        holder.audio_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClicked();
                return true;
            }
        });

        holder.audio_right.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClicked();
                return true;
            }
        });

        holder.audio_button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioClicked();
            }
        });

        holder.audio_button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioClicked();
            }
        });

    }

    public void imageClicked(){
        Intent intent = new Intent(context, ImageShow.class);
        String url = "";
        String title = "";
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    public void audioClicked(){

        /*materialDialog = new MaterialDialog.Builder(context)
                .title("Audio")
                .content("Playing Audio")
                .progress(true, 0)
                .show();
        mp.setDataSource(Mvalues.get(position));
        mp.prepare();
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                materialDialog.hide();
            }
        });*/

    }

    public void longClicked(){
        Intent intent = new Intent(context, Details.class);
        //Put object into sharedData
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout left,right,audio_left,audio_right;
        ImageView image_left,image_right,audio_button_left,audio_button_right,train_icon_left,train_icon_right;
        TextView text_left,text_right,name_text_left,time_text_left,name_text_right,time_text_right,audio_text_left,audio_text_right;

        public ViewHolder(View itemView) {
            super(itemView);
            left = (RelativeLayout) itemView.findViewById(R.id.left);
            right = (RelativeLayout) itemView.findViewById(R.id.right);
            audio_left = (RelativeLayout) itemView.findViewById(R.id.audio_left);
            audio_right = (RelativeLayout) itemView.findViewById(R.id.audio_right);

            image_left = (ImageView) itemView.findViewById(R.id.image_left);
            image_right = (ImageView) itemView.findViewById(R.id.image_right);
            audio_button_left = (ImageView) itemView.findViewById(R.id.audio_button_left);
            audio_button_right = (ImageView) itemView.findViewById(R.id.audio_button_right);
            train_icon_left = (ImageView) itemView.findViewById(R.id.train_icon_left);
            train_icon_right = (ImageView) itemView.findViewById(R.id.train_icon_right);

            text_left = (TextView) itemView.findViewById(R.id.text_left);
            text_right = (TextView) itemView.findViewById(R.id.text_right);
            name_text_left = (TextView) itemView.findViewById(R.id.name_text_left);
            time_text_left = (TextView) itemView.findViewById(R.id.time_text_left);
            name_text_right = (TextView) itemView.findViewById(R.id.name_text_right);
            time_text_right = (TextView) itemView.findViewById(R.id.time_text_right);

            audio_text_left = (TextView) itemView.findViewById(R.id.audio_text_left);
            audio_text_right = (TextView) itemView.findViewById(R.id.audio_text_right);
        }
    }
}
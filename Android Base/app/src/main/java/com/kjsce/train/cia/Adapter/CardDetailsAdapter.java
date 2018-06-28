package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Activity.Details;
import com.kjsce.train.cia.Activity.ImageShow;
import com.kjsce.train.cia.Activity.MainActivity;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class CardDetailsAdapter extends RecyclerView.Adapter<com.kjsce.train.cia.Adapter.CardDetailsAdapter.ViewHolder>{
    private final ArrayList<CardEntity> Mvalues;
    private String type,side;
    Context context;
    private SharedData sharedData;
    MaterialDialog materialDialog;
    MediaPlayer mp;

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
        mp = new MediaPlayer();
        CardEntity cardEntity = Mvalues.get(position);
        System.out.println("CardEntity: "+cardEntity);
        if(cardEntity.getSender().equalsIgnoreCase(sharedData.getUserEntity().getName())){
            side = "right";
        }
        else{
            side = "left";
        }

        if(cardEntity.getAudio()!=null)
            type = "audio";

        else if(cardEntity.getImage()!=null)
            type = "image";

        else
            type = "comment";

        if(side.equalsIgnoreCase("left")) {
            holder.left.setVisibility(View.VISIBLE);
            holder.right.setVisibility(View.GONE);

            if(type.equalsIgnoreCase("audio")){
                holder.audio_left.setVisibility(View.VISIBLE);
                holder.image_left.setVisibility(View.GONE);
                holder.text_left.setVisibility(View.GONE);
                holder.audio_text_left.setText(cardEntity.getComment());
            }

            else if(type.equalsIgnoreCase("image")){
                holder.audio_left.setVisibility(View.GONE);
                holder.image_left.setVisibility(View.VISIBLE);
                holder.text_left.setVisibility(View.GONE);

                Picasso.with(context).load(cardEntity.getImage().get(0)).into(holder.image_left);
            }

            else{
                holder.audio_left.setVisibility(View.GONE);
                holder.image_left.setVisibility(View.GONE);
                holder.text_left.setVisibility(View.VISIBLE);
                holder.text_left.setText(cardEntity.getComment());
            }

            holder.name_text_left.setText(cardEntity.getSender());
            holder.time_text_left.setText(getDate(cardEntity.getDateTime()));
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

                Picasso.with(context).load(cardEntity.getImage().get(0)).into(holder.image_right);
            }

            else{
                holder.audio_right.setVisibility(View.GONE);
                holder.image_right.setVisibility(View.GONE);
                holder.text_right.setVisibility(View.VISIBLE);
                holder.text_right.setText(cardEntity.getComment());
            }

            holder.name_text_right.setText(cardEntity.getSender());
            holder.time_text_right.setText(getDate(cardEntity.getDateTime()));
        }

        holder.image_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardEntity cardEntity1 = Mvalues.get(position);
                imageClicked(cardEntity1);
            }
        });

        holder.image_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardEntity cardEntity1 = Mvalues.get(position);
                imageClicked(cardEntity1);
            }
        });

        holder.image_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CardEntity cardEntity1 = Mvalues.get(position);
                longClicked(cardEntity1);
                return true;
            }
        });

        holder.image_right.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CardEntity cardEntity1 = Mvalues.get(position);
                longClicked(cardEntity1);
                return true;
            }
        });

        holder.text_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CardEntity cardEntity1 = Mvalues.get(position);
                longClicked(cardEntity1);
                return true;
            }
        });

        holder.text_right.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CardEntity cardEntity1 = Mvalues.get(position);
                longClicked(cardEntity1);
                return true;
            }
        });

        holder.audio_left.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CardEntity cardEntity1 = Mvalues.get(position);
                longClicked(cardEntity1);
                return true;
            }
        });

        holder.audio_right.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CardEntity cardEntity1 = Mvalues.get(position);
                longClicked(cardEntity1);
                return true;
            }
        });

        holder.audio_button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CardEntity cardEntity1 = Mvalues.get(position);
                    audioClicked(cardEntity1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.audio_button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CardEntity cardEntity1 = Mvalues.get(position);
                    audioClicked(cardEntity1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void imageClicked(CardEntity cardEntity){
        Intent intent = new Intent(context, ImageShow.class);
        String url = cardEntity.getImage().get(0);
        String title = cardEntity.getComment();
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    public void audioClicked(CardEntity cardEntity) throws IOException {

        System.out.println("CardEntityInside: "+cardEntity);
        materialDialog = new MaterialDialog.Builder(context)
                .title("Audio")
                .content("Playing Audio")
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
        System.out.println("CardEntityInside: "+cardEntity);
        mp = new MediaPlayer();
        mp.setDataSource(cardEntity.getAudio().get(0));
        mp.prepare();
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                materialDialog.hide();
                mp.stop();
            }
        });

    }

    public void longClicked(CardEntity cardEntity){
        Intent intent = new Intent(context, Details.class);
        sharedData.setCardEntity(cardEntity);
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
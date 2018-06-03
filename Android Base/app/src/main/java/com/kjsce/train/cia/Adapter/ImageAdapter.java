package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kjsce.train.cia.Activity.ImageShow;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhaval on 09-04-2018.
 */

/**
 * Created by Dhaval on 26-03-2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    private final List<String> Mvalues;


    Context context;
    int flag =1;
    public ImageAdapter() {
        Mvalues = null;
    }

    public ImageAdapter(List mvalues, Context c) {
        Mvalues = mvalues;
        context = c;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_image, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.image_name.setText(Mvalues.get(position));




        if(Mvalues.get(position).contains("3gp"))
            holder.type = "audio";

        else
            holder.type = "image";

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.type.equalsIgnoreCase("audio")){
                        MediaPlayer mp = new MediaPlayer();

                        try {
                            mp.setDataSource(Mvalues.get(position));
                            mp.prepare();
                            mp.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                   /* Intent i = new Intent(context, ImageShow.class);
                    i.putExtra("path",Mvalues.get(position));
                    context.startActivity(i);*/

                   // mToast(getLayoutPosition(),image_name.getText());
                    Intent i=new Intent(context,ImageShow.class);
                    i.putExtra("url",holder.image_name.getText());
                    context.startActivity(i);
                }
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mvalues.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView image_name;
        final ImageButton remove;
        String type;
        public ViewHolder(View itemView) {
            super(itemView);
            image_name = (TextView)itemView.findViewById(R.id.imageurl);
            remove =(ImageButton)itemView.findViewById(R.id.remove);
            type = "";
        }

    }
}
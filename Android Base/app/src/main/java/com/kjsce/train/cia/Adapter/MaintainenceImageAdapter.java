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

import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Activity.ImageShow;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

/**
 * Created by Dhaval on 02-06-2018.
 */

public class MaintainenceImageAdapter extends RecyclerView.Adapter<MaintainenceImageAdapter.ViewHolder>{
    private final ArrayList<String> Mvalues;
    MaterialDialog materialDialog;


    Context context;
    int flag =1;
    public MaintainenceImageAdapter() {
        Mvalues = null;
    }

    public MaintainenceImageAdapter(ArrayList mvalues,Context c) {
        Mvalues = mvalues;
        context = c;

    }

    @Override
    public MaintainenceImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_image_maintainence, parent, false);
        return new MaintainenceImageAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(MaintainenceImageAdapter.ViewHolder holder, final int position) {

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
                        materialDialog = new MaterialDialog.Builder(context)
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
                        });
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


    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        final TextView image_name;

        String type;
        public ViewHolder(View itemView) {
            super(itemView);
            image_name = (TextView)itemView.findViewById(R.id.imageurl);

            type = "";
        }

        @Override
        public void onClick(View view) {

           mToast(getLayoutPosition(),image_name.getText());
            Intent i=new Intent(context,ImageShow.class);
            i.putExtra("url",image_name.getText());
            context.startActivity(i);


            //Log.d("onclick", "onClick " + getLayoutPosition() + " " + item.getText());
            //Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show();
        }
        private void mToast(int layoutPosition, CharSequence text) {
            Toast.makeText(context,"Clicked"+ layoutPosition+text, Toast.LENGTH_SHORT).show();
        }
    }
}

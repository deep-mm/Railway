package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kjsce.train.cia.Activity.ImageShow;
import com.kjsce.train.cia.Entity.Item;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

/**
 * Created by darshil10 on 3/5/18.
 */

public class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
    private ArrayList<Item> itemList;
    static Context c;


    // Constructor of the class
    public ItemArrayAdapter(int layoutId, ArrayList<Item> itemList,Context c) {
        listItemLayout = layoutId;
        this.itemList = itemList;
        this.c=c;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
         return itemList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(itemList.get(listPosition).getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // itemList.remove(listPosition);

                if(listPosition==0)
                {
                    notifyDataSetChanged();
                    itemList.remove(listPosition);
                }
                else
                {
                    itemList.remove(listPosition);
                    notifyItemRemoved(listPosition);}

            }
        });
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item;
        public ImageButton delete;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item = (TextView) itemView.findViewById(R.id.row_item);
            delete=(ImageButton) itemView.findViewById(R.id.delete);
        }
        @Override
        public void onClick(View view) {

            mToast(getLayoutPosition(),item.getText());
            Intent i=new Intent(c,ImageShow.class);
            i.putExtra("url",item.getText());
            c.startActivity(i);


            //Log.d("onclick", "onClick " + getLayoutPosition() + " " + item.getText());
            //Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show();
        }


        private void mToast(int layoutPosition, CharSequence text) {
            Toast.makeText(c,"Clicked"+ layoutPosition+text, Toast.LENGTH_SHORT).show();
        }
    }
}

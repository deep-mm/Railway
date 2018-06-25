package com.kjsce.train.cia.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Activity.CoachSearch;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;


public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder>{
    private final ArrayList<String> Mvalues;
    Context context;
    String placeOfInspection;
    Intent intent;

    public TrainAdapter() {
        Mvalues = null;

    }

    public TrainAdapter(ArrayList mvalues, Context c) {
        Mvalues = mvalues;
        context = c;

    }

    @Override
    public TrainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_train, parent, false);
        return new TrainAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(final TrainAdapter.ViewHolder holder, final int position) {

        SharedData sd = new SharedData(context);
        holder.train_name.setText(Mvalues.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputName();
            }
        });
    }

    public void getInputName() {

        new MaterialDialog.Builder(context)
                .title("Place")
                .content("Enter place of Inspection")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("Enter Text Here", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        placeOfInspection = input.toString();
                        if (!placeOfInspection.isEmpty()){
                            intent =new Intent(context,CoachSearch.class);
                            context.startActivity(intent);
                        }
                        else{
                            Toast.makeText(context,"Place of Inspection cannot be left empty",Toast.LENGTH_LONG).show();
                        }
                    }
                }).show();
    }

    @Override
    public int getItemCount() {
        return Mvalues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView train_name;

        public ViewHolder(View itemView) {
            super(itemView);
            train_name = (TextView) itemView.findViewById(R.id.train_name_text);
        }
    }
}
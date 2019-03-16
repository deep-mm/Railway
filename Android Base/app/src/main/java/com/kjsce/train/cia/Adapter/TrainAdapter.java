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
import com.andexert.library.RippleView;
import com.kjsce.train.cia.Activity.CoachSearch;
import com.kjsce.train.cia.Activity.SelectType;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder>{
    private final List<String> Mvalues;
    Context context;
    String placeOfInspection, type;
    Intent intent;
    SharedData sharedData;

    public TrainAdapter() {
        Mvalues = null;

    }

    public TrainAdapter(List mvalues, Context c, String type) {
        this.Mvalues = mvalues;
        this.context = c;
        this.type = type;
    }

    @Override
    public TrainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_train, parent, false);
        return new TrainAdapter.ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(final TrainAdapter.ViewHolder holder, final int position) {

        sharedData = new SharedData(context);
        String[] splited = Mvalues.get(position).split("\\s+");
        String str = "";
        holder.train_number.setText(splited[0]);
        for(int i=1;i<splited.length;i++)
        {
            str = str+" "+splited[i];
        }
        holder.train_name.setText(str);
        holder.rippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("train"))
                    getInputName(position);

                else{
                    sharedData.setBogie(Mvalues.get(position));
                    intent = new Intent(context,SelectType.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    public void getInputName(int position) {

        new MaterialDialog.Builder(context)
                .title("Place")
                .content("Enter place of Inspection")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("Enter Text Here", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        placeOfInspection = input.toString();
                        if (!placeOfInspection.isEmpty()){
                            sharedData.setTrain(Mvalues.get(position));
                            sharedData.setPlaceOfInspection(placeOfInspection);
                            intent =new Intent(context,CoachSearch.class);
                            context.startActivity(intent);
                        }
                        else{
                            Toast.makeText(context,"Place of Inspection cannot be left empty",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .canceledOnTouchOutside(false)
                .show();
    }

    @Override
    public int getItemCount() {
        if(Mvalues!=null)
            return Mvalues.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView train_name;
        final TextView train_number;
        final RippleView rippleView;

        public ViewHolder(View itemView) {
            super(itemView);
            train_name = (TextView) itemView.findViewById(R.id.train_name_text);
            train_number = (TextView) itemView.findViewById(R.id.train_name_text_number);
            rippleView = (RippleView) itemView.findViewById(R.id.ripple);
        }
    }
}
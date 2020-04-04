package com.example.android.covidapp;

import android.content.Context;
import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Complains> complaindata;

    public UserAdapter(Context c,ArrayList<Complains> complaindata)
    {
        this.context=c;
        this.complaindata=complaindata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.complains_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Complains complaindata=this.complaindata.get(position);

        holder.complains.setText(complaindata.getComp());
        holder.area.setText(complaindata.getLoc());


    }

    @Override
    public int getItemCount() {
        return complaindata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView complains,area;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            complains=itemView.findViewById(R.id.txtcomplainshow);
            area=itemView.findViewById(R.id.txtareashow);

        }
    }
}

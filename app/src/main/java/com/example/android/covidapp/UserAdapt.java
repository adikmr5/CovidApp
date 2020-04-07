package com.example.android.covidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapt extends RecyclerView.Adapter<UserAdapt.MyViewHolder> {

    private Context context;
    private ArrayList<Users> users;

    public UserAdapt(Context c,ArrayList<Users> users){
        this.context=c;
        this.users=users;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_items,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Users uss=this.users.get(position);

        holder.textName.setText(uss.getUsername());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textName=itemView.findViewById(R.id.uname);

        }
    }
}

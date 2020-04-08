package com.example.android.covidapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MessageAdapt extends RecyclerView.Adapter<MessageAdapt.MyViewHolder> {

    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_RIGHT=1;


    private Context context;
    private ArrayList<Chatdbm> chatdbms;

    FirebaseUser fuser;

    public MessageAdapt(Context c,ArrayList<Chatdbm> chatdbms){
        this.context=c;
        this.chatdbms=chatdbms;
    }




    @NonNull
    @Override
    public MessageAdapt.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_RIGHT) {


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapt.MyViewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapt.MyViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull MessageAdapt.MyViewHolder holder, int position) {

        Chatdbm chat=chatdbms.get(position);

        holder.show_message.setText(chat.getMessage());



    }

    @Override
    public int getItemCount() {
        return chatdbms.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView show_message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message=itemView.findViewById(R.id.show_message);

        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        if(chatdbms.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        }
        else
        {
            return MSG_TYPE_LEFT;
        }
    }
}

package com.example.android.covidapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    TextView usname;
    ImageButton send;
    EditText message;

    FirebaseUser fuser;
    DatabaseReference reference;

    MessageAdapt messageAdapt;
    ArrayList<Chatdbm> chatdbms;

    RecyclerView recyclerView;

    Intent intent;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView=findViewById(R.id.review);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        usname=findViewById(R.id.txtusername);
        send=findViewById(R.id.btnsend);
        message=findViewById(R.id.txtsend);

        intent=getIntent();
        final String userid=intent.getStringExtra("userid");
        fuser= FirebaseAuth.getInstance().getCurrentUser();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=message.getText().toString();
                if(!msg.equals(""))
                {
                    sendMessage(fuser.getUid(), userid ,msg);
                }
                else
                {
                    Toast.makeText(MessageActivity.this,"you can't send an empty text",Toast.LENGTH_SHORT).show();
                }
                message.setText("");
            }
        });


        reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users u=dataSnapshot.getValue(Users.class);
                usname.setText(u.getUsername());
                readMessage(fuser.getUid(),userid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendMessage(String sender,String reciever,String message)
    {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("reciever",reciever);
        hashMap.put("message",message);

        reference.child("Chats").push().setValue(hashMap);


    }

    private void readMessage(final String myid, final String userid)
    {
        chatdbms=new ArrayList<>();


        reference=FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatdbms.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Chatdbm ch=snapshot.getValue(Chatdbm.class);
                    if(ch.getReciever().equals(myid) && ch.getSender().equals(userid) || ch.getReciever().equals(userid) && ch.getSender().equals(myid))
                    {
                        chatdbms.add(ch);

                    }
                    messageAdapt=new MessageAdapt(MessageActivity.this, chatdbms);
                    recyclerView.setAdapter(messageAdapt);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

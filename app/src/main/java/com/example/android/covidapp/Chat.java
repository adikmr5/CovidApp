package com.example.android.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.lang.UScript;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapt userAdapt;
    private ArrayList<Users> users;
    DatabaseReference daref;

    DatabaseReference refer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        users = new ArrayList<Users>();

        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        daref = FirebaseDatabase.getInstance().getReference().child("Users");
        daref.addListenerForSingleValueEvent(valueEventListener);


    }

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Users us=dataSnapshot1.getValue(Users.class);

                    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                    if(!us.getId().equals(firebaseUser.getUid()))
                    {
                        users.add(us);

                    }



                }
                userAdapt=new UserAdapt(Chat.this,users);
                recyclerView.setAdapter(userAdapt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


}

package com.example.android.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CompToPolice extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Complains> complaindata;
    private UserAdapter userAdapter;
    private Button btn;

    DatabaseReference refe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_to_police);
        btn=findViewById(R.id.btnc);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(CompToPolice.this,MainActivity.class);
                startActivity(i);
            }
        });

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        complaindata = new ArrayList<Complains>();

        refe = FirebaseDatabase.getInstance().getReference().child("Complain");
        refe.addListenerForSingleValueEvent(valueEventListener);
    }


        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Complains complains=dataSnapshot1.getValue(Complains.class);
                    complaindata.add(complains);
                }
                userAdapter=new UserAdapter(CompToPolice.this,complaindata);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };



}

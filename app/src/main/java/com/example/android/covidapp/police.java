package com.example.android.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class police extends AppCompatActivity {

    EditText name,pid,area;
    Button autha;
    DatabaseReference ref;
    Members member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);

        name=(EditText)findViewById(R.id.txtname);
        pid=(EditText)findViewById(R.id.txtid);
        area=(EditText)findViewById(R.id.txtarea);
        autha=(Button) findViewById(R.id.btna);
        ref= FirebaseDatabase.getInstance().getReference().child("police");
        member=new Members();

        autha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ref.orderByChild("policeid1").equalTo(Integer.parseInt(pid.getText().toString().trim())).addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     if(dataSnapshot.exists())
                     {
                         Toast.makeText(police.this,"You are welcome",Toast.LENGTH_SHORT).show();
                         Intent j=new Intent(getApplicationContext(),Helping.class);
                         startActivity(j);
                     }
                     else
                     {

                         Toast.makeText(police.this,"Sorry you are not an authentic policman!!but you can register as volunteer",Toast.LENGTH_SHORT).show();
                     }
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             });
            }
        });

    }
}

package com.example.android.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Report extends AppCompatActivity {

    EditText complain,locality;
    Button btn;
    DatabaseReference reff;
    Complains compl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        complain=findViewById(R.id.etcom);
        locality=findViewById(R.id.etlocal);
        btn=findViewById(R.id.btncom);
        compl=new Complains();
        reff= FirebaseDatabase.getInstance().getReference().child("Complain");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compl.setComp(complain.getText().toString());
                compl.setLoc(locality.getText().toString());

                reff.push().setValue(compl);

                Toast.makeText(Report.this,"value added",Toast.LENGTH_SHORT).show();


            }
        });


    }
}

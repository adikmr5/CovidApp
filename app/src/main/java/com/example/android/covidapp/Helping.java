package com.example.android.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Helping extends AppCompatActivity {
    Button complaindata;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helping);
        complaindata=findViewById(R.id.btnhelp);

        complaindata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Helping.this,CompToPolice.class);
                startActivity(k);
            }
        });
    }
}

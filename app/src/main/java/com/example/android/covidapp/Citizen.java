package com.example.android.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class Citizen extends AppCompatActivity {
    public Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen);
        button=(Button)findViewById(R.id.btncitizen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Citizen.this,actvityDetails.class);
                startActivity(intent);
            }
        });
    }
}

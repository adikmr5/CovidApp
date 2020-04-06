package com.example.android.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void citizen(View view){
        Intent activity2Intent = new Intent(getApplicationContext(), page.class);
        startActivity(activity2Intent);
    }

    public void police(View view){
        Intent i=new Intent(getApplicationContext(), police.class);
        startActivity(i);

    }


    public void help(View view)
    {
        Intent j=new Intent(getApplicationContext(), Report.class);
        startActivity(j);
    }






}

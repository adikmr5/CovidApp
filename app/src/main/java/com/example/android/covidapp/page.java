package com.example.android.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class page extends AppCompatActivity {
    Button Register, Login;

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null)
        {
            Intent k=new Intent(page.this,Citizen.class);
            startActivity(k);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);









        Register = findViewById(R.id.buttonRegister);
        Login = findViewById(R.id.buttonLogin);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(page.this, CitizenRegister.class);
                startActivity(i);


            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y = new Intent(page.this, CitizenLogin.class);
                startActivity(y);
            }
        });


    }
}

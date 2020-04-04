package com.example.android.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Volunteer extends AppCompatActivity {

    private EditText emailid,pswd;
    private Button regst;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        emailid=findViewById(R.id.etmail);
        pswd=findViewById(R.id.etpswd);
        regst=findViewById(R.id.btn);

        mAuth=FirebaseAuth.getInstance();

        regst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailid.getText().toString();
                String password=pswd.getText().toString();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Volunteer.this,"you are registered",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            String message=task.getException().toString();
                            Toast.makeText(Volunteer.this,"Error Occurred"+message,Toast.LENGTH_SHORT).show();
                        }
                    }
                });









            }
        });
    }






}

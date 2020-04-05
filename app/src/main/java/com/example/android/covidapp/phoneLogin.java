package com.example.android.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class phoneLogin extends AppCompatActivity {
    private EditText textphone;
    private TextView textviewphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        textphone=findViewById(R.id.textphone);
        textviewphone=findViewById(R.id.textviewphone);
        findViewById(R.id.btnphone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code= textviewphone.getText().toString().trim();
                String number=textphone.getText().toString().trim();
                if(number.isEmpty() || number.length()<10)
                {
                    textphone.setError(" Valid number is required!");
                    textphone.requestFocus();
                    return;
                }
                String phoneNumber="+"+code+number;
                Intent intent=new Intent(phoneLogin.this,phoneVerify.class);
                intent.putExtra("phonenumber",phoneNumber);
                startActivity(intent);


            }
        });
    }

}

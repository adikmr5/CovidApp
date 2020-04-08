package com.example.android.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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


                if(TextUtils.isEmpty(complain.getText()))
                {
                    complain.setError("Please Enter your complain");
                    Toast.makeText(Report.this,"please enter your complain",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(locality.getText()))
                {
                    locality.setError("Please Enter your address and locality");
                    Toast.makeText(Report.this,"please enter your address and locality",Toast.LENGTH_SHORT).show();
                    return;

                }
                else if(TextUtils.isEmpty(locality.getText()) && TextUtils.isEmpty(complain.getText()) )
                {

                    Toast.makeText(Report.this,"please fill details",Toast.LENGTH_SHORT).show();
                    return;

                }
                else {
                    compl.setComp(complain.getText().toString());
                    compl.setLoc(locality.getText().toString());

                    reff.push().setValue(compl);

                    Toast.makeText(Report.this, "value added", Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(Report.this,AfterReport.class);
                    startActivity(i);
                }


            }
        });


    }
}

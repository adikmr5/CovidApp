package com.example.android.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class actvityDetails extends AppCompatActivity {
    EditText dname,dage,dtravel,dresp,dphyc,dcontact,dgender;
    RadioButton dmale,dfemale;
    RadioGroup radio;
    Button btnnext;
    DatabaseReference reff;
    Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actvity_details);
        dname=(EditText)findViewById(R.id.dname);
        radio=(RadioGroup)findViewById(R.id.radio);
        dage=(EditText)findViewById(R.id.dage);
        dcontact =(EditText)findViewById(R.id.dcontact);
        dtravel=(EditText)findViewById(R.id.dtravel);
        dresp=(EditText)findViewById(R.id.dresp);
        dphyc=(EditText)findViewById(R.id.dphyc);
        dmale=(RadioButton) findViewById(R.id.dmale);
        dfemale=(RadioButton) findViewById(R.id.dfemale);
        patient=new Patient();
        reff= FirebaseDatabase.getInstance().getReference().child("Patient");
        btnnext=(Button)findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                patient.setName(dname.getText().toString().trim());
                int agee=Integer.parseInt(dage.getText().toString().trim());
                Long phn=Long.parseLong(dcontact.getText().toString().trim());
                patient.setAge(agee);
                patient.setPhone(phn);
                String gender="";
                if(dmale.isChecked()) {
                    gender = "Male";
                }
                if(dfemale.isChecked())
                {
                    gender="Female";
                }
                patient.setGender(gender.trim());

                patient.setTravel(dtravel.getText().toString().trim());
                patient.setIllness(dresp.getText().toString().trim());
                patient.setPhycontact(dphyc.getText().toString().trim());


                reff.push().setValue(patient);

            }
        });


    }


}

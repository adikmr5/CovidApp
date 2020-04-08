package com.example.android.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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
    EditText dname,dage,dtravel,dresp,dphyc,dcontact,dgender,hospitalNmae;
    RadioButton dmale,dfemale;
    RadioGroup radio;
    Button btnnext,btnbck;
    DatabaseReference reff;
    Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actvity_details);
        hospitalNmae=findViewById(R.id.hosname);
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


                if (TextUtils.isEmpty(dname.getText())) {
                    dname.setError("Name is required!");
                    Toast.makeText(actvityDetails.this, "Please fill your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(hospitalNmae.getText())) {
                    hospitalNmae.setError("please enter your hospital name");
                    Toast.makeText(actvityDetails.this, "Please fill Hospital name", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(dcontact.getText())) {
                    dcontact.setError("please enter your contact details");
                    Toast.makeText(actvityDetails.this, "Please fill your contact details", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(dname.getText()) || TextUtils.isEmpty(hospitalNmae.getText())|| radio.getCheckedRadioButtonId()==-1
                        ||TextUtils.isEmpty(dcontact.getText()) || TextUtils.isEmpty(dage.getText())
                || TextUtils.isEmpty(dcontact.getText()) || TextUtils.isEmpty(dresp.getText()) || TextUtils.isEmpty(dphyc.getText())
                || (TextUtils.isEmpty(dmale.getText()) && TextUtils.isEmpty(dfemale.getText())))
                {
                    Toast.makeText(actvityDetails.this, "Please Fill in the details", Toast.LENGTH_SHORT).show();
                    return;
                } else {


                    patient.setName(dname.getText().toString().trim());

                    patient.setHospitalname(hospitalNmae.getText().toString().trim());
                    int age = Integer.parseInt(dage.getText().toString());
                    Long phn = Long.parseLong(dcontact.getText().toString().trim());


                    patient.setAge(age);
                    patient.setPhone(phn);
                    String gender = "";
                    if (dmale.isChecked()) {
                        gender = "Male";
                    }
                    if (dfemale.isChecked()) {
                        gender = "Female";
                    }
                    patient.setGender(gender.trim());

                    patient.setTravel(dtravel.getText().toString().trim());
                    patient.setIllness(dresp.getText().toString().trim());
                    patient.setPhycontact(dphyc.getText().toString().trim());


                    reff.push().setValue(patient);
                    Toast.makeText(actvityDetails.this, "Your Value is Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(actvityDetails.this, afterSubmit.class);
                    startActivity(intent);


                }
            }
        });

        btnbck=(Button)findViewById(R.id.btnbck);
        btnbck.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(actvityDetails.this,NearByHospitals.class);
                startActivity(intent);

            }
        });




    }


}

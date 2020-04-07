package com.example.android.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;
import java.util.HashMap;

public class CitizenRegister extends AppCompatActivity {

    EditText username,email,password;
    Button regb;
    FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_register);

        username=findViewById(R.id.etname);
        email=findViewById(R.id.etmail);
        password=findViewById(R.id.etpass);
        regb=findViewById(R.id.regbut);

        auth=FirebaseAuth.getInstance();

        regb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username=username.getText().toString();
                String txt_email=email.getText().toString();
                String txt_password=password.getText().toString();

                if(TextUtils.isEmpty(txt_username)||TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(CitizenRegister.this,"please fill the required information",Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length()<6)
                {
                    Toast.makeText(CitizenRegister.this,"password must be at least 6 characters",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(CitizenRegister.this,"Just wait for few seconds",Toast.LENGTH_SHORT).show();

                    register(txt_username,txt_email,txt_password);
                }
            }
        });

    }

    private void register(final String username, String email, String password)
    {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser firebaseUser=auth.getCurrentUser();
                    assert firebaseUser != null;
                    String userid=firebaseUser.getUid();

                    reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);

                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("id",userid);
                    hashMap.put("username",username);


                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Intent k =new Intent(CitizenRegister.this,page.class);
                                startActivity(k);
                                Toast.makeText(CitizenRegister.this,"you are now register go back to login page and login ",Toast.LENGTH_SHORT).show();
                                finish();

                            }
                            else
                            {
                                Toast.makeText(CitizenRegister.this,"you can't register ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}

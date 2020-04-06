package com.example.android.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class phoneVerify extends AppCompatActivity {
    private Button btnback;
    private String verificationId;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;
    private EditText textcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);
        textcode=findViewById(R.id.textcode);
        mAuth=FirebaseAuth.getInstance();
        progressbar=findViewById(R.id.progressbar);

        String phonenumber=getIntent().getStringExtra("phonenumber");
        sendVerificationCode(phonenumber);

        findViewById(R.id.btndone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=textcode.getText().toString().trim();
                if(code.isEmpty())
                {
                    textcode.setError("Enter code!!");
                    textcode.requestFocus();
                    return;
                }

                verifyCode(code);

            }
        });

       btnback=(Button)findViewById(R.id.btnback);
       btnback.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intentback=new Intent(phoneVerify.this,phoneLogin.class);
               startActivity(intentback);
           }
       });

    }

    private void verifyCode(String code){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
                signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intentneww=new Intent(phoneVerify.this,Report.class);
                            startActivity(intentneww);

                        }
                        else
                        {
                            Toast.makeText(phoneVerify.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String number)
    {
        progressbar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            String code= phoneAuthCredential.getSmsCode();
            if(code!=null){
                textcode.setText(code);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(phoneVerify.this,e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };

}



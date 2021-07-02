package com.example.demo;

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

public class Register extends AppCompatActivity {

     EditText email,passeord;
     Button register;
     FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.editTextTextPersonName);
        passeord=findViewById(R.id.editTextTextPersonName2);
        register=findViewById(R.id.button3);
        getSupportActionBar().hide();
       auth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email=email.getText().toString();
                String txt_password=passeord.getText().toString();
           if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
               Toast.makeText(Register.this, "Empty Credential", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this,MainActivity.class));
                finish();
           }
           else if(txt_password.length()<6){
               Toast.makeText(Register.this, "Password is to short", Toast.LENGTH_SHORT).show();
           }
           else {
               resgisterUser(txt_email,txt_password);
           }
            }
        });
    }

    private void resgisterUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Regestring user sucessful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Register.this, "Regestration failed", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
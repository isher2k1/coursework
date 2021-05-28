package com.isher.skytodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegActivity extends AppCompatActivity {

    private EditText regEmail, regPass;
    private Button regButt;
    private TextView regLink;
    //private Toolbar toolbar;
    private FirebaseAuth mAuth;

    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reg);


//        toolbar = findViewById(R.id.loginToolBar);
//
//        getSupportActionBar().setTitle("Login");
//        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        loader = new ProgressDialog(this);

        regEmail = findViewById(R.id.regEmail);
        regPass = findViewById(R.id.regPass);
        regButt = findViewById(R.id.regButton);
        regLink = findViewById(R.id.linkForLogin);

        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });

        regButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regEmail.getText().toString().trim();
                String pass = regPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    regEmail.setError("Email is required!");
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    regPass.setError("Password is required!");
                    return;
                }else {
                    loader.setMessage("Registration in progress...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(RegActivity.this, HeroAddActivity.class);
                            startActivity(intent);
                            finish();
                            loader.dismiss();
                        } else {
                            String error = task.getException().toString();
                            Toast.makeText(RegActivity.this, "Registration failed!" + error, Toast.LENGTH_SHORT).show();
                            loader.dismiss();
                          }

                    }
                    });
                }
            }
        });
    }
}
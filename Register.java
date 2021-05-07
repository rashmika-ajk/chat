package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Register extends AppCompatActivity {
    Button register, login;
    EditText username, email0, password0, cp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.register);
        register = (Button) findViewById(R.id.register);
        //login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.user);
        email0 = (EditText) findViewById(R.id.email);
        password0 = (EditText) findViewById(R.id.password);
        cp = (EditText) findViewById(R.id.cp);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegister();
            }
        });
    }

    private void UserRegister() {
        String Name, Email, Password;
        Name = username.getText().toString().trim();
        Email = email0.getText().toString().trim();
        Password = password0.getText().toString().trim();

        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(Register.this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(Email)) {
            Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(Password)) {
            Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        } else if (Password.length() < 6) {
            Toast.makeText(Register.this, "Password must be greater then 6 digit", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),
                            "Registration successful!",
                            Toast.LENGTH_LONG)
                            .show();

                    // hide the progress bar

                    // if the user created intent to login activity
                    Intent intent
                            = new Intent(Register.this,
                            MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(Register.this, "error on creating user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

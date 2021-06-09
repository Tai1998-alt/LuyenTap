package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainLogin extends AppCompatActivity {

    EditText editEmail, editPassWord;
    Button btnSignIn;
    TextView txtExit;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        editEmail = findViewById(R.id.editEmail);
        editPassWord = findViewById(R.id.editPassWord);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtExit = findViewById(R.id.txtExit);
        auth = FirebaseAuth.getInstance();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void SignIn()
    {
        String email = String.valueOf(editEmail.getText());
        String password = String.valueOf(editPassWord.getText());
        if(email.isEmpty())
        {
            editEmail.setError("Not Empty");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editPassWord.setError("Not Empty");
            editPassWord.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editPassWord.setError("Need than character six");
            editPassWord.requestFocus();
            return;
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(MainLogin.this, MainActivity.class));
                        }
                        else {
                            new AlertDialog.Builder(MainLogin.this)
                                    .setTitle("Confirm")
                                    .setMessage("Failed")
                                    .setPositiveButton("YES",null).show();
                            }
                    }
                });
    }
}
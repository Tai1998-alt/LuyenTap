package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainLogin extends AppCompatActivity {

    EditText editEmail, editPassWord;
    Button btnSignIn;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        editEmail = findViewById(R.id.editEmail);
        editPassWord = findViewById(R.id.editPassWord);
        btnSignIn = findViewById(R.id.btnSignIn);
        auth = FirebaseAuth.getInstance();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });
    }
    private void SignIn()
    {
        String email = String.valueOf(editEmail.getText());
        String password = String.valueOf(editPassWord.getText());
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
                                    .setTitle("Thong Bao")
                                    .setMessage("Khong Thanh Cong")
                                    .setPositiveButton("YES",null).show();
                            }
                    }
                });
    }
}
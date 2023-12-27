package com.example.reclect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import Utils.httpRequests;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        Button btnLogIn = findViewById(R.id.button2);
        Button btnCreate = findViewById(R.id.buttonCreate);

        editEmail = findViewById(R.id.emailEditText);
        editPassword = findViewById(R.id.passwordEditText);
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EmailPassword(email, password)) {
                    LoginUser(email, password);
                }
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EmailPassword(email, password)) {
                    RegisterUser(email, password);
                }
            }
        });
    }

    private boolean EmailPassword(String email, String password) {
        if ((email.equals("")) && !(password.equals(""))) {
            Toast.makeText(this, "Enter your email and password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void callRecordActivity() {
        Intent intent = new Intent(MainActivity.this, RecordActivity.class);
        MainActivity.this.startActivity(intent);
    }

    private void RegisterUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callRecordActivity();
                } else {
                    Toast.makeText(MainActivity.this, "Authentication failed. Please try again",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void LoginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callRecordActivity();
                        } else {
                                // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
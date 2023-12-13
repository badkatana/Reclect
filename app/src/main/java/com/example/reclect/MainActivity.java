package com.example.reclect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button btnLogIn, btnCreate;
    FirebaseAuth mAuth;
    EditText editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        btnLogIn = findViewById(R.id.button2);
        btnCreate = findViewById(R.id.buttonCreate);

        btnLogIn.setOnClickListener(view -> LoginUser());
        btnCreate.setOnClickListener(view -> RegisterUser());

    }

    private void RegisterUser() {
        editEmail = findViewById(R.id.emailEditText);
        editPassword = findViewById(R.id.passwordEditText);
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (!(email.equals("")) && !(password.equals(""))) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
//                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Enter your email and password", Toast.LENGTH_SHORT).show();
        }
    };

    private void LoginUser() {
        editEmail = findViewById(R.id.emailEditText);
        editPassword = findViewById(R.id.passwordEditText);
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (!(email.equals("")) && !(password.equals(""))) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                                MainActivity.this.startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Enter your email and password", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.reclect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnLogIn;
    Dialog login;
    Button btnDialog_cancel, getBtnDialog_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogIn = findViewById(R.id.button2);

        btnLogIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }
    private void showDialog(){
        login = new Dialog(this);
        login.setContentView(R.layout.login_dialog);
        getBtnDialog_login = login.findViewById(R.id.btn_log);
        getBtnDialog_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // confirm the login in account
                Intent recordAct;
                recordAct = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(recordAct);
            }
        });
        login.show();
    }


}
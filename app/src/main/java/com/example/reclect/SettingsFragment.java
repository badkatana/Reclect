package com.example.reclect;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.reclect.databinding.FragmentLecturesBinding;
import com.example.reclect.databinding.FragmentSettingsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileWriter;
import java.util.Locale;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSettingsBinding binding =  FragmentSettingsBinding.inflate(inflater, container, false);
        Button sendButton = binding.getRoot().findViewById(R.id.sendButton);
        EditText et = binding.getRoot().findViewById(R.id.et_message);
        TextView tx = binding.getRoot().findViewById(R.id.emailUser);
        String name = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        tx.setText(name);

        sendButton.setOnClickListener(view -> {
            String text = et.getText().toString();
            if (!text.equals(""))
            {
                sendToFireBase(name, text);
            }});

        return binding.getRoot();
    }

    //get all user conspects
    public void sendToFireBase(String email, String text) {
        try {
            String filename = email + "_support.txt";
            File file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    filename);
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();

            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("support/"+filename);
            storageReference.putFile(Uri.fromFile(file)).addOnSuccessListener(taskSnapshot -> {
                Log.d("firebase", "success");
                file.delete();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package com.example.reclect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import com.example.reclect.databinding.ActivityRecordBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecordActivity extends AppCompatActivity {
    ActivityRecordBinding binding;
    public static final int REQUEST_AUDIO = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setFragments(new RecordFragment());
        binding.BottomNavigationView.setBackground(null);

        binding.BottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.homerecord) {
                setFragments(new RecordFragment());
            } else if (itemId == R.id.lectures) {
                setFragments(new LecturesFragment());
            } else if (itemId == R.id.settings) {
                setFragments(new SettingsFragment());
            }
            return true;
        });
        if (!checkRecordPermission())
        {
            AskRecordPermission();
        }
    }

    public void setFragments (Fragment frgmt) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, frgmt);
        fragmentTransaction.commit();
    }

    public void AskRecordPermission() {
        ActivityCompat.requestPermissions(RecordActivity.this,
                new String[] {Manifest.permission.RECORD_AUDIO},
                REQUEST_AUDIO);
    }

    public boolean checkRecordPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED){
            AskRecordPermission();
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
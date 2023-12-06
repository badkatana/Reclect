package com.example.reclect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.reclect.databinding.ActivityRecordBinding;

public class RecordActivity extends AppCompatActivity {
    ActivityRecordBinding binding;

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
    }

    private void setFragments (Fragment frgmt) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, frgmt);
        fragmentTransaction.commit();
    }

    private void AskPermissions() {
        requestPermissions(new String[] {Manifest.permission.RECORD_AUDIO}, 1);

    }

}
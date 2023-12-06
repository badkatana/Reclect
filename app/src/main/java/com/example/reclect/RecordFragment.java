package com.example.reclect;

import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.Manifest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.IOException;
import java.security.Permission;


public class RecordFragment extends Fragment {

    private int AUDIO_PERMISSION_CODE = 1;

    public RecordFragment() {
        // Required empty public constructor
    }
    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ImageButton mainButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        mainButton = (ImageButton) view.findViewById(R.id.imageButton2);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecord();
            }
        });
        return view;
    }

    private void CheckPermissions() {
        boolean recAudio = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        boolean writeMemory = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void startRecord() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
        {
            // поменять цвет кнопки
            String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            String name = "/example.3gp"; //change

            MediaRecorder mRecord = new MediaRecorder();
            mRecord.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecord.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecord.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecord.setOutputFile(name);
            try {
                mRecord.prepare();
            } catch (IOException e) {
                Log.e("TAG", "prepare() failed");
            }
            // start method will start
            // the audio recording.
            mRecord.start();
        } else {
            Log.d("Permission", "Not Granted");
        }
    }
}
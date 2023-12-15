package com.example.reclect;

import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
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
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.security.Permission;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
    int seconds;
    int RECORD_TIME_LIMIT_IN_SECONDS = 60000 * 120; // 2 hours
    boolean isRecording = false;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        mainButton = (ImageButton) view.findViewById(R.id.imageButton2);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordingProcess();
            }
        });
        return view;
    }

    private void CheckPermissions() {
        boolean recAudio = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        boolean writeMemory = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private String getRecordingFilePath() {
        ContextWrapper contextWrapper = new ContextWrapper(getActivity().getApplicationContext());
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString();
        String id = UUID.randomUUID().toString();
        File newFile = new File(path, id + ".mp3");
        return newFile.getPath();
    }

    public String pat;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    private void recordingProcess() {
        if (((RecordActivity)getActivity()).checkRecordPermission()) {
            if (!isRecording) {
                isRecording = true;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        mediaRecorder = new MediaRecorder();
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        pat = getRecordingFilePath();
                        mediaRecorder.setOutputFile(pat);
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mediaRecorder.setMaxDuration(RECORD_TIME_LIMIT_IN_SECONDS);
                        Log.e("Record", "Recording started");
                        try {
                            mediaRecorder.prepare();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                        mediaRecorder.start();
                    }
                });
            } else
            {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        mediaRecorder.stop();
                        mediaRecorder.release();
                        mediaRecorder = null;
                        isRecording = false;
                        Log.d("Record", "Recording ended");

                        sendML();
                    }
                });
            }
        }
    }

    private void sendML(){

    }
}
package com.example.reclect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.reclect.databinding.FragmentLecturesBinding;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Data.ConspectAdapter;
import Data.DataBaseHandler;
import Model.Conspect;

public class LecturesFragment extends Fragment {

    FragmentLecturesBinding binding;
    FirebaseStorage storage;
    StorageReference storageRef;
    public LecturesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentLecturesBinding.inflate(inflater, container, false);

        ConspectAdapter adapt = new ConspectAdapter(getContext(), R.layout.conspect_item, getData());
        ListView listView = binding.getRoot().findViewById(R.id.conspect_view);
        listView.setAdapter(adapt);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private ArrayList<Conspect> getData(){
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        DataBaseHandler dataBaseHandler = new DataBaseHandler(getContext());;
        StorageReference fileRef = storageRef.child("starting files/startingFile.pdf");
        File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), "First Step.pdf");
        if (!localFile.exists()) {
            fileRef.getFile(localFile);
            Conspect conspect = new Conspect(0, localFile.getName());
            dataBaseHandler.AddConspect(conspect);
        }
        return dataBaseHandler.getAllConspects();
    }
}
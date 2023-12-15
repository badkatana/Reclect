package com.example.reclect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reclect.databinding.FragmentLecturesBinding;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
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
        listView.setClickable(true);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            String text = adapt.getItem(i).getConspectName();
            Log.d("", text);

            ViewConspFragment viewConspFragment = new ViewConspFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1", text);
            viewConspFragment.setArguments(bundle);
            ((RecordActivity)getActivity()).setFragments(viewConspFragment);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private ArrayList<Conspect> getData() {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference("starting files/startingFile.pdf");
        File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "/First Step.pdf");
        DataBaseHandler dbHandler = new DataBaseHandler(getContext());

        if (!localFile.exists()){
            storageRef.getFile(localFile);
            Conspect conspect = new Conspect(0, localFile.getName());
            dbHandler.AddConspect(conspect);
        }

        Conspect t = new Conspect(1, "Norma.pdf");
        dbHandler.AddConspect(t);
        return dbHandler.getAllConspects();
    }
}
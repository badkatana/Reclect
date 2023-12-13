package com.example.reclect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.reclect.databinding.FragmentLecturesBinding;

import java.util.ArrayList;
import Data.ConspectAdapter;
import Model.Conspect;

public class LecturesFragment extends Fragment {

    FragmentLecturesBinding binding;
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
        ArrayList<Conspect> conspects = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Conspect conspect = new Conspect(i, "ConSpectus");
            conspects.add(conspect);
        }
        return conspects;
    }
}
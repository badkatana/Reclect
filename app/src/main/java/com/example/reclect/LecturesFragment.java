package com.example.reclect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LecturesFragment extends Fragment {

    public LecturesFragment() {
        // Required empty public constructor
    }

    public static LecturesFragment newInstance(String param1, String param2) {
        LecturesFragment fragment = new LecturesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lectures, container, false);
    }
}
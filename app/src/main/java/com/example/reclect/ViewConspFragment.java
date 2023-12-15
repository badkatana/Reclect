package com.example.reclect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

import Data.DataBaseHandler;
import Model.Conspect;

public class ViewConspFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private String mParam2;

    public ViewConspFragment() {
        // Required empty public constructor
    }

    PDFView pdfView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pdfView = getView().findViewById(R.id.pdfShow);
        File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), mParam1);
        if (localFile.exists()) {
            pdfView.fromFile(localFile)
                    .defaultPage(0)
                    .scrollHandle(new DefaultScrollHandle(getContext()))
                    .spacing(10)
                    .load();
        } else {
            DataBaseHandler dbHandler = new DataBaseHandler(getContext());
            dbHandler.deleteConspect(mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_consp, container, false);
    }
}
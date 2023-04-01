package com.safeinvest.safetrax;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.Toast;

public class termsncondi extends Fragment {
    RadioButton nri,domestic;
    TableLayout ntable,dtable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_termsncondi, container, false);
        nri=(RadioButton) view.findViewById(R.id.nri);
        domestic=(RadioButton) view.findViewById(R.id.domestic);
        ntable=(TableLayout)view.findViewById(R.id.nritable);
        dtable=(TableLayout)view.findViewById(R.id.domestictable);

        return view;
    }
}
package com.example.nmbapp;

import static com.example.nmbapp.env.SelectedDriver;
import static com.example.nmbapp.env.SelectedRoute;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class routeFragment extends Fragment {

    TextView rinfo;
    TextView dinfo;
    Button logoutBtn;
    Intent intent;
    public routeFragment(Intent intent) {
        this.intent = intent;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_route, container, false);
        rinfo = view.findViewById(R.id.profileRecentRoute);
        dinfo = view.findViewById(R.id.profileRecentDriver);
        logoutBtn = view.findViewById(R.id.logoutButton);
        rinfo.setText(SelectedRoute);
        dinfo.setText(SelectedDriver);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        return view;
    }
}
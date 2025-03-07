package com.example.nmbapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nmbapp.databinding.ActivityHomeMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home_Main extends AppCompatActivity {

    ActivityHomeMainBinding binding;
    FloatingActionButton fab;
    ArrayList<DriverBusRoute> bral = new ArrayList<>();
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setBackground(null);
        Fragment mapsFragment = new MapsFragment(Home_Main.this, bral);
        replaceFragment(mapsFragment);
        fab = binding.floatingBtn;
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                replaceFragment(mapsFragment);
            }
            else if (itemId == R.id.library) {
                replaceFragment(new routeFragment(new Intent(Home_Main.this, MainActivity2.class)));
            }

            return true;
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RouteSelectionDialog.showRouteDriverStopDialog(Home_Main.this, bral);
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


//    @Override
//    public void onMessageReceived(String message) {
//
//    }
}
package com.example.nmbapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Map;

public class Home_page extends AppCompatActivity {
    Button viewBuses, trackBuses, settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewBuses = findViewById(R.id.ViewBusesBtn);
        trackBuses = findViewById(R.id.TrackBusesBtn);
        settings = findViewById(R.id.SettingsBtn);

        viewBuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_page.this, ViewBuses.class);
                startActivity(intent);
            }
        });
        trackBuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] p = listRequestedPermissions();
                if(p == null){
                    Log.d("Permissions", "No Perms Required");
                    launchMaps();
                    return;
                }
                PermManger2(Home_page.this, p);
            }
        });
    }
    private void PermManger2(Context context, String[] perm) {
        Boolean[] granted = new Boolean[perm.length];
        int ungrantedPermsCount = 0;

        // Loop through permissions and check each
        for (int i = 0; i < perm.length; i++) {
            if (ContextCompat.checkSelfPermission(context, perm[i]) == PackageManager.PERMISSION_GRANTED) {
                granted[i] = true;
            } else {
                granted[i] = false;
                ungrantedPermsCount++;
            }
        }

        // If all permissions are already granted
        if (ungrantedPermsCount == 0) {
            Toast.makeText(context, "All permissions granted", Toast.LENGTH_SHORT).show();
            launchMaps(); // Launch the dial pad activity here
            return;
        }

        // Collect ungranted permissions
        String[] reqPerms = new String[ungrantedPermsCount];
        int index = 0;
        for (int i = 0; i < perm.length; i++) {
            if (!granted[i]) {
                reqPerms[index++] = perm[i];
            }
        }

        // Request the ungranted permissions
        mulReqLauncher.launch(reqPerms);
    }
    private ActivityResultLauncher<String[]> mulReqLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                boolean allGranted = true;

                // Check the result of each permission
                for (Map.Entry<String, Boolean> entry : result.entrySet()) {
                    if (!entry.getValue()) {
                        allGranted = false;
                        Toast.makeText(Home_page.this, "Permission " + entry.getKey() + " not granted", Toast.LENGTH_SHORT).show();
                    }
                }

                // If all permissions are granted, proceed to the next activity
                if (allGranted) {
                    launchMaps();
                } else {
                    Toast.makeText(Home_page.this, "Some permissions are not granted", Toast.LENGTH_SHORT).show();
                }
            }
    );

    private String[] listRequestedPermissions() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = packageInfo.requestedPermissions;
            if (requestedPermissions != null) {
                for (String permission : requestedPermissions) {
                    System.out.println("Requested permission: " + permission);
                }
            }
            return requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void launchMaps(){
        Intent intent = new Intent(Home_page.this, BusMaps.class);
        startActivity(intent);
    }
}
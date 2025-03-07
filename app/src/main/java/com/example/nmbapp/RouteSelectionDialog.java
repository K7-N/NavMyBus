package com.example.nmbapp;

import static com.example.nmbapp.env.SelectedDriver;
import static com.example.nmbapp.env.SelectedRoute;
import static com.example.nmbapp.env.SelectedStop;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class RouteSelectionDialog {

    public static void showRouteDriverStopDialog(Context context, List<DriverBusRoute> routes) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog, null);

        Spinner routeSpinner = dialogView.findViewById(R.id.spinner_route);
        Spinner driverSpinner = dialogView.findViewById(R.id.spinner_driver);
        Spinner stopSpinner = dialogView.findViewById(R.id.spinner_stop);

        Set<String> uniqueRouteNames = new HashSet<>();
        for (DriverBusRoute route : routes) {
            uniqueRouteNames.add(route.routeName);
        }

        List<String> routeNames = new ArrayList<>(uniqueRouteNames);
        ArrayAdapter<String> routeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, routeNames);
        routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeSpinner.setAdapter(routeAdapter);
        if (SelectedRoute != null && routeNames.contains(SelectedRoute)){
            routeSpinner.setSelection(routeNames.indexOf(SelectedRoute));
        }
        routeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRouteName = routeNames.get(position);

                List<String> driverNames = new ArrayList<>();
                for (DriverBusRoute route : routes) {
                    if (route.routeName.equals(selectedRouteName)) {
                        driverNames.add(route.name);
                    }
                }
                ArrayAdapter<String> driverAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, driverNames);
                driverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                driverSpinner.setAdapter(driverAdapter);
                if(SelectedDriver != null && driverNames.contains(SelectedDriver)){
                    driverSpinner.setSelection(driverNames.indexOf(SelectedDriver));
                }
                List<String> stopDescriptions = new ArrayList<>();
                for (DriverBusRoute route : routes) {
                    if (route.routeName.equals(selectedRouteName)) {
                        for (BusStop stop : route.stops) {
                            stopDescriptions.add(stop.description);
                        }
                        break;
                    }
                }
                ArrayAdapter<String> stopAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, stopDescriptions);
                stopAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                stopSpinner.setAdapter(stopAdapter);
              if(SelectedStop != null && stopDescriptions.contains(SelectedStop)) {
                    stopSpinner.setSelection(stopDescriptions.indexOf(SelectedStop));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Create and show the dialog
        new AlertDialog.Builder(context)
                .setTitle("Select Route, Driver, and Stop")
                .setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> {
                    SelectedRoute = routeSpinner.getSelectedItem().toString();
                    SelectedDriver = driverSpinner.getSelectedItem().toString();
                    SelectedStop = stopSpinner.getSelectedItem().toString();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
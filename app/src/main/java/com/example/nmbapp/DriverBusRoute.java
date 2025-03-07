package com.example.nmbapp;

import java.util.ArrayList;
import java.util.List;

public class DriverBusRoute {
    String name;
    String email;
    String busNo;
    String driverId;
    String routeId;
    String routeName;

    public DriverBusRoute(String name, String email, String busNo, String driverId, String routeId, String routeName, ArrayList<BusStop> stops) {
        this.name = name;
        this.email = email;
        this.busNo = busNo;
        this.driverId = driverId;
        this.routeId = routeId;
        this.routeName = routeName;
        this.stops = stops;
    }

    ArrayList<BusStop> stops;
}
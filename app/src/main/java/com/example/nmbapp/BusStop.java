package com.example.nmbapp;


class BusStop {
    double latitude;
    double longitude;

    public BusStop(double latitude, double longitude, String time, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.description = description;
    }

    String time;
    String description;
}
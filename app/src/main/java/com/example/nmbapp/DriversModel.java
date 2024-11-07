package com.example.nmbapp;

public class DriversModel {
    String id;
    String Bus;
    String route;
    String Name;
    String Time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBus() {
        return Bus;
    }

    public void setBus(String bus) {
        Bus = bus;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public DriversModel(String id, String bus, String route, String name) {
        this.id = id;
        Bus = bus;
        this.route = route;
        Name = name;
    }
}

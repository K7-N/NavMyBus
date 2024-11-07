package com.example.nmbapp;

import static com.example.nmbapp.env.apikey;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

public class MapUtils {

        public static String buildRequestUrl(ArrayList<GeoPoint> geoPoints) {
            // Base URL for the API
            StringBuilder urlBuilder = new StringBuilder("https://graphhopper.com/api/1/route?");

            // Append each GeoPoint as a "point" parameter
            for (GeoPoint point : geoPoints) {
                urlBuilder.append("point=")
                        .append(point.getLatitude())
                        .append(",")
                        .append(point.getLongitude())
                        .append("&");
            }

            // Append other fixed parameters
            urlBuilder.append("profile=car")
                    .append("&locale=en")
                    .append("&calc_points=true")
                    .append("&points_encoded=false")
                    .append("&details=road_class")
                    .append("&details=surface")
                    .append("&instructions=false")
                    .append("&key=")
                    .append(apikey);

            return urlBuilder.toString();
        }

    public Polyline getPolyline(String response) {

        try {

            JSONObject jsonResponse = new JSONObject(response);

            JSONArray coordinates = jsonResponse.getJSONArray("paths")
                    .getJSONObject(0)
                    .getJSONObject("points")
                    .getJSONArray("coordinates");

            // Prepare polyline options
            Polyline polyline = new Polyline();
            for (int i = 0; i < coordinates.length(); i++) {
                JSONArray point = coordinates.getJSONArray(i);
                double lng = point.getDouble(0);
                double lat = point.getDouble(1);
                polyline.addPoint(new GeoPoint(lat, lng));
            }

            return polyline;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

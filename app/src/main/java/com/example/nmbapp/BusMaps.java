package com.example.nmbapp;

import static com.example.nmbapp.MapUtils.buildRequestUrl;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.chromium.net.CronetEngine;
import org.chromium.net.UrlRequest;
import org.json.JSONException;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BusMaps extends AppCompatActivity {
    private MapView map = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Configuration.getInstance().setUserAgentValue(getPackageName());
        setContentView(R.layout.activity_bus_maps);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(this);
        CronetEngine cronetEngine = myBuilder.build();
        Executor executor = Executors.newSingleThreadExecutor();
        GeoPoint startPoint = new GeoPoint(16.498123668460345, 80.49915367423226);
        GeoPoint endPoint = new GeoPoint(16.502117163711993, 80.64806720088406);
        GeoPoint midPoint = new GeoPoint(16.45939388502441, 80.63526364041354);
        ArrayList<GeoPoint> al = new ArrayList<>();
        al.add(startPoint);
        al.add(midPoint);
        al.add(endPoint);
        UrlRequest.Builder requestBuilder = null;
        map = findViewById(R.id.map);
        requestBuilder = cronetEngine.newUrlRequestBuilder(
                buildRequestUrl(al), new RouteApiCallback(map), executor);


        UrlRequest request = requestBuilder.build();
        request.start();

        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        Marker marker = new Marker(map);
        marker.setPosition(startPoint);
        marker.setIcon(AppCompatResources.getDrawable(this, R.drawable.location_red_20));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(marker);
        Marker marker2 = new Marker(map);
        marker2.setPosition(endPoint);
        marker2.setIcon(AppCompatResources.getDrawable(this, R.drawable.location_blue));
        Marker marker3 = new Marker(map);
        marker3.setPosition(midPoint);
        marker3.setIcon(AppCompatResources.getDrawable(this, R.drawable.location_blue));
        map.getOverlays().add(marker2);
        map.getOverlays().add(marker3);
        Marker m4 = new Marker(map);
        m4.setIcon(AppCompatResources.getDrawable(this, R.drawable.bus_fv));
        map.getOverlays().add(m4);
        m4.setPosition(endPoint);
        IMapController mapController = map.getController();
        mapController.setZoom(12.1);
        mapController.setCenter(startPoint);

    }
}
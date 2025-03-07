package com.example.nmbapp;

import static com.example.nmbapp.MapUtils.buildRequestUrl;
import static com.example.nmbapp.WebSocketHandler.status;
import static com.example.nmbapp.env.DriverDataRet;
import static com.example.nmbapp.env.SelectedDriver;
import static com.example.nmbapp.env.SelectedRoute;
import static com.example.nmbapp.env.SelectedStop;
import static com.example.nmbapp.env.auth;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;

import org.chromium.net.CronetEngine;
import org.chromium.net.UrlRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.IOException;
import java.net.URI;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.nmbapp.env.email;
import static com.example.nmbapp.env.socketurl;

public class MapsFragment extends Fragment implements Listener{
    private MapView map = null;
    HashMap<String, String> hm = new HashMap<>();
    private FusedLocationProviderClient locClient;
    WebSocketHandler client;
    Marker busMark;
    GeoPoint cloc;
    boolean functionCalled = false;
    Context context;
    ArrayList<BusStop> busStopAl;
    ArrayList<GeoPoint> geoPointAl;
    ArrayList<Marker> markers = new ArrayList<>();
    HashMap<String, String> header = new HashMap<>();
    ArrayList<DriverBusRoute> bral;
    private String lastRoute = "";
    private String lastDriver = "";
    private String lastStop = "";
    private final Handler handler = new Handler(Looper.getMainLooper());
    String lastId = "";
    public MapsFragment(Context context, ArrayList<DriverBusRoute> al) {
        bral = al;
        this.context = context;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(context.getPackageName());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        if(!DriverDataRet){
            getDriverData();
        }
        if(!status){
            hm.put("id", email);
            hm.put("type", "user");
            URI myurl = URI.create(socketurl);
            client = new WebSocketHandler(myurl, hm, MapsFragment.this);
            client.connect();
        }
        map = view.findViewById(R.id.map2);
        handler.post(valuesRunnable);
        System.out.println(SelectedRoute);
        System.out.println(lastRoute);
        System.out.println(functionCalled);
        busMark = new Marker(map);
        busMark.setIcon(AppCompatResources.getDrawable(context, R.drawable.bus_svgrepo_com));
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(13.1);
        return view;
    }
    public void getDriverData(){
        ApiUtils au = new ApiUtils();
        Call<ResponseBody> call = au.allDriversPoint.getAll(auth);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getBoolean("success")) {
                        JSONArray dataArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject dataObject = dataArray.getJSONObject(i);
                            String id = dataObject.getString("id");
                            getSdriverInfo(id);
                        }
                        DriverDataRet = true;
                    } else {
                        System.out.println("Operation not successful.");
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void updatePaths(){
        map.getOverlays().clear();
        map.invalidate();
        for(int i=0; i<bral.size(); i++){
            if(bral.get(i).routeName.equals(SelectedRoute) && bral.get(i).name.equals(SelectedDriver)){
                busStopAl = bral.get(i).stops;
                geoPointAl = convertToGeoPoints(busStopAl);
                cronetReq(geoPointAl);
                addPoints();
                if(!status){
                    continue;
                }
                if(!lastId.isEmpty()){
                    client.sendMessage(lastId+"_unsub");
                }
                String id = bral.get(i).driverId;
                client.sendMessage(id +"_sub");
                lastId = id;
            }

        }

    }
    public void addPoints(){
        for(int i=0; i<markers.size(); i++){
            markers.get(i).remove(map);
        }
        for(int i=0; i<geoPointAl.size(); i++){
            Marker marker = new Marker(map);
            marker.setPosition(geoPointAl.get(i));
            marker.setIcon(AppCompatResources.getDrawable(context, R.drawable.location_red_20));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            map.getOverlays().add(marker);
            markers.add(marker);
        }
        IMapController mapController = map.getController();
        mapController.setCenter(geoPointAl.get(0));
        map.invalidate();
    }
    public void cronetReq(ArrayList<GeoPoint> al){
        CronetEngine.Builder myBuilder = new CronetEngine.Builder(context);
        CronetEngine cronetEngine = myBuilder.build();
        Executor executor = Executors.newSingleThreadExecutor();
        UrlRequest.Builder requestBuilder = null;
        requestBuilder = cronetEngine.newUrlRequestBuilder(
                buildRequestUrl(al), new RouteApiCallback(map), executor);
        UrlRequest request = requestBuilder.build();
        request.start();
    }
    public ArrayList<GeoPoint> convertToGeoPoints(ArrayList<BusStop> busStops) {
        ArrayList<GeoPoint> geoPoints = new ArrayList<>();
        for (BusStop stop : busStops) {
            geoPoints.add(new GeoPoint(stop.latitude, stop.longitude));
        }
        return geoPoints;
    }
    private final Runnable valuesRunnable = new Runnable() {
        @Override
        public void run() {
            checkVals(this);
        }
    };
    public void checkVals(Runnable r){
        if (!SelectedRoute.isEmpty() &&
                !SelectedDriver.isEmpty() &&
                !SelectedStop.isEmpty()) {

            if (!functionCalled ||
                    !SelectedRoute.equals(lastRoute) ||
                    !SelectedDriver.equals(lastDriver) ||
                    !SelectedStop.equals(lastStop)) {
                System.out.println("running");
                lastRoute = SelectedRoute;
                lastDriver = SelectedDriver;
                lastStop = SelectedStop;

                updatePaths();

                functionCalled = true;
            }
        } else {
            functionCalled = false;
        }
        handler.postDelayed(r, 3000);
    }

    public void getSdriverInfo(String id){
        ApiUtils au = new ApiUtils();
        Call<ResponseBody> call = au.driDetPoint.getDet(id, auth);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject res = new JSONObject(response.body().string());
                    bral.add(getBusObject(res));
                } catch (Exception e) {
                    Log.w("Invalid Input", e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    @Override
    public void onMessageReceived(String message) {
        if(message.equals("-1")){
            return;
        }
        String loc = message.split("_")[1];
        String[] coor = loc.split(",");
        GeoPoint gp = new GeoPoint(Double.parseDouble(coor[0]), Double.parseDouble(coor[1]));
        busMark.setPosition(gp);
        map.getOverlays().add(busMark);
    }
    public DriverBusRoute getBusObject(JSONObject json) throws JSONException {
        JSONObject data = json.getJSONObject("data");
        String name = data.getString("name");
        String email = data.getString("email");

        // Getting the first bus object
        JSONObject bus = data.getJSONArray("bus").getJSONObject(0);
        String busNo = bus.getString("busno");
        String driverId = bus.getString("driverId");
        String routeId = bus.getString("routeId");

        JSONObject routes = bus.getJSONObject("routes");
        String routeName = routes.getString("routeName");

        // Collecting stops
        JSONArray stopsArray = routes.getJSONArray("stops");
        ArrayList<BusStop> stops = new ArrayList<>();

        for (int i = 0; i < stopsArray.length(); i++) {
            JSONObject stop = stopsArray.getJSONObject(i);
            String description = stop.getString("description").split(",")[0];
            double lat = stop.getDouble("lat");
            double lon = stop.getDouble("long");
            String time = stop.getString("time");
            stops.add(new BusStop(lat, lon, time, description));
        }
        DriverBusRoute driverBusRoute = new DriverBusRoute(name, email, busNo, driverId, routeId, routeName, stops);
        return driverBusRoute;
    }

    @Override
    public void onPause(){
        super.onPause();
        handler.removeCallbacks(valuesRunnable);
        map.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        map.onResume();
        functionCalled = false;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        handler.removeCallbacks(valuesRunnable);
        map.onDetach();
    }
}
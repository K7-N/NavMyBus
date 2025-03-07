package com.example.nmbapp;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class RouteApiCallback extends UrlRequest.Callback {
        private static final String TAG = "RouteApiCallback";
        MapUtils mu = new MapUtils();
        private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MapView mapView;
        RouteApiCallback(MapView mv){
            mapView = mv;
        }
        @Override
        public void onRedirectReceived(UrlRequest request, UrlResponseInfo info, String newLocationUrl) {
            Log.i(TAG, "onRedirectReceived method called.");
            request.followRedirect();
        }

        @Override
        public void onResponseStarted(UrlRequest request, UrlResponseInfo info) {
            Log.i(TAG, "onResponseStarted method called.");
            request.read(ByteBuffer.allocateDirect(102400));
        }

        @Override
        public void onReadCompleted(UrlRequest request, UrlResponseInfo info, ByteBuffer byteBuffer) {
            Log.i(TAG, "onReadCompleted method called.");

            // Read data from ByteBuffer
            byteBuffer.flip(); // Switch buffer from write mode to read mode
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);

            // Accumulate bytes
            byteArrayOutputStream.write(bytes, 0, bytes.length);

            // Prepare to read more data
            byteBuffer.clear();
            request.read(byteBuffer);
        }

        @Override
        public void onSucceeded(UrlRequest request, UrlResponseInfo info) {
            Log.i(TAG, "onSucceeded method called.");
            String responseData = byteArrayOutputStream.toString();
            Log.i(TAG, "Response data: " + responseData);
            Polyline pl = mu.getPolyline(responseData);
            pl.getOutlinePaint().setColor(Color.BLUE);
            pl.getOutlinePaint().setStrokeWidth(10f);
            pl.getOutlinePaint().setStrokeJoin(Paint.Join.ROUND);
            pl.getOutlinePaint().setStrokeCap(Paint.Cap.ROUND);
            mapView.getOverlays().add(0, pl);
            mapView.invalidate();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing ByteArrayOutputStream", e);
            }
        }

        @Override
        public void onFailed(UrlRequest request, UrlResponseInfo info, CronetException error) {
            Log.e(TAG, "Request failed: " + error.getMessage());
        }
    }


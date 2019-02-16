package com.example.uccampusmap;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.widget.Toast;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public class StreetViewWearActivity extends Activity implements
        OnStreetViewPanoramaReadyCallback {

    private LatLng coordinates;
    private static final float ZOOM_BY = -1.0f;
    long duration = 5000;
    float tilt = 0;
    private static final int PAN_BY = 180;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view_wear);

        Bundle extras = getIntent().getExtras();
        double latitude = extras.getDouble("latitude");
        double longitude = extras.getDouble("longitude");
        coordinates = new LatLng(latitude, longitude);
        setTitle(extras.getString("name"));

        StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment)
                getFragmentManager().findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                .zoom(panorama.getPanoramaCamera().zoom + ZOOM_BY)
                .bearing(panorama.getPanoramaCamera().bearing - PAN_BY)
                .tilt(tilt)
                .build();
        panorama.animateTo(camera, duration);
        panorama.setPosition(coordinates);

        panorama.setOnStreetViewPanoramaLongClickListener(new StreetViewPanorama.OnStreetViewPanoramaLongClickListener() {
            @Override
            public void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
                finish();
            }
        });
    }

}

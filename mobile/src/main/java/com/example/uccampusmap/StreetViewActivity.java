package com.example.uccampusmap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class StreetViewActivity extends AppCompatActivity implements
        OnStreetViewPanoramaReadyCallback {

    private LatLng coordinates;
    private static final float ZOOM_BY = -1.0f;
    long duration = 5000;
    float tilt = 0;
    private static final int PAN_BY = 180;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Adds a back button to the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
    }

    // Event handler for when back button is pressed
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

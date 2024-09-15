package com.example.pokemoness3.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.example.pokemoness3.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.pokemoness3.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private LocationManager mLocationManager;
    private Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        lastLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double LATPokemon = getIntent().getDoubleExtra("POKEMON_LAT", 0.0);
        double LOGPokemon = getIntent().getDoubleExtra("POKEMON_LOG", 0.0);

        Log.d("MAPS_ACTIVITY", "Latitud: " + LATPokemon + ", Longitud: " + LOGPokemon);

        LatLng Pokemon = new LatLng(LATPokemon, LOGPokemon);
        LatLng user = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());

        mMap.addMarker(new MarkerOptions().position(user).title("Ubicacion Actual"));
        mMap.addMarker(new MarkerOptions().position(Pokemon).title("Ubicacion Pokemon"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user,17));
    }
}
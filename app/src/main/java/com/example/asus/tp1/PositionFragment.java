package com.example.asus.tp1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PositionFragment extends Fragment implements OnMapReadyCallback {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.position_tab, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        // utiliser `getChildFragmentManager` et pas `getFragmentManager`
        // sur l'activity pour éviter une `NullPointerException`.
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    // Ce qui est souligné est le problème pour la geolocalisation, il faut qu'on vérifie
    // si l'autorisation est bien fait (alt+entree avec InteliJ)

    @Override
    public void onMapReady(GoogleMap map) {
        if (ContextCompat.checkSelfPermission(
            this.getActivity().getApplicationContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            askGPSPersmissions();
        }

        map.setMyLocationEnabled(true);

        LocationManager locationManager = (LocationManager) getActivity()
                .getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        
        Location location = locationManager.getLastKnownLocation(
             LocationManager.GPS_PROVIDER
        );
    }

    protected void askGPSPersmissions() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(
            this.getActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )) {
            ActivityCompat.requestPermissions(
                this.getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                1
            );
        }
    }
}

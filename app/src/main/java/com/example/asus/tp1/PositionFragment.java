package com.example.asus.tp1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PositionFragment extends Fragment implements OnMapReadyCallback {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.position_tab, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        // Note importante : utiliser `getChildFragmentManager` et pas `getFragmentManager`
        // sur l'activity pour éviter une `NullPointerException`.
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    // Ce qui est souligné est le problème pour la geolocalisation, il faut qu'on vérifie si l'autorisation est bien fait (alt+entree avec InteliJ)

    @Override
    public void onMapReady(GoogleMap map) {

        String[] latlong = "50.273876,3.971405".split(",");
        LatLng location = new LatLng(50.273876, 3.971405);

        /*map.setMyLocationEnabled(true);
            MarkerOptions marker = new MarkerOptions();

            LatLng myLocation = new LatLng(latitude,longitude);
            map.addMarker(new MarkerOptions()
                    .position(myLocation)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title("Ma position"));
            map.moveCamera(CameraUpdateFactory.newLatLng(myLocation));*/
        map.addMarker(new MarkerOptions().position(location).title("Ma position"));

    }
}

package com.example.thunder2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class aboutContest extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contestinformation_aboutcontest);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void onMapReady(final GoogleMap map) {
        double mLatitude = 37.225165;
        double mLongitude = 127.187141;

        mMap = map;

        LatLng now = new LatLng(mLatitude, mLongitude);   //현재 위치

        MarkerOptions marketOptions = new MarkerOptions();

        marketOptions.position(now);    //지금 위치로 포지션
        marketOptions.title("PC방");
        marketOptions.snippet("레볼루션 PC방");
        mMap.addMarker(marketOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(now));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
    }



}

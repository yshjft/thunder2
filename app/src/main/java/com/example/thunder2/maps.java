package com.example.thunder2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import com.example.thunder2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class maps extends AppCompatActivity implements OnMapReadyCallback {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(final GoogleMap map){
        LatLng now = new LatLng(37.56,126.97);   //일단 서울의 좌표를 넣음

        MarkerOptions marketOptions = new MarkerOptions();

        marketOptions.position(now);    //지금 위치로 포지션
        marketOptions.title("서울");
        marketOptions.snippet("한국의 수도");
        map.addMarker(marketOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(now));

        map.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
}

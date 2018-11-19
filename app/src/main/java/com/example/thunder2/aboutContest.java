package com.example.thunder2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

        //초기화
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        //툴바 설정
        toolbar.setTitleTextColor(Color.parseColor("#000000")); //제목의 칼라
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));   //툴바컬러
        toolbar.setTitle("대회 정보");  //주제목 넣기
        // toolbar.setSubtitle("Main"); //부제목 넣기
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher); //제목앞에 아이콘 넣기
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.

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

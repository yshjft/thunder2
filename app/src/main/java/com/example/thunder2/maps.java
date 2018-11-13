package com.example.thunder2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.thunder2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class maps extends AppCompatActivity implements OnMapReadyCallback {
    //GoogleMap 객체
    GoogleMap googleMap;
    MapFragment mapFragment;
    LocationManager locationManager;
    RelativeLayout boxMap;
    //나의 위도 경도 고도
    double mLatitude;  //위도
    double mLongitude; //경도

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps); //maps로

        boxMap = (RelativeLayout)findViewById(R.id.boxMap);

        //LocationManager
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);


        //GPS가 켜져있는지 체크
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //GPS 설정화면으로 이동
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
            finish();
        }

        //마시멜로 이상이면 권한 요청하기
        if(Build.VERSION.SDK_INT >= 23){
            //권한이 없는 경우
            if(ContextCompat.checkSelfPermission(maps.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(maps.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(maps.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION , Manifest.permission.ACCESS_FINE_LOCATION} , 1);
            }
            //권한이 있는 경우
            else{
                requestMyLocation();
            }
        }
        //마시멜로 아래
        else{
            requestMyLocation();
        }
    }

    //권한 요청후 응답 콜백
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //ACCESS_COARSE_LOCATION 권한
        if(requestCode==1){
            //권한받음
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                requestMyLocation();
            }
            //권한못받음
            else{
                Toast.makeText(this, "권한없음", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    //나의 위치 요청
    public void requestMyLocation(){
        if(ContextCompat.checkSelfPermission(maps.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(maps.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        //요청
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 10, locationListener);
    }

    //위치정보 구하기 리스너
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (ContextCompat.checkSelfPermission(maps.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(maps.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //나의 위치를 한번만 가져오기 위해
            locationManager.removeUpdates(locationListener);

            //위도 경도
            mLatitude = location.getLatitude();   //위도
            mLongitude = location.getLongitude(); //경도


            //맵생성
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            //콜백클래스 설정
            mapFragment.getMapAsync(maps.this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { Log.d("gps", "onStatusChanged"); }

        @Override
        public void onProviderEnabled(String provider) { }

        @Override
        public void onProviderDisabled(String provider) { }
    };

    //구글맵 생성 콜백
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        //지도타입 - 일반
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //나의 위치 설정
        LatLng position = new LatLng(mLatitude , mLongitude);

        //화면중앙의 위치와 카메라 줌비율
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 18));

        LatLng sub = new LatLng(mLatitude + Math.random()/1000.0,mLongitude + Math.random()/1000.0);

        MarkerOptions marketOptions = new MarkerOptions();

        marketOptions.position(sub);    //지금 위치로 포지션
        marketOptions.title("PC방");
        marketOptions.snippet("명물 PC방");
        googleMap.addMarker(marketOptions);
        sub = new LatLng(mLatitude + Math.random()/1000.0,mLongitude + Math.random()/1000.0);

        marketOptions.position(sub);    //지금 위치로 포지션
        marketOptions.title("PC방");
        marketOptions.snippet("캡숑 PC방");
        googleMap.addMarker(marketOptions);

        sub = new LatLng(mLatitude + Math.random()/1000.0,mLongitude + Math.random()/1000.0);
        marketOptions.position(sub);    //지금 위치로 포지션
        marketOptions.title("PC방");
        marketOptions.snippet("울랄라 PC방");
        googleMap.addMarker(marketOptions);

        //지도 보여주기
        boxMap.setVisibility(View.VISIBLE);
    }


    /*
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps); //activity_maps.xml

        android.app.FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(final GoogleMap map){


        LatLng now = new LatLng(mLatitude,mLongitude);   //현재 위치
        //sub는 전부 현재위치 기준 Fake위치

        LatLng sub = new LatLng(mLatitude + Math.random()/1000.0,mLongitude + Math.random()/1000.0);

        MarkerOptions marketOptions = new MarkerOptions();

        marketOptions.position(sub);    //지금 위치로 포지션
        marketOptions.title("PC방");
        marketOptions.snippet("명물 PC방");
        map.addMarker(marketOptions);
        sub = new LatLng(mLatitude + Math.random()/1000.0,mLongitude + Math.random()/1000.0);

        marketOptions.position(sub);    //지금 위치로 포지션
        marketOptions.title("PC방");
        marketOptions.snippet("캡숑 PC방");
        map.addMarker(marketOptions);

        sub = new LatLng(mLatitude + Math.random()/1000.0,mLongitude + Math.random()/1000.0);
        marketOptions.position(now);    //지금 위치로 포지션
        marketOptions.title("PC방");
        marketOptions.snippet("울랄라 PC방");
        map.addMarker(marketOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(now));

        map.animateCamera(CameraUpdateFactory.zoomTo(17));
    }

    */
}

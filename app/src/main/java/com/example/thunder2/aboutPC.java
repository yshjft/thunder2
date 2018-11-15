package com.example.thunder2;

//PC방 상세정보창이여, init() 걍 이미지 잘 들어가나 확인하는 거야

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class aboutPC extends FragmentActivity
    implements OnMapReadyCallback{

    RecyclerView rcv;
    RcvAdapter_aboutPC rcvAdapter;
    ArrayList<DataForm_forPC> list= new ArrayList<>();
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pclist_aboutpc);
        init();
        rcv=(RecyclerView)findViewById(R.id.second_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));


        rcvAdapter=new RcvAdapter_aboutPC(this, list);
        rcv.setAdapter(rcvAdapter);

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

    private void init(){
        DataForm_forPC a=new DataForm_forPC("넥스트");
        list.add(a);

        a=new DataForm_forPC("고릴라");
        list.add(a);

        a=new DataForm_forPC("조이칸");
        list.add(a);


    }
}

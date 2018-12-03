package com.example.thunder2;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class aboutPC extends FragmentActivity
    implements OnMapReadyCallback{

    //파이어베이스 시작
    private DatabaseReference mDatabase;
//    ArrayList<DataForm_forPC> list= new ArrayList<>();
    //파이어베이스 끝


    private GoogleMap mMap;
    private String locationName; //주소 스트링 값
    private double mlat;//위도 전달
    private double mlng;//경도 전달
    private String pcRooomName;//PC방 이름 전달
    private String strUid;//uid 스트링
    int unuse;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pclist_aboutpc);

        mContext = this;

        //전달된 데이터 받기 시작
        String getLocation=getIntent().getStringExtra("stringLocation");
        locationName=getLocation;
        String getSeakind=getIntent().getStringExtra("stringSeatkind");
        final int getSeatTotal=getIntent().getIntExtra("intSeatTotal", 1);
        String getSpec=getIntent().getStringExtra("stringSpec");
        String getName=getIntent().getStringExtra("stringName");
        pcRooomName=getName;
        String getNotice =getIntent().getStringExtra("stringNotice");
        String getUid=getIntent().getStringExtra("stringUID");
        strUid=getUid;
        String getImage=getIntent().getStringExtra("stringImage");
        //전달된 데이터 받기


        mDatabase= FirebaseDatabase
                .getInstance()
                .getReference()
                .child("PCL")
                .child(strUid).child("seatUnuse");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    unuse = dataSnapshot.getValue(int.class);
                }catch(Exception e){ }

                try {
                    if (unuse > getSeatTotal) {
                        Toast.makeText(getApplicationContext(), "남은자리가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        TextView seatUnuse = (TextView) findViewById(R.id.pc_seatUnuse);
                        seatUnuse.setText(unuse + "");
                    }
                }catch(Exception e){ }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });


        //데이터 XML에 넣기 시작
        ImageView target = (ImageView) findViewById(R.id.imageView2);
        Glide.with(mContext)
                .load(getImage)
                .into(target);

        TextView name=(TextView)findViewById(R.id.pc_name);
        name.setText(getName);
        TextView address=(TextView)findViewById(R.id.pc_address);
        address.setText(getLocation);
        TextView seatTotal=(TextView)findViewById(R.id.pc_seatTotal);
        seatTotal.setText(getSeatTotal+"");
        TextView seatKind=(TextView)findViewById(R.id.pc_seatKind);
        seatKind.setText(getSeakind);
        TextView spec=(TextView)findViewById(R.id.pc_spec);
        spec.setText(getSpec);
        TextView notice=(TextView)findViewById(R.id.pc_notice);
        notice.setText(getNotice);
        //데이터 XML에 넣기 끝



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(final GoogleMap map) {

        //지오 코딩 주소 ->좌표 시작
        Geocoder mGeoCoder = new Geocoder(mContext);
        try{
            List<Address>mResultLocation=mGeoCoder.getFromLocationName(locationName,1);
            double mLat=mResultLocation.get(0).getLatitude();
            mlat=mLat;
            double mLng=mResultLocation.get(0).getLongitude();
            mlng=mLng;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "주소를 변환할 수 없습니다.", Toast.LENGTH_SHORT);
        }
        //지오 코딩 주소->좌표 끝

        double mLatitude = mlat;
        double mLongitude = mlng;
        mMap = map;
        LatLng now = new LatLng(mLatitude, mLongitude);   //현재 위치
        MarkerOptions marketOptions = new MarkerOptions();
        marketOptions.position(now);    //지금 위치로 포지션
        marketOptions.title(pcRooomName);
        marketOptions.snippet(locationName);
        mMap.addMarker(marketOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(now));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
    }

}

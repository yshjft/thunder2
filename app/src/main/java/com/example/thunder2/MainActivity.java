package com.example.thunder2;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    //파이어베이스 시작
    private DatabaseReference mDatabase;
    //파이어베이스 끝


    //리사이클러뷰 시작
    RecyclerView rcv;
    RcvAdapter_forPC rcvAdapter;
    private ArrayList<DTOaboutPC> pcList = new ArrayList<>();
    //리사이클러뷰 끝


    //거리제한(600m)
    private double limitDistance=600;
    private double distance;
    double mlatitude;
    double mlongitude;
    double pcLat; //pc방 위도
    double pcLng; //pc방 경도
    //거리제한(600m)

    private BackPressCloseHandler backPressCloseHandler;
    Context mContext=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        //getGPS();
        mlatitude=37.233868981629044;
        mlongitude=127.1857721894659;



        mDatabase=FirebaseDatabase
                .getInstance()
                .getReference()
                .child("PCL");

        checkGpsInternet();

        rcv = (RecyclerView) findViewById(R.id.main_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcvAdapter = new RcvAdapter_forPC(this, pcList);
        rcv.setAdapter(rcvAdapter);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pcList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String location=snapshot.getValue(DTOaboutPC.class).getLocation();
                    Geocoder mGeoCoder = new Geocoder(mContext);
                    try{
                        List<Address> mResultLocation=mGeoCoder.getFromLocationName(location,1);
                        double Lat=mResultLocation.get(0).getLatitude();
                        pcLat =Lat;
                        double Lng=mResultLocation.get(0).getLongitude();
                        pcLng=Lng;
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(mContext, "주소를 변환할 수 없습니다.", Toast.LENGTH_SHORT);
                    }


                    Location myLocation=new Location("point_of_me");
                    myLocation.setLatitude(mlatitude);
                    myLocation.setLongitude(mlongitude);

                    Location pcLocation= new Location("point_of_pc");
                    pcLocation.setLatitude(pcLat);
                    pcLocation.setLongitude(pcLng);

                    distance=myLocation.distanceTo(pcLocation);

                    if(distance<=limitDistance){
                        pcList.add(snapshot.getValue(DTOaboutPC.class));
                    }
                }
                rcvAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        backPressCloseHandler = new BackPressCloseHandler(this);
    }



    public void checkGpsInternet(){
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled((LocationManager.GPS_PROVIDER))){
            AlertDialog.Builder gpsDialog = new AlertDialog.Builder(this);
            gpsDialog.setTitle("GPS 연결 문제");
            gpsDialog.setMessage("GPS 연결 후 사용해주세요. GPS를 연결하지 않고 사용할 경우 서비스에 제한이 있습니다. " +
                    "GPS를 접속하여도 목록이 나오지 않을 경우 앱을 켰다 껐다를 반복해주세요");
            gpsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            }).create().show();
        }

        // wifi 또는 모바일 네트워크 어느 하나라도 연결이 되어있다면,
        if (wifi.isConnected() || mobile.isConnected()) {

        }else{
            AlertDialog.Builder intDialog = new AlertDialog.Builder(this);
            intDialog.setTitle("인터넷 연결 문제");
            intDialog.setMessage("인터넷 연결 후 사용해주세요. 인터넷을 연결하지 않고 사용할 경우 서비스에 제한이 있습니다.");
            intDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            }).create().show();
        }
    }

    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }

    public void onButton_contest(View v) {
        Intent intent = new Intent(getApplicationContext(), list_contest.class);
        startActivity(intent);
    }

    public void onButton_SignIn(View v) {
        Intent intent = new Intent(getApplicationContext(), SignIn.class);
        startActivity(intent);
    }

}




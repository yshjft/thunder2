package com.example.thunder2;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    //파이어베이스 시작
    private DatabaseReference mDatabase;
    //파이어베이스 끝


    //리사이클러뷰 시작
    RecyclerView rcv;
    RcvAdapter_forPC rcvAdapter;
    private ArrayList<DTOaboutPC> pcList = new ArrayList<>();
    //리사이클러뷰 끝


    private int limitDistance=600;
    Location location;
    double lat;
    double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        mDatabase=FirebaseDatabase
                .getInstance()
                .getReference()
                .child("PCL");


        //인터넷, GPS 연결 확인 부분 시작
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled((LocationManager.GPS_PROVIDER))){
            Toast.makeText(this,"GPS 연결 안됨",Toast.LENGTH_LONG).show();
            Toast.makeText(this,"GPS를 확인해주세요",Toast.LENGTH_LONG).show();
            AlertDialog.Builder gpsDialog = new AlertDialog.Builder(this);
            gpsDialog.setTitle("GPS 설정");
            gpsDialog.setMessage("GPS 기능을 설정하시겠습니까?");
            gpsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // GPS설정 화면으로 이동
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                }
            })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    }).create().show();
        }
        else{
            Toast.makeText(this,"GPS 체크 완료",Toast.LENGTH_LONG).show();
        }

        // wifi 또는 모바일 네트워크 어느 하나라도 연결이 되어있다면,
        if (wifi.isConnected() || mobile.isConnected()) {
            Log.i("연결됨" , "연결이 되었습니다.");
            Toast.makeText(this,"인터넷 체크 완료",Toast.LENGTH_LONG).show();
        } else {
            Log.i("연결 안 됨" , "연결이 다시 한번 확인해주세요");
            Toast.makeText(this,"인터넷을 확인해주세요",Toast.LENGTH_LONG).show();
            AlertDialog.Builder intDialog = new AlertDialog.Builder(this);
            intDialog.setTitle("인터넷 설정");
            intDialog.setMessage("인터넷 기능을 설정하시겠습니까?");
            intDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // GPS설정 화면으로 이동
                    Intent intent = new Intent(Settings.ACTION_WIFI_IP_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                }
            })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    }).create().show();
        }
        //인터넷, GPS 연결 확인 부분 끝



        rcv = (RecyclerView) findViewById(R.id.main_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcvAdapter = new RcvAdapter_forPC(this, pcList);
        rcv.setAdapter(rcvAdapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pcList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    pcList.add(snapshot.getValue(DTOaboutPC.class));
                }
                //필터링시작
                //내위치를 구한다.
                //600m 반경으로 구한다.
                //

                //필터링끝
                rcvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

 package com.example.thunder2;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;


 public class MainActivity extends AppCompatActivity {

     private Button btnShowLocation;
     private TextView txtLat;
     private TextView txtLon;
     private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
     private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
     private boolean isAccessFineLocation = false;
     private boolean isAccessCoarseLocation = false;
     private boolean isPermission = false;

     LocationManager locationManager;
     RecyclerView rcv;
     RcvAdapter_forPC rcvAdapter;
     ArrayList<DataForm_forPC> list = new ArrayList<>();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         Intent intent = new Intent(this, LoadingActivity.class);
         startActivity(intent);


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


         init();

         rcv = (RecyclerView) findViewById(R.id.main_rcv);
         rcv.setLayoutManager(new LinearLayoutManager(this));

         rcvAdapter = new RcvAdapter_forPC(this, list);
         rcv.setAdapter(rcvAdapter);
     }




     public void onButton_contest(View v) {
         Intent intent = new Intent(getApplicationContext(), list_contest.class);
         startActivity(intent);
     }

     public void onButton_setting(View v) {
         Intent intent = new Intent(getApplicationContext(), setting.class);
         startActivity(intent);
     }

    
     private void init() {
         DataForm_forPC a = new DataForm_forPC("넥스트");
         list.add(a);

         a = new DataForm_forPC("고릴라");
         list.add(a);

         a = new DataForm_forPC("조이칸");
         list.add(a);

         a = new DataForm_forPC("큐브");
         list.add(a);

         a = new DataForm_forPC("해라");
         list.add(a);

         a = new DataForm_forPC("인디고");
         list.add(a);

         a = new DataForm_forPC("스타덤");
         list.add(a);

         a = new DataForm_forPC("다락");
         list.add(a);
     }
 }

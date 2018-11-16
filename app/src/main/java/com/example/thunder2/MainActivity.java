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

         if(chkGpsService() == false){
             Toast.makeText(this,"GPS가 꺼져있습니다.",Toast.LENGTH_LONG).show();
         }
         else{
             Toast.makeText(this,"GPS 체크 완료",Toast.LENGTH_LONG).show();
         }

         ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
         NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


         // wifi 또는 모바일 네트워크 어느 하나라도 연결이 되어있다면,
         if (wifi.isConnected() || mobile.isConnected()) {
             Log.i("연결됨" , "연결이 되었습니다.");
             Toast.makeText(this,"인터넷 체크 완료",Toast.LENGTH_LONG).show();
         } else {
             Log.i("연결 안 됨" , "연결이 다시 한번 확인해주세요");
             Toast.makeText(this,"인터넷을 확인해주세요되었습니다.",Toast.LENGTH_LONG).show();
             AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
             gsDialog.setTitle("인터넷 설정");
             gsDialog.setMessage("인터넷 기능을 설정하시겠습니까?");
             gsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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

     private boolean chkGpsService() {

         String gps = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

         Log.d(gps, "aaaa");

         if (!(gps.matches(".*gps.*") && gps.matches(".*network.*"))) {

             // GPS OFF 일때 Dialog 표시
             AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
             gsDialog.setTitle("위치 서비스 설정");
             gsDialog.setMessage("무선 네트워크 사용, GPS 위성 사용을 모두 체크하셔야 정확한 위치 서비스가 가능합니다.\n위치 서비스 기능을 설정하시겠습니까?");
             gsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int which) {
                     // GPS설정 화면으로 이동
                     Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                     intent.addCategory(Intent.CATEGORY_DEFAULT);
                     startActivity(intent);
                 }
             })
                     .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                             return;
                         }
                     }).create().show();
             return false;

         } else {
             return true;
         }
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

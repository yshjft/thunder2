 package com.example.thunder2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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


     RecyclerView rcv;
     RcvAdapter_forPC rcvAdapter;
     ArrayList<DataForm_forPC> list = new ArrayList<>();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         Intent intent = new Intent(this, LoadingActivity.class);
         startActivity(intent);


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


     public void onButton_map(View v) {
         Intent intent = new Intent(getApplicationContext(), maps.class);
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

 package com.example.thunder2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {

     private Button btnShowLocation;
     private TextView txtLat;
     private TextView txtLon;
     private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
     private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
     private boolean isAccessFineLocation = false;
     private boolean isAccessCoarseLocation = false;
     private boolean isPermission = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,LodingActivity.class);
        startActivity(intent);

    }



     public void onButton_contest(View v){
         Intent intent=new Intent(getApplicationContext(), contest_inform.class);
         startActivity(intent);
     }

     public void onButton_setting(View v){
         Intent intent=new Intent(getApplicationContext(), setting.class);
         startActivity(intent);
     }

     public void onButton_pclist(View v){
        Intent intent= new Intent(getApplicationContext(),pcList.class);
        startActivity(intent);
     }


}

 package com.example.thunder2;

 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.content.Intent;
 import android.view.View;

 public class setting extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main_setting);
     }

     public void onButton_setting_alarm(View v) {
         Intent intent = new Intent(getApplicationContext(), setting_alarm.class);
         startActivity(intent);
     }

     public void onButton_setting_login(View v) {
         Intent intent = new Intent(getApplicationContext(), setting_login.class);
         startActivity(intent);
     }
     public void onButton_setting_market(View v) {
         Intent intent = new Intent(getApplicationContext(), setting_market.class);
         startActivity(intent);
     }



 }

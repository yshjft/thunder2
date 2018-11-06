 package com.example.thunder2;

 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.content.Intent;
 import android.view.View;

 public class 설정 extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_setting);
     }

     public void onButton_setting_alarm(View v) {
         Intent intent = new Intent(getApplicationContext(), 설정_알람설정.class);
         startActivity(intent);
     }

     public void onButton_setting_login(View v) {
         Intent intent = new Intent(getApplicationContext(), 설정_로그인.class);
         startActivity(intent);
     }
     public void onButton_setting_market(View v) {
         Intent intent = new Intent(getApplicationContext(), 설정_업주관리.class);
         startActivity(intent);
     }

 }

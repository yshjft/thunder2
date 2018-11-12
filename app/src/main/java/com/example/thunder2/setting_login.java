 package com.example.thunder2;

 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.content.Intent;
 import android.view.View;

 public class setting_login extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main_setting_login);
     }

     public void onButton_setting_register(View v) {
         Intent intent = new Intent(getApplicationContext(), setting_register.class);
         startActivity(intent);
     }


 }

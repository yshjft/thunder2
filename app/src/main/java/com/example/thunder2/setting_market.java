 package com.example.thunder2;

 import android.content.Intent;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.view.View;

 public class setting_market extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_setting_market);
     }

     public void onButton_market_manage(View v){
         Intent intent=new Intent(getApplication(), setting_market_manage.class);
         startActivity(intent);
     }

     public void onButton_notice_contest(View v){
         Intent intent= new Intent(getApplication(), setting_market_contestManage.class);
         startActivity(intent);
     }


 }

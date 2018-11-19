 package com.example.thunder2;

 import android.graphics.Color;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.content.Intent;
 import android.support.v7.widget.Toolbar;
 import android.view.View;

 public class setting extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main_setting);

         Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
         //툴바 설정
         toolbar.setTitleTextColor(Color.parseColor("#000000")); //제목의 칼라
         toolbar.setBackgroundColor(Color.parseColor("#ffffff"));   //툴바컬러
         toolbar.setTitle("매장 관리");  //주제목 넣기
        // toolbar.setSubtitle("Main"); //부제목 넣기
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher); //제목앞에 아이콘 넣기
         setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
     }


     public void onButton_setting_market(View v) {
         Intent intent = new Intent(getApplicationContext(), setting_market.class);
         startActivity(intent);
     }



 }

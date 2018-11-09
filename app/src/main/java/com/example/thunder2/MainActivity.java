 package com.example.thunder2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

 public class MainActivity extends AppCompatActivity {

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


}

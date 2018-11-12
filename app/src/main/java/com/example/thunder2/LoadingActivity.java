package com.example.thunder2;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends Activity {
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }
    private void startLoading(){
        Handler handler = new Handler();    //핸들러
        handler.postDelayed(new Runnable(){
            public void run(){
                finish();
            }
        },2000);
    }
}

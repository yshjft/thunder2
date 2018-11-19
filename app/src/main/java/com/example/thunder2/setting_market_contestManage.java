package com.example.thunder2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class setting_market_contestManage extends AppCompatActivity {

    RecyclerView rcv;
    RcvAdapter_correctionContest rcvAdapter;
    ArrayList<DataForm_correctionContest> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting_market_contestmanage);

        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //툴바 설정
        toolbar.setTitleTextColor(Color.parseColor("#000000")); //제목의 칼라
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));   //툴바컬러
        toolbar.setTitle("대회 관리");  //주제목 넣기
        // toolbar.setSubtitle("Main"); //부제목 넣기
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher); //제목앞에 아이콘 넣기
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.

        rcv=(RecyclerView)findViewById(R.id.main_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        rcvAdapter=new RcvAdapter_correctionContest(this, list);
        rcv.setAdapter(rcvAdapter);
    }

    private void init(){

        DataForm_correctionContest a=new DataForm_correctionContest("대회1 수정하기");
        list.add(a);

    }


    public void onButton_add_contest(View v) {
        Intent intent = new Intent(getApplicationContext(),setting_market_contestManage_addContest.class);
        startActivity(intent);
    }


}

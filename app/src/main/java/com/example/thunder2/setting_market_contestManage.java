package com.example.thunder2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

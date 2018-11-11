package com.example.thunder2;

//PC방 상세정보창이여, init() 걍 이미지 잘 들어가나 확인하는 거야

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class aboutPC extends AppCompatActivity {

    RecyclerView rcv;
    RcvAdapter_aboutPC rcvAdapter;
    ArrayList<DataForm_forPC> list= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pclist_aboutpc);

        init();

        rcv=(RecyclerView)findViewById(R.id.second_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));


        rcvAdapter=new RcvAdapter_aboutPC(this, list);
        rcv.setAdapter(rcvAdapter);

    }




    private void init(){
        DataForm_forPC a=new DataForm_forPC("넥스트");
        list.add(a);

        a=new DataForm_forPC("고릴라");
        list.add(a);

        a=new DataForm_forPC("조이칸");
        list.add(a);


    }
}

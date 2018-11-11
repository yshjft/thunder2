 package com.example.thunder2;

 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.LinearLayoutManager;
 import android.support.v7.widget.RecyclerView;

 import java.util.ArrayList;

 public class list_contest extends AppCompatActivity {

     RecyclerView rcv;
     RcvAdapter_forContest rcvAdapter;
     ArrayList<DataForm_forContest> list= new ArrayList<>();


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main_contestinformation);

         init();

         rcv = (RecyclerView) findViewById(R.id.contest_rcv1);
         rcv.setLayoutManager(new LinearLayoutManager(this));

         rcvAdapter = new RcvAdapter_forContest(this, list);
         rcv.setAdapter(rcvAdapter);

     }

     private void init(){
         DataForm_forContest a=new DataForm_forContest("대회1");
         list.add(a);

         a=new DataForm_forContest("대회2");
         list.add(a);


         a=new DataForm_forContest("대회3");
         list.add(a);

         a=new DataForm_forContest("대회4");
         list.add(a);

         a=new DataForm_forContest("대회5");
         list.add(a);

         a=new DataForm_forContest("대회6");
         list.add(a);

         a=new DataForm_forContest("대회7");
         list.add(a);

     }




 }

 package com.example.thunder2;


 import android.os.Bundle;
 import android.support.v7.widget.LinearLayoutManager;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.RecyclerView;


 import java.util.ArrayList;

 public class pcList extends AppCompatActivity {

     RecyclerView rcv;
     RcvAdapter rcvAdapter;
     ArrayList<DataForm> list= new ArrayList<>();


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main_pclist);

         init();

         rcv=(RecyclerView)findViewById(R.id.main_rcv);
         rcv.setLayoutManager(new LinearLayoutManager(this));

         rcvAdapter=new RcvAdapter(this, list);
         rcv.setAdapter(rcvAdapter);
     }

     private void init(){
         DataForm a=new DataForm("넥스트");
         list.add(a);

         a=new DataForm("고릴라");
         list.add(a);

         a=new DataForm("조이칸");
         list.add(a);

         a=new DataForm("큐브");
         list.add(a);

         a=new DataForm("해라");
         list.add(a);

         a=new DataForm("인디고");
         list.add(a);

         a=new DataForm("스타덤");
         list.add(a);

         a=new DataForm("다락");
         list.add(a);


     }

 }

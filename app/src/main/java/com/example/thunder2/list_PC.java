 package com.example.thunder2;


 import android.os.Bundle;
 import android.support.v7.widget.LinearLayoutManager;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.RecyclerView;


 import java.util.ArrayList;

 public class list_PC extends AppCompatActivity {

     RecyclerView rcv;
     RcvAdapter_forPC rcvAdapter;
     ArrayList<DataForm_forPC> list= new ArrayList<>();


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main_pclist);

         init();

         rcv=(RecyclerView)findViewById(R.id.main_rcv);
         rcv.setLayoutManager(new LinearLayoutManager(this));

         rcvAdapter=new RcvAdapter_forPC(this, list);
         rcv.setAdapter(rcvAdapter);
     }

     private void init(){
         DataForm_forPC a=new DataForm_forPC("넥스트");
         list.add(a);

         a=new DataForm_forPC("고릴라");
         list.add(a);

         a=new DataForm_forPC("조이칸");
         list.add(a);

         a=new DataForm_forPC("큐브");
         list.add(a);

         a=new DataForm_forPC("해라");
         list.add(a);

         a=new DataForm_forPC("인디고");
         list.add(a);

         a=new DataForm_forPC("스타덤");
         list.add(a);

         a=new DataForm_forPC("다락");
         list.add(a);


     }

 }

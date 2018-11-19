 package com.example.thunder2;

 import android.os.Bundle;
 import android.support.annotation.NonNull;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.LinearLayoutManager;
 import android.support.v7.widget.RecyclerView;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.Spinner;

 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

 import java.util.ArrayList;

 public class list_contest extends AppCompatActivity {

     RecyclerView rcv;
     RcvAdapter_forContest rcvAdapter;
     ArrayList<DTOaboutContest> contestList= new ArrayList<>();
     private DatabaseReference mDatabase;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main_contestinformation);

         mDatabase= FirebaseDatabase
                 .getInstance()
                 .getReference()
                 .child("Contest");




         rcv = (RecyclerView) findViewById(R.id.contest_rcv1);
         rcv.setLayoutManager(new LinearLayoutManager(this));
         rcvAdapter = new RcvAdapter_forContest(this, contestList);
         rcv.setAdapter(rcvAdapter);

         mDatabase.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 contestList.clear();
                 for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                     contestList.add(snapshot.getValue(DTOaboutContest.class));
                 }
                 //필터링시작

                 //필터링끝
                 rcvAdapter.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


         Spinner s = (Spinner)findViewById(R.id.spinner);
         s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

             @Override
             public void onNothingSelected(AdapterView<?> parent) {}
         });

     }

 }

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

 /*리그오브 레전드 0
   배틀그라운드 1
   포트나이트 2
   오버워치 3
   스타크래프트 4
   기타 5
  */

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

//         mDatabase.addValueEventListener(new ValueEventListener() {
//             @Override
//             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                 contestList.clear();
//                 for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                     contestList.add(snapshot.getValue(DTOaboutContest.class));
//                 }
//                 rcvAdapter.notifyDataSetChanged();
//             }
//
//             @Override
//             public void onCancelled(@NonNull DatabaseError databaseError) {
//
//             }
//         });


         Spinner s = (Spinner)findViewById(R.id.spinner);
         s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if(position==0){
                     mDatabase.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             contestList.clear();
                             for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                 if(0==snapshot.getValue(DTOaboutContest.class).getEvent()){
                                     contestList.add(snapshot.getValue(DTOaboutContest.class));
                                 }
                             }
                             rcvAdapter.notifyDataSetChanged();
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) { }
                     });
                 }else if(position==1){
                     mDatabase.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             contestList.clear();
                             for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                 if(1==snapshot.getValue(DTOaboutContest.class).getEvent()){
                                     contestList.add(snapshot.getValue(DTOaboutContest.class));
                                 }
                             }
                             rcvAdapter.notifyDataSetChanged();
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) { }
                     });
                 }else if(position==2){
                     mDatabase.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             contestList.clear();
                             for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                 if(2==snapshot.getValue(DTOaboutContest.class).getEvent()){
                                     contestList.add(snapshot.getValue(DTOaboutContest.class));
                                 }
                             }
                             rcvAdapter.notifyDataSetChanged();
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) { }
                     });
                 }else if(position==3){
                     mDatabase.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             contestList.clear();
                             for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                 if(3==snapshot.getValue(DTOaboutContest.class).getEvent()){
                                     contestList.add(snapshot.getValue(DTOaboutContest.class));
                                 }
                             }
                             rcvAdapter.notifyDataSetChanged();
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) { }
                     });
                 }else if(position==4){
                     mDatabase.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             contestList.clear();
                             for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                 if(4==snapshot.getValue(DTOaboutContest.class).getEvent()){
                                     contestList.add(snapshot.getValue(DTOaboutContest.class));
                                 }
                             }
                             rcvAdapter.notifyDataSetChanged();
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) { }
                     });
                 }else if(position==5){
                     mDatabase.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             contestList.clear();
                             for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                 if(5==snapshot.getValue(DTOaboutContest.class).getEvent()){
                                     contestList.add(snapshot.getValue(DTOaboutContest.class));
                                 }
                             }
                             rcvAdapter.notifyDataSetChanged();
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) { }
                     });
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parent) {}
         });
     }
 }

 package com.example.thunder2;

 import android.content.Intent;
 import android.os.Bundle;
 import android.support.annotation.NonNull;
 import android.support.v7.app.AppCompatActivity;
 import android.util.Log;
 import android.view.KeyEvent;
 import android.view.View;
 import android.widget.Button;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.auth.FirebaseAuthSettings;
 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

 public class setting_market extends AppCompatActivity {
     private String UID;

     private String location;
     private String seatKind;
     private int seat_total;
     private String spec;
     private String name;
     private String notice;
     private String uid;
     private int seatUnuse;

     DatabaseReference mDatabase;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main_setting_market);


         Button SignOut=(Button)findViewById(R.id.SignOut_btn);
         SignOut.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 FirebaseAuth.getInstance().signOut();
                 finish();
                 Toast.makeText(getApplication(), "Sign Out", Toast.LENGTH_SHORT).show();
             }
         });

         String tmpUID=getIntent().getStringExtra("stringUID");
         UID=tmpUID;//UID를 다른 함수 에서도 사용할 수 있도록 전역변수에 할당
     }

     public void onButton_market_manage(View v){

         Intent intent=new Intent(getApplication(), setting_market_manage.class);
         intent.putExtra("UID", UID);
         startActivity(intent);

     }

     public void onButton_notice_contest(View v){
         mDatabase= FirebaseDatabase.getInstance().getReference().child("PCL").child(UID);
         mDatabase.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if(dataSnapshot.getValue(DTOaboutPC.class)==null){
                     Toast.makeText(getApplicationContext(), "PC방이 등록돼지 않았습니다.", Toast.LENGTH_LONG).show();
                 }else{
                     Intent intent= new Intent(getApplication(), setting_market_contestManage.class);
                     intent.putExtra("UID", UID);
                     startActivity(intent);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) { }
         });
     }


     @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {
         switch (keyCode) {
             case KeyEvent.KEYCODE_BACK:
                 return true;
         }
         return super.onKeyDown(keyCode, event);
     }

 }

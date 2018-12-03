 package com.example.thunder2;

 import android.content.Context;
 import android.content.DialogInterface;
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

 import com.google.android.gms.tasks.OnCompleteListener;
 import com.google.android.gms.tasks.OnFailureListener;
 import com.google.android.gms.tasks.OnSuccessListener;
 import com.google.android.gms.tasks.Task;
 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.auth.FirebaseAuthSettings;
 import com.google.firebase.auth.FirebaseUser;
 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;
 import com.google.firebase.storage.FirebaseStorage;
 import com.google.firebase.storage.StorageReference;

 public class setting_market extends AppCompatActivity {
     private String UID;

     DatabaseReference mDatabase=null;
     DatabaseReference mDatabase2=null;
     DatabaseReference mDatabase3=null;
     Context mContext=this;
     DTOaboutPC aboutPC=null;

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
                 Toast.makeText(getApplication(), "로그아웃", Toast.LENGTH_SHORT).show();
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
                     Toast.makeText(getApplicationContext(), "PC방이 등록돼지 않았습니다.", Toast.LENGTH_SHORT).show();
                 }else if(dataSnapshot.getValue(DTOaboutPC.class)!=null){
                     aboutPC=dataSnapshot.getValue(DTOaboutPC.class);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) { }
         });

         if(aboutPC!=null){
             Intent intent= new Intent(getApplication(), setting_market_contestManage.class);
             intent.putExtra("UID", UID);
             startActivity(intent);
         }
     }

     public void onButton_withdraw(View view){

         android.support.v7.app.AlertDialog.Builder intDialog = new android.support.v7.app.AlertDialog.Builder(mContext);
         intDialog.setTitle("삭제");
         intDialog.setMessage("계정을 삭제합니다.");
         intDialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which) {

                 FirebaseStorage storage= FirebaseStorage.getInstance();
                 StorageReference storageRef=storage.getReference();
                 StorageReference desertRef = storageRef.child("market").child(UID);
                 desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void aVoid) { }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) { }
                 });

                 final StorageReference desertRef2=storageRef.child("contest");
                 mDatabase3=FirebaseDatabase.getInstance().getReference().child("Contest");
                 mDatabase3.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                             if(UID.equals(snapshot.getValue(DTOaboutContest.class).getUid())==true){
                                 desertRef2.child(snapshot.getValue(DTOaboutContest.class).getkey()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                     @Override
                                     public void onSuccess(Void aVoid) { }
                                 }).addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull Exception e) { }
                                 });
                             }
                         }
                     }
                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) { }
                 });



                 mDatabase2=FirebaseDatabase.getInstance().getReference().child("Contest");
                 mDatabase2.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                             if(UID.equals(snapshot.getValue(DTOaboutContest.class).getUid())==true){
                                 mDatabase2.child(snapshot.getValue(DTOaboutContest.class).getkey()).setValue(null);
                             }
                         }
                     }
                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) { }
                 });

                 mDatabase= FirebaseDatabase.getInstance().getReference();
                 mDatabase.child("PCL").child(UID).setValue(null);

                 FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                 user.delete()
                         .addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 if (task.isSuccessful()) {
                                     Toast.makeText(getApplicationContext(), "계정이 정상적으로 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                                     finish();
                                 }
                             }
                         });
             }
         }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which) {
                 dialog.cancel();
             }
         }).create().show();
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

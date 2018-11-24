package com.example.thunder2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class setting_market_contestManage extends AppCompatActivity {

    RecyclerView rcv;
    RcvAdapter_correctionContest rcvAdapter;
    ArrayList<DTOaboutContest> contestlist= new ArrayList<>();

    private String UID;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting_market_contestmanage);
        UID=getIntent().getStringExtra("UID");

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Contest");

        rcv=(RecyclerView)findViewById(R.id.main_rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcvAdapter=new RcvAdapter_correctionContest(this, contestlist);
        rcv.setAdapter(rcvAdapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contestlist.clear();
                Log.d("jesus", "uid : "+UID);

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(UID.equals(snapshot.getValue(DTOaboutContest.class).getUid())==true){
                        Log.d("jesus", "uid2 : "+snapshot.getValue(DTOaboutContest.class).getUid());
                        contestlist.add(snapshot.getValue(DTOaboutContest.class));
                    }
                }
                rcvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

//    private void init(){
//
//        DataForm_correctionContest a=new DataForm_correctionContest("대회1 수정하기");
//        list.add(a);
//
//    }


    public void onButton_add_contest(View v) {
        Intent intent = new Intent(getApplicationContext(),setting_market_contestManage_addContest.class);
        intent.putExtra("UID", UID);
        startActivity(intent);
    }


}

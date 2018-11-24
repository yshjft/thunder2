package com.example.thunder2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class setting_market_manage extends AppCompatActivity {

    private static int PICK_IMAGE_REQUEST1=1;
    private static int PICK_IMAGE_REQUEST2=2;
    private static int PICK_IMAGE_REQUEST3=3;
    ImageView imgView;

   private String getUID;


    private String warn="입력된 값이 존재하지 않습니다.";
    DatabaseReference mDatabase1;
    DatabaseReference mDatabase2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting_market_mange);

        String tmpUID=getIntent().getStringExtra("UID");
        getUID=tmpUID;

        mDatabase1= FirebaseDatabase.getInstance().getReference().child("PCL");
        mDatabase1.child(getUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    EditText pcLocation=(EditText)findViewById(R.id.location);
                    pcLocation.setText(dataSnapshot.getValue(DTOaboutPC.class).getLocation());
                }catch(Exception e){ }

                try{
                    EditText pcSeatKind=(EditText)findViewById(R.id.seatKind);
                    pcSeatKind.setText(dataSnapshot.getValue(DTOaboutPC.class).getSeatKind());
                }catch(Exception e){}

                try{
                    EditText pcTotal=(EditText)findViewById(R.id.totalSeat);
                    pcTotal.setText(dataSnapshot.getValue(DTOaboutPC.class).getSeat_total()+"");

                }catch(Exception e){}

                try{
                    EditText pcSpec=(EditText)findViewById(R.id.spec);
                    pcSpec.setText(dataSnapshot.getValue(DTOaboutPC.class).getSpec());
                }catch(Exception e){}

                try{
                    EditText pcName=(EditText)findViewById(R.id.name);
                    pcName.setText(dataSnapshot.getValue(DTOaboutPC.class).getName());
                }catch(Exception e){}

                try{
                    EditText pcNotice=(EditText)findViewById(R.id.notice);
                    pcNotice.setText(dataSnapshot.getValue(DTOaboutPC.class).getNotice());
                }catch(Exception e){}
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public void onButton_pc1(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST1);
    }

    public void onButton_pc2(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST2);
    }

    public void onButton_pc3(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST3);
    }

    public void onButton_confirm(View view){

        mDatabase1 = FirebaseDatabase
                .getInstance()
                .getReference();

        DTOaboutPC aboutPC = new DTOaboutPC();

        EditText pcName = (EditText) findViewById(R.id.name);
        String Name = pcName.getText().toString();
        aboutPC.setName(Name);

        EditText pcLocation = (EditText) findViewById(R.id.location);
        String Location = pcLocation.getText().toString();
        aboutPC.setLocation(Location);

        EditText pcTotal = (EditText) findViewById(R.id.totalSeat);
        int TotalSeat = Integer.parseInt(pcTotal.getText().toString());
        aboutPC.setSeat_total(TotalSeat);


        EditText pcSeatKind = (EditText) findViewById(R.id.seatKind);
        String SeatKind = pcSeatKind.getText().toString();
        aboutPC.setSeatKind(SeatKind);

        EditText pcSpec = (EditText) findViewById(R.id.spec);
        String Spec = pcSpec.getText().toString();
        aboutPC.setSpec(Spec);

        EditText pcNotice = (EditText) findViewById(R.id.notice);
        String Notice = pcNotice.getText().toString();
        aboutPC.setNotice(Notice);

        aboutPC.setUID(getUID);
        aboutPC.setSeatUnuse(TotalSeat);


       mDatabase1.child("PCL").child(getUID).setValue(aboutPC);
       Toast.makeText(getApplicationContext(), "입력 및 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
       finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == RESULT_OK && null != data) {
                Uri uri = data.getData();



                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                imgView = (ImageView) findViewById(R.id.pc1);
                imgView.setImageBitmap(scaled);

            }else if(requestCode == PICK_IMAGE_REQUEST2 && resultCode == RESULT_OK && null != data) {
                Uri uri = data.getData();



                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                imgView = (ImageView) findViewById(R.id.pc2);
                imgView.setImageBitmap(scaled);

            }else if(requestCode == PICK_IMAGE_REQUEST3 && resultCode == RESULT_OK && null != data) {
                Uri uri = data.getData();

               

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                imgView = (ImageView) findViewById(R.id.pc3);
                imgView.setImageBitmap(scaled);

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


}

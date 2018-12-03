package com.example.thunder2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class setting_market_manage extends AppCompatActivity {

    private static int PICK_IMAGE_REQUEST1=1;
    ImageView imgView;

   private String getUID;


    DatabaseReference mDatabase1;
    Uri uri1;
    private String downloadurl;
    DTOaboutPC aboutPC = new DTOaboutPC();
    Context context=this;


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

                }catch(Exception e){

                }

                try{
                    ImageView target = (ImageView) findViewById(R.id.pc1);
                    downloadurl=dataSnapshot.getValue(DTOaboutPC.class).getImage();
                    Glide.with(context)
                            .load(dataSnapshot.getValue(DTOaboutPC.class).getImage())
                            .into(target);

                }catch(Exception e){ }

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


    public void onButton_confirm(View view){
        try {
            mDatabase1 = FirebaseDatabase
                    .getInstance()
                    .getReference();


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



            uploadFile(uri1);

            if(downloadurl==null && uri1==null){
                Toast.makeText(getApplicationContext(), "사진을 입력해주세요.", Toast.LENGTH_SHORT).show();
            }else{
                new Handler().postDelayed(new Runnable()
                {
                    public void run(){
                        mDatabase1.child("PCL").child(getUID).setValue(aboutPC);
                        Toast.makeText(getApplicationContext(), "입력 및 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                },10000);

            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "입력이 완료되지 않았습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == RESULT_OK && null != data) {
                uri1 = data.getData();


                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri1);
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                imgView = (ImageView) findViewById(R.id.pc1);
                imgView.setImageBitmap(scaled);

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


    private void uploadFile(Uri uri) {
        if(uri!=null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();


            StorageReference storageRef = storage.getReference();
            final StorageReference riversRef = storageRef.child("market").child(getUID);
            final UploadTask uploadTask= riversRef.putFile(uri);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return riversRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        aboutPC.setImage(downloadUri.toString());
                    } else {

                    }
                }
            });
        }else{
            aboutPC.setImage(downloadurl);
        }
    }

}

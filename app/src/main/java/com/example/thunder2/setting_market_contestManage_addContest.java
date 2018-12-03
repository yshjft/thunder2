package com.example.thunder2;

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;
import java.util.concurrent.ExecutionException;


/*리그오브 레전드 0
   배틀그라운드 1
   포트나이트 2
   오버워치 3
   스타크래프트 4
   기타 5
  */


public class setting_market_contestManage_addContest extends AppCompatActivity{

    private static int PICK_IMAGE_REQUEST=1;
    ImageView imgView;
    private String UID;
    private String key;
    private Uri uri;

    DatabaseReference mDatabase;
    DTOaboutContest aboutContest=new DTOaboutContest();


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting_market_contestmanage_addcontest);
        UID=getIntent().getStringExtra("UID");

        Spinner s = (Spinner)findViewById(R.id.spinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aboutContest.setEvent(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        DatabaseReference mDatabase2=FirebaseDatabase.getInstance().getReference().child("PCL");
        mDatabase2.child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    EditText pcLocation = (EditText) findViewById(R.id.contest_place);
                    pcLocation.setText(dataSnapshot.getValue(DTOaboutPC.class).getLocation());
                }catch(Exception e){}


                try {
                    EditText pcName = (EditText) findViewById(R.id.contest_pcName);
                    pcName.setText(dataSnapshot.getValue(DTOaboutContest.class).getName());
                }catch (Exception e){ }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });


    }

    public void add_contest_image(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }


    public void onButton_add(View view){

        for(int i=0; i<10;i++){
            key= UUID.randomUUID().toString();
        }

        mDatabase= FirebaseDatabase
                .getInstance()
                .getReference();

        EditText contestName = (EditText) findViewById(R.id.contest_name);
        String Name = contestName.getText().toString();
        aboutContest.setName(Name);

        EditText contestpcName = (EditText) findViewById(R.id.contest_pcName);
        String  pcName= contestpcName.getText().toString();
        aboutContest.setHost(pcName);

        EditText contestPlace = (EditText) findViewById(R.id.contest_place);
        String pcPlace = contestPlace.getText().toString();
        aboutContest.setLocation(pcPlace);

        EditText contestDate = (EditText) findViewById(R.id.contest_date);
        String Date = contestDate.getText().toString();
        aboutContest.setDate(Date);

        EditText contestDeadline = (EditText) findViewById(R.id.contest_deadline);
        String Deadline = contestDeadline.getText().toString();
        aboutContest.setDeadline(Deadline);


        EditText contestPrize = (EditText) findViewById(R.id.contest_prize);
        String Prize = contestPrize.getText().toString();
        aboutContest.setPrize(Prize);

        EditText contestQual = (EditText) findViewById(R.id.contest_qual);
        String Qual = contestQual.getText().toString();
        aboutContest.setQuali(Qual);

        EditText contestHow = (EditText) findViewById(R.id.contest_how);
        String How = contestHow.getText().toString();
        aboutContest.setHow(How);

        EditText contestEtc = (EditText) findViewById(R.id.contest_etc);
        String Etc = contestEtc.getText().toString();
        aboutContest.setETC(Etc);

        aboutContest.setUid(UID);
        aboutContest.setkey(key);

        uploadFile(uri);


        if(Name.equals("")){
            Toast.makeText(getApplicationContext(), "대회 이름이 입력이 되지 않았습니다.", Toast.LENGTH_SHORT).show();
        }else if(Date.equals("")) {
            Toast.makeText(getApplicationContext(), "대회 날짜가 입력이 되지 않았습니다.", Toast.LENGTH_SHORT).show();
        }else if(Deadline.equals("")){
            Toast.makeText(getApplicationContext(), "대회 신청기한 입력이 되지 않았습니다.", Toast.LENGTH_SHORT).show();
        }else if(uri==null) {
            Toast.makeText(getApplicationContext(), "사진이 입력 되지 않았습니다.", Toast.LENGTH_SHORT).show();
        }else{
            new Handler().postDelayed(new Runnable()
            {
                public void run(){
                    mDatabase.child("Contest").child(key).setValue(aboutContest);
                    Toast.makeText(getApplicationContext(), "대회가 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            },10000);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                uri = data.getData();//uri  가능성 있다. DB에서 할때 준비할 것


                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                imgView = (ImageView) findViewById(R.id.contest1);
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
            final StorageReference riversRef = storageRef.child("contest").child(key);
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
                        aboutContest.setImage(downloadUri.toString());
                    } else { }
                }
            });
        }
    }
}

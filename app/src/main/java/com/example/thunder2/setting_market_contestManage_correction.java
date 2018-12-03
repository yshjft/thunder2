package com.example.thunder2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class setting_market_contestManage_correction extends AppCompatActivity{

    private static int PICK_IMAGE_REQUEST=1;
    ImageView imgView;
    private String UID;
    private String key;
    private String downloadurl;
    private Uri uri;

    DatabaseReference mDatabase;
    DTOaboutContest aboutContest=new DTOaboutContest();
    Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting_market_contestmanage_correction);

        ImageView target = (ImageView) findViewById(R.id.contest2);
        downloadurl=getIntent().getStringExtra("stringImage");
        Glide.with(context)
                .load(downloadurl)
                .into(target);


        EditText Name=(EditText)findViewById(R.id.contest_name);
        Name.setText(getIntent().getStringExtra("stringName"));

        EditText pcName=(EditText)findViewById(R.id.contest_host);
        pcName.setText(getIntent().getStringExtra("stringHost"));

        EditText Locaiton=(EditText)findViewById(R.id.contest_place);
        Locaiton.setText(getIntent().getStringExtra("stringLocation"));

        EditText Date=(EditText)findViewById(R.id.contest_date);
        Date.setText(getIntent().getStringExtra("stringDate"));

        EditText Deadline=(EditText)findViewById(R.id.contest_deadline);
        Deadline.setText(getIntent().getStringExtra("stringDeadline"));


        int Event=getIntent().getIntExtra("intEvent", 0);
        TextView event=(TextView)findViewById(R.id.contest_event);
        if(Event==0){
            String game="리그오브레전드";
            event.setText(game);
        }else if(Event==1){
            String game="배틀그라운드";
            event.setText(game);
        }else if(Event==2){
            String game="포트나이트";
            event.setText(game);
        }else if(Event==3){
            String game="오버워치";
            event.setText(game);
        }else if(Event==4){
            String game="스타크래프트";
            event.setText(game);
        }else if(Event==5){
            String game="기타";
            event.setText(game);
        }

        EditText Prize=(EditText)findViewById(R.id.contest_prize);
        Prize.setText(getIntent().getStringExtra("stringPrize"));

        EditText Quali=(EditText)findViewById(R.id.contest_qual);
        Quali.setText(getIntent().getStringExtra("stringQuali"));

        EditText How=(EditText)findViewById(R.id.contest_how);
        How.setText(getIntent().getStringExtra("stringHow"));

        EditText Etc=(EditText)findViewById(R.id.contest_etc);
        Etc.setText(getIntent().getStringExtra("stringEtc"));

        UID=getIntent().getStringExtra("stringUID");
        key=getIntent().getStringExtra("stringKey");



        Spinner s = (Spinner)findViewById(R.id.spinner);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aboutContest.setEvent(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


    }

    public void onButton_contest_correction(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                imgView = (ImageView) findViewById(R.id.contest2);
                imgView.setImageBitmap(scaled);

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


    public void onButton_correction(View view){
        mDatabase= FirebaseDatabase
                .getInstance()
                .getReference();

        EditText contestName = (EditText) findViewById(R.id.contest_name);
        String Name = contestName.getText().toString();
        aboutContest.setName(Name);

        EditText contestHost = (EditText) findViewById(R.id.contest_host);
        String pcName = contestHost.getText().toString();
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
            Toast.makeText(getApplicationContext(), "대회 이름 수정에 오류가 있습니다.", Toast.LENGTH_SHORT).show();
        }else if(Date.equals("")) {
            Toast.makeText(getApplicationContext(), "대회 날짜 수정에 오류가 있습니다.", Toast.LENGTH_SHORT).show();
        }else if(Deadline.equals("")){
            Toast.makeText(getApplicationContext(), "대회 신청기한 수정에 오류가 있습니다.", Toast.LENGTH_SHORT).show();
        }else{
            new Handler().postDelayed(new Runnable()
            {
                public void run(){
                    mDatabase.child("Contest").child(key).setValue(aboutContest);
                    Toast.makeText(getApplicationContext(), "대회가 수정되었습니다. 변경 사항에 이상이 없는지 확인해주세요.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            },10000);
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
        }else{
            aboutContest.setImage(downloadurl);
        }
    }

}

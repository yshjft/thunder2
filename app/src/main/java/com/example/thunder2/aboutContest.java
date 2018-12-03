package com.example.thunder2;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/*리그오브 레전드 0
   배틀그라운드 1
   포트나이트 2
   오버워치 3
   스타크래프트 4
   기타 5
  */

public class aboutContest extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;
    Context mContext;

    private int event;

    private String locationName;//주소 스트링 값
    private double mlat;//위도 전달
    private double mlng;//경도 전달
    private String pcRooomName;//PC방 이름 전달



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contestinformation_aboutcontest);
        mContext=this;


        String getDate=getIntent().getStringExtra("stringDate");
        String getDeadline=getIntent().getStringExtra("stringDeadline");
        String getETC=getIntent().getStringExtra("stringETC");
        int getEvent=getIntent().getIntExtra("intEvent", 1);
        String getHost=getIntent().getStringExtra("stringHost");
        pcRooomName=getHost;
        String getLocation=getIntent().getStringExtra("stringLocation");
        locationName=getLocation;
        String getName=getIntent().getStringExtra("stringName");
        String getPrize=getIntent().getStringExtra("stringPrize");
        String getQualification=getIntent().getStringExtra("stringQualification");
        String getHow=getIntent().getStringExtra("stringHow");

        String getImage=getIntent().getStringExtra("stringImage");

        ImageView target = (ImageView) findViewById(R.id.contest_image);
        Glide.with(mContext)
                .load(getImage)
                .into(target);


        TextView name=(TextView)findViewById(R.id.contest_name);
        name.setText(getName);
        TextView location=(TextView)findViewById(R.id.contest_address);
        location.setText(getLocation);
        TextView date=(TextView)findViewById(R.id.contest_date);
        date.setText(getDate);
        TextView deadline=(TextView)findViewById(R.id.contest_deadline);
        deadline.setText(getDeadline);


        TextView event=(TextView)findViewById(R.id.contest_event);
        if(getEvent==0){
            String game="리그오브레전드";
            event.setText(game);
        }else if(getEvent==1){
            String game="배틀그라운드";
            event.setText(game);
        }else if(getEvent==2){
            String game="포트나이트";
            event.setText(game);
        }else if(getEvent==3){
            String game="오버워치";
            event.setText(game);
        }else if(getEvent==4){
            String game="스타크래프트";
            event.setText(game);
        }else if(getEvent==5){
            String game="기타";
            event.setText(game);
        }



        TextView how=(TextView)findViewById(R.id.contest_how);
        how.setText(getHow);
        TextView prize=(TextView)findViewById(R.id.contest_prize);
        prize.setText(getPrize);
        TextView qualification=(TextView)findViewById(R.id.contest_qual);
        qualification.setText(getQualification);
        TextView etc=(TextView)findViewById(R.id.contest_etc);
        etc.setText(getETC);




        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    public void onMapReady(final GoogleMap map) {
        //지오 코딩 주소 ->좌표 시작
        Geocoder mGeoCoder = new Geocoder(mContext);
        try{
            List<Address> mResultLocation=mGeoCoder.getFromLocationName(locationName,1);
            double mLat=mResultLocation.get(0).getLatitude();
            mlat=mLat;
            double mLng=mResultLocation.get(0).getLongitude();
            mlng=mLng;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "주소를 변환할 수 없습니다.", Toast.LENGTH_SHORT);
        }
        //지오 코딩 주소->좌표 끝

        double mLatitude = mlat;
        double mLongitude = mlng;
        mMap = map;
        LatLng now = new LatLng(mLatitude, mLongitude);   //현재 위치
        MarkerOptions marketOptions = new MarkerOptions();
        marketOptions.position(now);    //지금 위치로 포지션
        marketOptions.title(pcRooomName);
        marketOptions.snippet(locationName);
        mMap.addMarker(marketOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(now));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
    }



}

package com.example.lab8;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ChooseLocationActivity extends AppCompatActivity implements OnMapReadyCallback,
        LocationListener,
        GoogleMap.OnMarkerClickListener ,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapClickListener{

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    LatLng mLastLocation;
    Marker mCurrLocationMarker;

    String Title;
    String Content;
    String Money;
    String Date1;
    String Date2;
    String Time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        setTitle("選取地點");

        Title = getIntent().getStringExtra("Title");
        Content = getIntent().getStringExtra("Content");
        Money = getIntent().getStringExtra("Money");
        Date1 = getIntent().getStringExtra("Date1");
        Date2 = getIntent().getStringExtra("Date2");
        Time = getIntent().getStringExtra("Time");


        findViewById(R.id.backButtonInMap).setOnClickListener(view -> {

            Intent intent = new Intent(this,AddQuestActivity.class);
            intent.putExtra("Lat", getIntent().getDoubleExtra("Lat",0));
            intent.putExtra("Lon",getIntent().getDoubleExtra("Lon",0));

            intent.putExtra("Title", Title);
            intent.putExtra("Content", Content);
            intent.putExtra("Money", Money);
            intent.putExtra("Date1", Date1);
            intent.putExtra("Date2", Date2);
            intent.putExtra("Time", Time);

            startActivity(intent);
        });
        findViewById(R.id.VerifyButton).setOnClickListener(view->{


            Intent intent = new Intent(this,AddQuestActivity.class);
            intent.putExtra("Lat", mLastLocation.latitude);
            intent.putExtra("Lon",mLastLocation.longitude);

            intent.putExtra("Title", Title);
            intent.putExtra("Content", Content);
            intent.putExtra("Money", Money);
            intent.putExtra("Date1", Date1);
            intent.putExtra("Date2", Date2);
            intent.putExtra("Time", Time);

            Toast.makeText(this,"位置資料更新成功",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });


        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setCompassEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mGoogleMap.getUiSettings().setAllGesturesEnabled(true);



        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                 mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
             mGoogleMap.setMyLocationEnabled(true);
        }
        mCurrLocationMarker = mGoogleMap.addMarker(new MarkerOptions().
                position(new LatLng(24.9437013,121.3709149)).
                title("你的位置").draggable(true));

        mLastLocation = mCurrLocationMarker.getPosition();
        mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(mLastLocation,11,0,0)));

        mGoogleMap.setOnMarkerClickListener(this);
        mGoogleMap.setOnMapClickListener(this);
        mGoogleMap.setOnMarkerDragListener(this);

    }
    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("需要位置資訊權限")
                        .setMessage("請開啟對本APP的位置權限,才能使用本功能")
                        .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(ChooseLocationActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "沒有權限!", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        mLastLocation = marker.getPosition();
        return false;
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {
        mLastLocation = marker.getPosition();
        notifyUser();
    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        mLastLocation = marker.getPosition();
        notifyUser();
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {
        mLastLocation = marker.getPosition();
        notifyUser();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        mLastLocation = latLng;
        mCurrLocationMarker.setPosition(latLng);
        notifyUser();
    }
    public void notifyUser(){
        Toast.makeText(this,"當前經緯度("+mLastLocation.longitude+","+mLastLocation.latitude+")",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        return;
    }
}
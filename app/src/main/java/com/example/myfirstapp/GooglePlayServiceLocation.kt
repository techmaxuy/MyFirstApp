package com.example.myfirstapp

import android.annotation.SuppressLint
import android.location.LocationProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_google_play_service_location.*

class GooglePlayServiceLocation : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    val REQUEST_CODE = 1000;

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_play_service_location)


        if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION))
        {
            ActivityCompat.requestPermissions(this  , arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
        }
        else
        {
            Buildlocationrequest()
            buildlocationcallback()

            //Create Fused Provider Client
            fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)

            //Set Event
            btn_start_updates.setOnClickListener(View.OnClickListener {

                fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())
            });
        }
    }

    private fun Buildlocationrequest() {

    }

    private fun buildlocationcallback() {

    }
}

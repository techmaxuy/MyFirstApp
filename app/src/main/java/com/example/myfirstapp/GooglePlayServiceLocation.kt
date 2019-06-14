package com.example.myfirstapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
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

                if (ActivityCompat.checkSelfPermission(this@GooglePlayServiceLocation,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this@GooglePlayServiceLocation,android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this@GooglePlayServiceLocation, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
                    return@OnClickListener
                }
                fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())

                //Change the state of button

                btn_start_updates.isEnabled = !btn_start_updates.isEnabled
                btn_stop_updates.isEnabled=!btn_stop_updates.isEnabled
            });
            btn_stop_updates.setOnClickListener(View.OnClickListener {
                if (ActivityCompat.checkSelfPermission(this@GooglePlayServiceLocation,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this@GooglePlayServiceLocation,android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this@GooglePlayServiceLocation, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
                    return@OnClickListener
                }
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)

                //Change the state of button

                btn_start_updates.isEnabled = !btn_start_updates.isEnabled
                btn_stop_updates.isEnabled=!btn_stop_updates.isEnabled

            })
        }
    }


    private fun buildlocationcallback() {
        locationCallback=object: LocationCallback() {
            //ctrl+0
            override fun onLocationResult(p0: LocationResult?) {
                var location = p0!!.locations.get(p0!!.locations.size-1) //Get Last location
                txt_location.text=location.latitude.toString()+"/"+location.longitude.toString()
            }
        }
    }

    private fun Buildlocationrequest() {
        locationRequest=LocationRequest()
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval=5000
        locationRequest.fastestInterval=3000
        locationRequest.smallestDisplacement=10f


    }
}

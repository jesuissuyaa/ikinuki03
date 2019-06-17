package com.example.ikinuki03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        /* DEFAULT CODE
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        */

        // get clicked button id from intent
        val btnId = intent.getIntExtra(EXTRA_MESSAGE, 0)

        // coordinates for seikyou (hungry), library (bored), gym (tired)
        val gym = LatLng(35.6572031,139.5418123)
        val seikyou = LatLng(35.6571586,139.5419475)
        val library = LatLng(35.6573155,139.5426769)
        val lab = LatLng(35.6572187,139.5406295) // for error case

        // store corresponding marker position & marker title for button
        var mPos: LatLng
        var mTitle: String
        var mMsg: String
        when (btnId) {
            R.id.btn_hungry -> {
                mPos = seikyou
                mTitle = "生協"
                mMsg = "糖分補給タイムかも"
            }
            R.id.btn_bored -> {
                mPos = library
                mTitle = "図書館"
                mMsg = "面白い本があるかも"
            }
            R.id.btn_tired -> {
                mPos = gym
                mTitle = "トレセン"
                mMsg = "筋肉は裏切らない"
            }
            else -> {
                mPos = lab
                mTitle = "かしらぼ"
                mMsg = "画面遷移でエラーっぽい"
            }
        }

        // show marker -> move map -> zoom
        mMap.addMarker(MarkerOptions().position(mPos).title(mTitle))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mPos))
        mMap.moveCamera(CameraUpdateFactory.zoomTo(19.0f))

        // show toast
        Toast.makeText(this, mMsg, Toast.LENGTH_LONG).show()

        /* TEST CODE
        var msg: String
        when (btnId) {
            R.id.btn_hungry -> msg = "hungry"
            R.id.btn_bored -> msg = "bored"
            R.id.btn_tired -> msg = "tired"
            else -> msg = "intent error"
        }
        Log.d("hoshi", msg)
        */




    }
}

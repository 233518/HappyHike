package com.example.turglad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.turglad.systems.ArSystem

class MainActivity : AppCompatActivity() {

    lateinit var arSystem: ArSystem;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arSystem = ArSystem(this, this);
        setContentView(R.layout.activity_main)
    }


    override fun onResume() {
        super.onResume()
        //Check camera permission
        arSystem.checkCameraPermission();
        //Check google play service
        arSystem.checkGooglePLay();
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //Check permission camera
        arSystem.hasCameraPermissionResult(this, this)
        finish();
    }
}


package com.sun.qrcodedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView : BottomNavigationView


//    lateinit var CameraConfigurationManager :CameraConfigurationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener{ item ->

            when (item.itemId) {
                R.id.item_ScanQrCode->{
                    val scanQRFragment = ScanQRFragment()
                    changeFragment(scanQRFragment)
                }

                R.id.item_GenQrCode ->{
                    val genQRFragment = GenQRFragment()
                    changeFragment(genQRFragment)
                }

            }
            false
        }
        changeFragment(ScanQRFragment())

    }
    
    fun changeFragment(fragment: Fragment){

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content , fragment)
        fragmentTransaction.commit()
    }

}




//        tv_sacn.setOnClickListener {
//            val integrator = IntentIntegrator(this)
//            // Set to true to enable saving the barcode image and sending its path in the result Intent.
//            integrator.setBarcodeImageEnabled(true)
//            // Set to false to disable beep on scan.
//            // Set to false to disable beep on scan.
//            integrator.setBeepEnabled(false)
//            // Use the specified camera ID.
//            // Use the specified camera ID.
//            integrator.setCameraId(0)
//            // By default, the orientation is locked. Set to false to not lock.
//            // By default, the orientation is locked. Set to false to not lock.
//            integrator.setOrientationLocked(false)
//            // Set a prompt to display on the capture screen.
//            // Set a prompt to display on the capture screen.
//            integrator.setPrompt("Scan a QR Code")
//            // Initiates a scan
//            // integrator.setCaptureActivity ( MainActivity.class);
//            // Initiates a scan
//            // integrator.setCaptureActivity ( MainActivity::class.java)
//            integrator.initiateScan()
//        }
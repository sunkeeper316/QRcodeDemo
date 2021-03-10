package com.sun.qrcodedemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import pub.devrel.easypermissions.EasyPermissions


class ScanQRFragment : Fragment() , ZXingScannerView.ResultHandler , EasyPermissions.PermissionCallbacks{
    lateinit var zsv_scanner : ZXingScannerView
    lateinit var tv_result : TextView

    val PER_CAMERA = 201

    val perms  = arrayOf("android.permission.CAMERA")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scan_q_r, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zsv_scanner = view.findViewById(R.id.zsv_scanner)
        tv_result = view.findViewById(R.id.tv_result)
        if (EasyPermissions.hasPermissions(requireContext() , *perms)){
            scanner()
        }else{
            EasyPermissions.requestPermissions(this ,"camera",  PER_CAMERA , "android.permission.CAMERA")
        }

//        zsv_scanner.flash = true

    }

    override fun onDestroy() {
        super.onDestroy()
        zsv_scanner.stopCamera()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode , permissions , grantResults , this)
    }

    fun scanner(){
        zsv_scanner.setAspectTolerance(0.5f)
        zsv_scanner.setAutoFocus(true)
        zsv_scanner.setFormats(arrayListOf(BarcodeFormat.QR_CODE))
        zsv_scanner.setResultHandler(this)
        zsv_scanner.startCamera()
    }
    override fun handleResult(rawResult: Result?) {
        rawResult?.let {
            tv_result.text = it.text
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        when (requestCode){
            PER_CAMERA -> {
                scanner()
            }
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(requireContext() , "沒有相機權限" , Toast.LENGTH_SHORT).show()
    }

}
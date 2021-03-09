package com.sun.qrcodedemo

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix


class GenQRFragment : Fragment() {

    lateinit var btGenerateQrCode : Button
    lateinit var etQRCodeText : EditText
    lateinit var ivQRCode : ImageView

    var width : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gen_q_r, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btGenerateQrCode = view.findViewById(R.id.btGenerateQrCode)
        etQRCodeText = view.findViewById(R.id.etQRCodeText)
        ivQRCode = view.findViewById(R.id.ivQRCode)
        btGenerateQrCode.setOnClickListener {
            val text = etQRCodeText.text.toString()
            width = getResources().getDisplayMetrics().widthPixels;
            val bitmap = encodeAsBitmap(text)
            ivQRCode.setImageBitmap(bitmap)
        }

    }

    @Throws(WriterException::class)
    fun encodeAsBitmap(text: String) : Bitmap? {

        val WHITE = -0x1
        val BLACK = -0x1000000

        var bitMatrix : BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, width)
        }catch (e: IllegalArgumentException){
            //unsupported format
            return null;
        }
        val w = bitMatrix.width
        val h = bitMatrix.height
        val pixels = IntArray(w * h)
        // All are 0, or black, by default
        for (y in 0 until h) {
            val offset = y * w
            for (x in 0 until w) {
                pixels[offset + x] = if (bitMatrix.get(x, y)) BLACK else WHITE
            }
        }

        val bitmap = Bitmap.createBitmap( w,h,Bitmap.Config.ARGB_8888)

        bitmap.setPixels(pixels , 0 , w , 0 , 0 , w , h )
        return  bitmap
    }

}
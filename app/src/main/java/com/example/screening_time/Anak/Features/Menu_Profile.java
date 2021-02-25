package com.example.screening_time.Anak.Features;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screening_time.Anak.Fragment.Menu_Utama;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Menu_Profile extends AppCompatActivity {
    @BindView(R.id.Barcode)
    ImageView barcode;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__profile);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        String text=sharedPrefManager.getSP_IMEI();// Whatever you need to encode in the QR code
        Log.d("imei",text);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            barcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent intent=new Intent(Menu_Profile.this, Menu_Utama.class);
        startActivity(intent);
        finish();
    }
}
package com.example.screening_time.Fitur.OrangTua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.screening_time.Controller.Device;
import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.R;
import com.example.screening_time.Session.SharedPrefManager;
import com.example.screening_time.Utils.Portrait;
import com.example.screening_time.View.MyDevice;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class Sinkronisasi_Ponsel extends AppCompatActivity implements MyDevice {
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    IntentIntegrator intentIntegrator;
    private static final String TAG = Sinkronisasi_Ponsel.class.getSimpleName();
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private FusedLocationProviderClient mFusedLocationClient;
    protected Location mLastLocation;
    double Latitude;
    double Longitude;
    Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinkronisasi__ponsel);
        sharedPrefManager=new SharedPrefManager(this);
        loading=new ProgressDialog(this);
        device=new Device(this);
//        Toast.makeText(this, ""+sharedPrefManager.getSPEmail(), Toast.LENGTH_SHORT).show();
        checkPermission();
        scanow();
    }

    public void scanow() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(Portrait.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan Barcode Device");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_SHORT).show();
            } else {
//                formadddevice.setVisibility(View.VISIBLE);
//                scanbarcode.setVisibility(View.GONE);
//                No_Sn.setText(result.getContents());
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                Sikron(result.getContents(),sharedPrefManager.getSPEmail());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void Sikron(String contents, String spEmail) {
        loading.setMessage("Loading.....");
        loading.setCancelable(true);
        loading.show();
        device.sinkron(contents,spEmail);

    }

    private void checkPermission() {
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        if (!hasPermissions(Sinkronisasi_Ponsel.this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(Sinkronisasi_Ponsel.this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void truedata(List<Model_Device> devices) {

    }

    @Override
    public void ImeiTerdaftar(String Message) {
        loading.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
        back();


    }

    @Override
    public void ImeiTidakTerdaftar(String Message) {
        loading.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void NoInternet(String Message) {
        loading.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        Intent menuSebelumnya = new Intent(this, Daftar_Ponsel.class);
        startActivity(menuSebelumnya);
        finish();
    }
}

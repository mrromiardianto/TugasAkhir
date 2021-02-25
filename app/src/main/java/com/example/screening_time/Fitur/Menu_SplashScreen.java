package com.example.screening_time.Fitur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.screening_time.Controller.Device;
import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.Model.Model_laporantugas;
import com.example.screening_time.Model.Model_tugas;
import com.example.screening_time.R;
import com.example.screening_time.Session.SharedPrefManager;
import com.example.screening_time.View.MyDevice;

import java.util.List;

public class Menu_SplashScreen extends AppCompatActivity implements MyDevice{
    SharedPrefManager sharedPrefManager;
    Device device;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu__splash_screen);
//        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
        sharedPrefManager=new SharedPrefManager(this);
        loading=new ProgressDialog(this);
        device=new Device(this);
        loading.setMessage("Loading...");
        loading.setCancelable(true);
        loading.show();
        String Imei = Settings.Secure.getString(Menu_SplashScreen.this.getContentResolver(),Settings.Secure.ANDROID_ID);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_IMEI,Imei);
        checkimei(sharedPrefManager.getSP_IMEI());
    }
    public void checkimei(String Imei){
        device.CheckImei(Imei);
    }

    @Override
    public void listlaporan(List<Model_laporantugas> laporan) {

    }

    @Override
    public void truedata(List<Model_Device> devices) {

    }

    @Override
    public void ImeiTerdaftar(String Message){
        loading.dismiss();
        GotoLogin();

    }
    @Override
    public void ImeiTidakTerdaftar(String Message){
        loading.dismiss();
        GotoRegister();
    }
    @Override
    public void NoInternet(String Message){
        loading.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void suksesgetdata(List<Model_tugas> tugases) {

    }

//    @Override
//    public void suksesgetdata(List<Model_laporantugas> tugases) {
//
//    }

    public void GotoLogin(){
        Intent i=new Intent(Menu_SplashScreen.this,Menu_Login.class);
        startActivity(i);
        finish();
    }
    public void GotoRegister(){
        Intent i=new Intent(Menu_SplashScreen.this,Menu_Register.class);
        startActivity(i);
        finish();
    }
}
package com.example.screening_time.Fitur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;

import com.example.screening_time.Controller.Device;
import com.example.screening_time.R;
import com.example.screening_time.Session.SharedPrefManager;
import com.example.screening_time.View.MyDevice;

public class Menu_SplashScreen extends AppCompatActivity implements MyDevice{
    SharedPrefManager sharedPrefManager;
    Device device;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__splash_screen);
        sharedPrefManager=new SharedPrefManager(this);
        loading=new ProgressDialog(this);
        device=new Device(this);
        loading.setMessage("Loading...");
        loading.setCancelable(true);
        loading.show();
        String android_id = Settings.Secure.getString(Menu_SplashScreen.this.getContentResolver(),Settings.Secure.ANDROID_ID);
        checkimei(android_id);
    }
    public void checkimei(String Imei){
        device.CheckImei("123");
    }

    @Override
    public void ImeiTerdaftar(String Message){

    }
    @Override
    public void ImeiTidakTerdaftar(String Message){

    }
    @Override
    public void NoInternet(String Message){

    }
}
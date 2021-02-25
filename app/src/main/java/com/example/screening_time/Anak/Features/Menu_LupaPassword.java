package com.example.screening_time.Anak.Features;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screening_time.Anak.Server.Controller.Controller;
import com.example.screening_time.Anak.Server.Controller.MyController;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_LupaPassword extends AppCompatActivity implements MyController {
    @BindView(R.id.ValidasiPengingat)
    EditText namapengingat;
    @BindView(R.id.Btn_Validasi)
    Button BtnValidasi;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__lupa_password);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        controller=new Controller(this);
        loading=new ProgressDialog(this);
        BtnValidasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChexInputan();
            }
        });
    }

    private void ChexInputan() {
        loading.setMessage("Mohon Tunggu Sebentar.....");
        loading.setCancelable(false);
        loading.show();
        String pengingat=namapengingat.getText().toString();
        if (pengingat.equals("")){
            loading.dismiss();
            Toast.makeText(this, "Kolom Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else {
            String Imei=sharedPrefManager.getSP_IMEI();
            Request(Imei,pengingat);
        }
    }

    private void Request(String imei, String pengingat) {
        controller.RequestPengingat(imei,pengingat);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Menu_LupaPassword.this,Menu_Masuk.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void gagalmasuk(String Message) {
        loading.dismiss();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ImeiTidakTerdaftar() {

    }

    @Override
    public void berhasilmasuk(String Message) {
        loading.dismiss();
        Intent intent=new Intent(Menu_LupaPassword.this,Menu_ResetPassword.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void TidakAdaKoneksi(String error_message) {
        loading.dismiss();
        Toast.makeText(this, ""+error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gagalupdate(String pesan) {

    }

    @Override
    public void berhasilupdate(String pesan) {

    }

}
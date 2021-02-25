package com.example.screening_time.Anak.Features;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screening_time.Anak.Fragment.Menu_Utama;
import com.example.screening_time.Anak.Server.Controller.Controller;
import com.example.screening_time.Anak.Server.Controller.MyController;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Menu_Masuk extends AppCompatActivity implements MyController {
    @BindView(R.id.MasukPassword)
    EditText Password;
    @BindView(R.id.Btn_Masuk)
    Button Masuk;
    @BindView(R.id.Lupa_Masuk)
    Button Lupa;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__masuk);
        ButterKnife.bind(Menu_Masuk.this);
        loading = new ProgressDialog(Menu_Masuk.this);
//        ProgressDialog loading = new ProgressDialog(Menu_Masuk.this);
        Lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoReset();
            }
        });
        Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validasi();
            }
        });
    }

    private void Validasi() {
        String password=Password.getText().toString();
        if (password.equals("")){
            Toast.makeText(this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else{
//            loading = new ProgressDialog(Menu_Masuk.this);
            loading.setMessage("Mohon Tunggu...");
            loading.setCancelable(false);
            loading.show();
            SharedPrefManager sharedPrefManager=new SharedPrefManager(Menu_Masuk.this);
            RequestMasuk(sharedPrefManager.getSP_IMEI(),password);
        }
    }

    private void RequestMasuk(String Imei, String password) {
        Controller controller =new Controller(Menu_Masuk.this);
        controller.Masuk(Imei,password);
    }

    private void GotoReset() {
        Intent intent =new Intent(Menu_Masuk.this,Menu_LupaPassword.class);
        startActivity(intent);
        finish();
    }
    private void gotoMasuk(){
        Intent intent =new Intent(Menu_Masuk.this, Menu_Utama.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void gagalmasuk(String Message) {
//        loading = new ProgressDialog(Menu_Masuk.this);
//        loading.setCancelable(false);
        loading.dismiss();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ImeiTidakTerdaftar() {

    }

    @Override
    public void berhasilmasuk(String Message) {
//        loading = new ProgressDialog(Menu_Masuk.this);
        loading.dismiss();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
        gotoMasuk();


    }

    @Override
    public void TidakAdaKoneksi(String error_message) {
//        loading = new ProgressDialog(Menu_Masuk.this);
//        loading.setCancelable(false);
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
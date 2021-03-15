package com.example.screening_time.Fitur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.screening_time.Anak.Features.Menu_Dashboard;
import com.example.screening_time.Anak.Features.Menu_SplashScreen;
import com.example.screening_time.Anak.Fragment.Menu_Utama;
import com.example.screening_time.Controller.User;
import com.example.screening_time.Fitur.OrangTua.Daftar_Ponsel;
import com.example.screening_time.R;
import com.example.screening_time.Session.SharedPrefManager;
import com.example.screening_time.View.MyUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_Login extends AppCompatActivity implements MyUser {
    @BindView(R.id.email)
    EditText Email;
    @BindView(R.id.Password)
    EditText Password;
    @BindView(R.id.login)
    Button Login;
    @BindView(R.id.lupa)
    Button Lupa;

    ProgressDialog loading;
    User user;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__login);
        ButterKnife.bind(Menu_Login.this);
        ButterKnife.bind(this);
        loading = new ProgressDialog(Menu_Login.this);
        sharedPrefManager=new SharedPrefManager(this);
        user = new User(Menu_Login.this);
//        Toast.makeText(this, ""+sharedPrefManager.getSudahLogin(), Toast.LENGTH_SHORT).show();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checklogin();
            }
        });
        Check_Session();


    }

    private void Check_Session() {
        if ( sharedPrefManager.getSudahLogin()) {
            String Role=sharedPrefManager.getRole();
            if (Role.equals("Anak")){
                startActivity(new Intent(Menu_Login.this,Menu_Utama.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }else {
                startActivity(new Intent(Menu_Login.this, Daftar_Ponsel.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }

        }
    }

    private void Checklogin() {
        String password=Password.getText().toString();
        if (password.equals("")){
            Toast.makeText(this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else{
//            loading = new ProgressDialog(Menu_Masuk.this);
            loading.setMessage("Mohon Tunggu...");
            loading.setCancelable(false);
            loading.show();
            String imei = sharedPrefManager.getSP_IMEI();
            String psw = Password.getText().toString();
            RequestMasuk(imei, psw);

//            SharedPrefManager sharedPrefManager=new SharedPrefManager(Menu_Login.this);
//            RequestMasuk(sharedPrefManager.getSP_IMEI(),password);
        }
    }

    private void RequestMasuk(String Imei, String password) {
    user.userlogin(Imei, password);
    }

//    private void GotoReset() {
//        Intent intent =new Intent(Menu_Login.this,Menu_LupaPassword.class);
//        startActivity(intent);
//        finish();
//    }
    private void gotoMasuk(){
        Intent intent =new Intent(Menu_Login.this, Daftar_Ponsel.class);
        startActivity(intent);
        finish();
    }






    @Override
    public void berhasilregister(String berhasil, String id, String imei, String email, String password, String role, String kata_pengingat) {
        loading.dismiss();
        sharedPrefManager.saveSPString(SharedPrefManager.id,id );
        sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
        sharedPrefManager.saveSPString(SharedPrefManager.password,password);
        sharedPrefManager.saveSPString(SharedPrefManager.role, role);
        sharedPrefManager.saveSPString(SharedPrefManager.kata_pengingat, kata_pengingat);
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
        gotoMasuk();
    }

    @Override
    public void berhasilregister(String Pesan) {
        loading.dismiss();
        Toast.makeText(this, Pesan, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void registergagal(String Pesan) {
        loading.dismiss();
        Toast.makeText(this, ""+Pesan, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void nointernet(String Pesan) {
        loading.dismiss();
        Toast.makeText(this, Pesan, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void saveuser(String id, String imei, String email, String password, String role, String kata_pengingat){
        loading.dismiss();
        sharedPrefManager.saveSPString(SharedPrefManager.id,id );
        sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
        sharedPrefManager.saveSPString(SharedPrefManager.password,password);
        sharedPrefManager.saveSPString(SharedPrefManager.role, role);
        sharedPrefManager.saveSPString(SharedPrefManager.kata_pengingat, kata_pengingat);
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
        gotoMasuk();


    }
}
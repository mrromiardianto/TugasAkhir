package com.example.screening_time.Anak.Features;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screening_time.Anak.Server.Controller.Controller;
import com.example.screening_time.Anak.Server.Controller.MyController;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.Anak.Utils.AppLockConstants;
import com.example.screening_time.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_RegisterPassword extends AppCompatActivity implements MyController {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int questionNumber = 0;
    @BindView(R.id.DaftarPassword)
    EditText Password;
    @BindView(R.id.Pengingat)
    EditText Pengingat;
    @BindView(R.id.NamaAnak)
    EditText Nama;
    @BindView(R.id.Btn_Daftar)
    Button Btn_Daftar;
    @BindView(R.id.Daftar_Masuk)
    Button Masuk;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__register_password);
        ButterKnife.bind(Menu_RegisterPassword.this);
        sharedPreferences = getSharedPreferences(AppLockConstants.MyPREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ProgressDialog loading = new ProgressDialog(Menu_RegisterPassword.this);
        Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoMasuk();
            }


        });
        Btn_Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validasi();
            }
        });
    }

    private void Validasi() {
        String password=Password.getText().toString();
        String nama=Pengingat.getText().toString();
        String namaanak=Nama.getText().toString();
        SharedPrefManager sharedPrefManager=new SharedPrefManager(Menu_RegisterPassword.this);
        String Imei=sharedPrefManager.getSP_IMEI();
        if (password.equals("")||nama.equals("")){
            Toast.makeText(this, "Password & Nama Pengingat Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else{
            final ProgressDialog loading = new ProgressDialog(Menu_RegisterPassword.this);
            loading.setMessage("Mohon Tunggu...");
            loading.setCancelable(false);
            loading.show();
            RequestDaftar(Imei,nama,password,namaanak);

        }
    }

    private void RequestDaftar(String imei, String nama, String password, String NamaAnak) {
        Controller controller =new Controller(Menu_RegisterPassword.this);
        controller.Daftar(imei,nama,password,NamaAnak);
    }

    private void GotoMasuk() {
        Intent intent =new Intent(Menu_RegisterPassword.this,Menu_Masuk.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void gagalmasuk(String Message) {
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ImeiTidakTerdaftar() {

    }

    @Override
    public void berhasilmasuk(String Message) {
        editor.putBoolean(AppLockConstants.IS_PASSWORD_SET, true);
        editor.commit();
        editor.putString(AppLockConstants.ANSWER, Pengingat.getText().toString());
        editor.commit();
        editor.putInt(AppLockConstants.QUESTION_NUMBER, questionNumber);
        editor.commit();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
        GotoMasuk();

    }



    @Override
    public void TidakAdaKoneksi(String error_message) {
        Toast.makeText(this, ""+error_message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void gagalupdate(String pesan) {

    }

    @Override
    public void berhasilupdate(String pesan) {

    }
}
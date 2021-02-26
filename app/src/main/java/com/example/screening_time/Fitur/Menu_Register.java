package com.example.screening_time.Fitur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.screening_time.Controller.User;
import com.example.screening_time.R;
import com.example.screening_time.View.MyUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_Register extends AppCompatActivity implements MyUser, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    @BindView(R.id.email)
    EditText Email;
    @BindView(R.id.password)
    EditText Password;
    @BindView(R.id.repassword)
    EditText Repassword;
    @BindView(R.id.kata_pengingat)
    EditText Kata_pengingat;
    @BindView(R.id.btn_register)
    Button Btn_register;
    @BindView(R.id.sudah)
    Button Sudah;
    String role;
    ProgressDialog loading;
    User user;
    @BindView(R.id.daftarrole)
    Spinner Daftar_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__register);
        ButterKnife.bind(Menu_Register.this);
        loading = new ProgressDialog(Menu_Register.this);
        user = new User(Menu_Register.this);
        Sudah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotomenulogin();
            }
        });

        Btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekinputan();
            }
        });

        Daftar_role.setOnItemSelectedListener(Menu_Register.this);
        List<String> categories = new ArrayList<String>();
        categories.add("Orang tua");
        categories.add("Anak");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Daftar_role.setAdapter(dataAdapter);
    }

    private void cekinputan() {
        if (Email.getText().toString().equals("")||Password.getText().toString().equals("")||Repassword.getText().toString().equals("")||role.equals("")||Kata_pengingat.getText().toString().equals("")){
            Toast.makeText(this, "Kolom Harus Terisi Semua", Toast.LENGTH_SHORT).show();
        } else{
            register();
        }

    }

    private void register() {
        loading.show();
        loading.setCancelable(false);
        loading.setMessage("Mohon Tunggu");
        String Imei = Settings.Secure.getString(Menu_Register.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        user.register(Imei, Email.getText().toString(), Password.getText().toString(), Repassword.getText().toString(), role, Kata_pengingat.getText().toString());
    }

    private void gotomenulogin() {
        Intent intent = new Intent(Menu_Register.this, Menu_Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void berhasilregister(String berhasil, String id, String imei, String email, String password, String role, String kata_pengingat) {

    }

    @Override
    public void berhasilregister(String Pesan){
        Toast.makeText(this, ""+Pesan, Toast.LENGTH_SHORT).show();
        loading.dismiss();
        gotomenulogin();


    }
    @Override
    public void registergagal(String Pesan){
        Toast.makeText(this, ""+Pesan, Toast.LENGTH_SHORT).show();
        loading.dismiss();


    }
    @Override
    public void nointernet(String Pesan){
        Toast.makeText(this, ""+Pesan, Toast.LENGTH_SHORT).show();
        loading.dismiss();

    }

    @Override
    public void saveuser(String id, String imei, String email, String password, String role, String kata_pengingat) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        role= parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
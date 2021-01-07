package com.example.screening_time.Fitur.OrangTua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.screening_time.R;

public class Menu_Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__dashboard2);


    }

    public void Daftar_Aplikasi(View view) {
        Intent intent = new Intent(this, Daftar_Aplikasi.class);
        startActivity(intent);
        finish();
    }

    public void Daftar_Ponsel(View view) {
        Intent intent = new Intent(this, Daftar_Ponsel.class);
        startActivity(intent);
        finish();
    }

    public void Hubungi(View view) {
        Intent intent = new Intent(this, Hubungi.class);
        startActivity(intent);
        finish();
    }

    public void Pengaturan(View view) {
        Intent intent = new Intent(this, Pengaturan.class);
        startActivity(intent);
        finish();
    }

    public void Statistik(View view) {
        Intent intent = new Intent(this, Statistik.class);
        startActivity(intent);
        finish();
    }

    public void Undang(View view) {
        Intent intent = new Intent(this, Undang.class);
        startActivity(intent);
        finish();

    }
}

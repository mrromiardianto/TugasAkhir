package com.example.screening_time.Fitur.OrangTua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.screening_time.Adapter.Adapter_Device;
import com.example.screening_time.Controller.Device;
import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.Model.Model_tugas;
import com.example.screening_time.R;
import com.example.screening_time.Session.SharedPrefManager;
import com.example.screening_time.View.MyDevice;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Daftar_Ponsel extends AppCompatActivity implements MyDevice {
    ProgressDialog loading;
    @BindView(R.id.listApkconnect)
    RecyclerView recyclerView;
    Device device;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar__ponsel);
        loading=new ProgressDialog(this);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        device=new Device(this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(llm);
        loading.setMessage("Loading....");
        loading.setCancelable(true);
        loading.show();
        device.GetDevice(sharedPrefManager.getSPEmail());
    }


    public void Daftar_Ponsel(View view){
        Intent intent = new Intent(this, Sinkronisasi_Ponsel.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void truedata(List<Model_Device> devices){
        loading.dismiss();
        Adapter_Device adapter=new Adapter_Device(this,devices);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void ImeiTerdaftar(String Message) {
        loading.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();


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
    public void suksesgetdata(List<Model_tugas> tugases) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        Intent menuSebelumnya = new Intent(this, Menu_Dashboard.class);
        startActivity(menuSebelumnya);
        finish();
    }
}
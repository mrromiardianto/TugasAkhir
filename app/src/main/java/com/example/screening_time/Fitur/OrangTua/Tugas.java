package com.example.screening_time.Fitur.OrangTua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.screening_time.Adapter.Adapter_Tugas;
import com.example.screening_time.Controller.Device;
import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.Model.Model_laporantugas;
import com.example.screening_time.Model.Model_tugas;
import com.example.screening_time.R;
import com.example.screening_time.Session.SharedPrefManager;
import com.example.screening_time.View.MyDevice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Tugas extends AppCompatActivity implements MyDevice {

    @BindView(R.id.daftartugas)
    EditText Daftar_tugas;
    @BindView(R.id.btn_tambahtugas)
    Button Tambahtugas;

    Device device;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    String imei, email;
    @BindView(R.id.listtugas)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas);
        ButterKnife.bind(this);
        device=new Device(this);
        progressDialog=new ProgressDialog(this);
        sharedPrefManager=new SharedPrefManager(this);
        email=sharedPrefManager.getSPEmail();
        imei= getIntent().getStringExtra("imei");
        Tambahtugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading....");
                progressDialog.setCancelable(true);
                progressDialog.show();
                if (Daftar_tugas.equals("")){
                    Toast.makeText(Tugas.this, "Kolom Tidak Boleh Kosng", Toast.LENGTH_SHORT).show();
                }else{
                    String nama_tugas = Daftar_tugas.getText().toString();
                    device.AddTugas(imei,email, nama_tugas);
                }
            }
        });
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(llm);
        getTugas();
    }

    @Override
    public void listlaporan(List<Model_laporantugas> laporan) {

    }

    @Override
    public void truedata(List<Model_Device> devices) {

    }

    @Override
    public void ImeiTerdaftar(String Message) {
        progressDialog.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
        getTugas();

    }

    private void getTugas() {
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(true);
        progressDialog.show();
        imei= getIntent().getStringExtra("imei");
        device.getTugas(imei);

    }

    @Override
    public void ImeiTidakTerdaftar(String Message) {
        progressDialog.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void NoInternet(String Message) {
        progressDialog.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();

    }



    @Override
    public void suksesgetdata(List<Model_tugas> tugases){
        progressDialog.dismiss();
        Adapter_Tugas adapter=new Adapter_Tugas(this,tugases);
        recyclerView.setAdapter(adapter);
    }
}
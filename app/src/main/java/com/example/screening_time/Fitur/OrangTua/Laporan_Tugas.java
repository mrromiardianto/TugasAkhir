package com.example.screening_time.Fitur.OrangTua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.screening_time.Adapter.Adapter_LaporanTugas;
import com.example.screening_time.Adapter.Adapter_Tugas;
import com.example.screening_time.Controller.Device;
import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.Model.Model_laporantugas;
import com.example.screening_time.Model.Model_tugas;
import com.example.screening_time.R;
import com.example.screening_time.View.MyDevice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Laporan_Tugas extends AppCompatActivity implements MyDevice {

    String imei;

    @BindView(R.id.listlaporantugas)
    RecyclerView recyclerView;

    ProgressDialog progressDialog;
    Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan__tugas);
        ButterKnife.bind(this);
        device = new Device(this);
        progressDialog=new ProgressDialog(this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(llm);
        getTugas();

    }

    private void getTugas() {
        progressDialog.setMessage("Loading....");
        progressDialog.setCancelable(true);
        progressDialog.show();
        imei= getIntent().getStringExtra("imei");
        device.getlaporantugas(imei);


    }
    @Override
    public void listlaporan(List<Model_laporantugas> laporan){
        progressDialog.dismiss();
        Adapter_LaporanTugas adapter=new Adapter_LaporanTugas(this,laporan);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void truedata(List<Model_Device> devices) {

    }

    @Override
    public void ImeiTerdaftar(String Message) {
        progressDialog.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();

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
    public void suksesgetdata(List<Model_tugas> tugases) {
        progressDialog.dismiss();
//        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }


}
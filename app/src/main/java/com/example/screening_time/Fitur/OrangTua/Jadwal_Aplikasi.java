package com.example.screening_time.Fitur.OrangTua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.screening_time.Adapter.Adapter_Jadwal;
import com.example.screening_time.Model.Item_Jadwal;
import com.example.screening_time.R;
import com.example.screening_time.Response.Response_Jadwal;
import com.example.screening_time.Server.ApiServices;
import com.example.screening_time.Server.InitRetrofit;
import com.example.screening_time.Session.SharedPrefManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Jadwal_Aplikasi extends AppCompatActivity {

    @BindView(R.id.list_jadwal)
    RecyclerView recyclerView;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal__aplikasi);

        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        loading=new ProgressDialog(Jadwal_Aplikasi.this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(llm);
        loading.setMessage("Mohon Tunggu Sebentar.....");
        loading.setCancelable(false);
        loading.show();
//        String Imei=getIntent().getExtras("imei");
        String Imei= getIntent().getStringExtra("imei");
        tampil_jadwal(Imei);

    }



    private void tampil_jadwal(String imei) {

        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Jadwal> menuCall = api.getJadwal(imei);
        menuCall.enqueue(new Callback<Response_Jadwal>() {
            @Override
            public void onResponse(Call<Response_Jadwal> call, Response<Response_Jadwal> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_Jadwal> jadwal= response.body().getJadwal();
                    boolean status = response.body().isStatus();
                    if (status){
                        loading.dismiss();
                        Adapter_Jadwal adapter = new Adapter_Jadwal(Jadwal_Aplikasi.this, jadwal);
                        recyclerView.setAdapter(adapter);
                    } else {
                        try {
                            loading.dismiss();
                            Toast.makeText(Jadwal_Aplikasi.this, "Device Belum memiliki Jadwal", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Jadwal> call, Throwable t) {
                try {
                    loading.dismiss();
                    Toast.makeText(Jadwal_Aplikasi.this, "Server Tidak Merespon", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

}
package com.example.screening_time.Anak.Features;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screening_time.Anak.Fragment.Menu_Utama;
import com.example.screening_time.Anak.Server.Adapter.Adapter_Jadwal;
import com.example.screening_time.Anak.Server.ApiServices;
import com.example.screening_time.Anak.Server.InitRetrofit;
import com.example.screening_time.Anak.Server.Item.Item_Jadwal;
import com.example.screening_time.Anak.Server.Response.Response_Jadwal;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.Fitur.Menu_Login;
import com.example.screening_time.Fitur.OrangTua.Daftar_Ponsel;
import com.example.screening_time.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Menu_ListJadwal extends AppCompatActivity {
    @BindView(R.id.list_jadwal)
    RecyclerView recyclerView;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;
    com.example.screening_time.Session.SharedPrefManager sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__list_jadwal);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        sh=new com.example.screening_time.Session.SharedPrefManager(this);
        loading=new ProgressDialog(Menu_ListJadwal.this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(llm);
        loading.setMessage("Mohon Tunggu Sebentar.....");
        loading.setCancelable(false);
        loading.show();
        tampil_jadwal();
    }

    private void tampil_jadwal() {
//        Toast.makeText(this, sharedPrefManager.getSP_IMEI(), Toast.LENGTH_SHORT).show();
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Jadwal> menuCall = api.tampil_jadwal(sharedPrefManager.getSP_IMEI());
        menuCall.enqueue(new Callback<Response_Jadwal>() {
            @Override
            public void onResponse(Call<Response_Jadwal> call, Response<Response_Jadwal> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_Jadwal> jadwal= response.body().getJadwal();
                    boolean status = response.body().isStatus();
                    if (status){
                        loading.dismiss();
                        Adapter_Jadwal adapter = new Adapter_Jadwal(Menu_ListJadwal.this, jadwal);
                        recyclerView.setAdapter(adapter);
                    } else {
                        try {
                            loading.dismiss();
                            Toast.makeText(Menu_ListJadwal.this, "Tidak Ada Jadwalsaat ini", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Menu_ListJadwal.this, "Server Tidak Merespon", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Intent intent=new Intent(Menu_ListJadwal.this, Menu_Utama.class);
//        startActivity(intent);
//        finish();
        String Role=sh.getRole();
//        Toast.makeText(this, Role, Toast.LENGTH_SHORT).show();
        if (Role.equals("Anak")){
            startActivity(new Intent(Menu_ListJadwal.this,Menu_Utama.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }else {
            startActivity(new Intent(Menu_ListJadwal.this, Daftar_Ponsel.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }
    }

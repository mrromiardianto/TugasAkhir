package com.example.screening_time.Anak.Features;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screening_time.Anak.Server.Adapter.Adapter_Tugas;
import com.example.screening_time.Anak.Server.ApiServices;
import com.example.screening_time.Anak.Server.InitRetrofit;
import com.example.screening_time.Anak.Server.Item.Item_Tugas;
import com.example.screening_time.Anak.Server.Response.Response_Tugas;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_Tugas extends AppCompatActivity {
    @BindView(R.id.list_tugas)
    RecyclerView recyclerView;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__tugas);
        ButterKnife.bind(this);
        loading=new ProgressDialog(this);
        sharedPrefManager=new SharedPrefManager(this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(llm);
        loading.setMessage("Mohon Tunggu Sebentar.....");
        loading.setCancelable(false);
        loading.show();
        Tampil_Tugas();
    }

    private void Tampil_Tugas() {
        String imei=sharedPrefManager.getSP_IMEI();
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Tugas> menuCall = api.tampil_tugas(imei);
        menuCall.enqueue(new Callback<Response_Tugas>() {
            @Override
            public void onResponse(Call<Response_Tugas> call, Response<Response_Tugas> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_Tugas> tugas= response.body().getTugas();
                    boolean status = response.body().isStatus();
                    if (status){
                        loading.dismiss();
                        Adapter_Tugas adapter = new Adapter_Tugas(Menu_Tugas.this, tugas);
                        recyclerView.setAdapter(adapter);
                    } else {
                        try {
                            loading.dismiss();
                            Toast.makeText(Menu_Tugas.this, "Belum ada Tugas", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Tugas> call, Throwable t) {
                try {
                    loading.dismiss();
                    Toast.makeText(Menu_Tugas.this, "Server Tidak Merespon", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
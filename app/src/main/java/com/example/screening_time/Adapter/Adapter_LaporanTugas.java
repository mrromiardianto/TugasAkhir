package com.example.screening_time.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.screening_time.Fitur.Anak.Menu_DashboarAnak;
import com.example.screening_time.Fitur.OrangTua.Jadwal_Aplikasi;
import com.example.screening_time.Fitur.OrangTua.Laporan_Tugas;
import com.example.screening_time.Fitur.OrangTua.Statistik;
import com.example.screening_time.Fitur.OrangTua.Tugas;
import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.Model.Model_laporantugas;
import com.example.screening_time.R;
import com.example.screening_time.Server.InitRetrofit;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_LaporanTugas extends RecyclerView.Adapter<Adapter_LaporanTugas.MyViewHolder> {
    Context context;
    List<Model_laporantugas> menu;
    public Adapter_LaporanTugas(Context context, List<Model_laporantugas> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public Adapter_LaporanTugas.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_laporantugas, parent, false);
        Adapter_LaporanTugas.MyViewHolder holder = new Adapter_LaporanTugas.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
//        holder.imei.setText(menu.get(position).getNama());
//        holder.harga.setText(menu.get(position).getHarga());
//        final String urlGambar = InitRetrofit.BASE_URL+"../Images/" + menu.get(position).getFoto();
//        Picasso.with(context).load(urlGambar).into(holder.gambarmenu);
        holder.Packagename.setText(menu.get(position).getJsonMemberPackage());
        holder.Nama.setText(menu.get(position).getNama());
        String UrlGambar= InitRetrofit.IP+"image/"+menu.get(position).getFile();
        Picasso.with(context).load(UrlGambar).into(holder.Gambarlaporan);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Intent varIntent = new Intent(context, Menu_DashboarAnak.class);
//                varIntent.putExtra("SN", menu.get(position).getSn());
//                SharedPrefManager sharedPrefManager=new SharedPrefManager(context);
//                sharedPrefManager.saveSPString(SharedPrefManager.SP_Mac, menu.get(position).getSn());
//                sharedPrefManager.saveSPString(SharedPrefManager.SP_Prov, menu.get(position).getProvinsi());
//                sharedPrefManager.saveSPString(SharedPrefManager.SP_Kab, menu.get(position).getKabupaten());
//                sharedPrefManager.saveSPString(SharedPrefManager.SP_device_channel, menu.get(position).getDevice_channel());
//                context.startActivity(varIntent);
            }
        });

//        holder.Jadwal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent varIntent = new Intent(context, Jadwal_Aplikasi.class);
//                varIntent.putExtra("imei", menu.get(position).getImei());
//                context.startActivity(varIntent);
//
//            }
//        });

//        holder.Statistik.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent varIntent = new Intent(context, Statistik.class);
//                varIntent.putExtra("imei", menu.get(position).getImei());
//                context.startActivity(varIntent);
//
//            }
//        });

//        holder.Tugas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent varIntent = new Intent(context, Tugas.class);
//                varIntent.putExtra("imei", menu.get(position).getImei());
//                context.startActivity(varIntent);
//
//            }
//        });

//        holder.LaporanTugas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent varIntent = new Intent(context, Laporan_Tugas.class);
//                varIntent.putExtra("imei", menu.get(position).getImei());
//                context.startActivity(varIntent);
//
//            }
//        });

        holder.Terima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imei=menu.get(position).getImei();
                String packagenama=menu.get(position).getJsonMemberPackage();
                TerimaTugas(imei,packagenama);
            }
        });

    }

    private void TerimaTugas(String imei, String packagenama) {
        ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("loading");
        progressDialog.setCancelable(true);
        progressDialog.show();
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().Terimatugas(imei, packagenama);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Berhasil=jsonRESULTS.getString("message");
                            Toast.makeText(context, Berhasil, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
//                            myDevice.ImeiTerdaftar(Berhasil);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Gagal=jsonRESULTS.getString("message");
                                progressDialog.dismiss();
//                                myDevice.ImeiTidakTerdaftar(Gagal);
//                                Toast.makeText(MenuRegist.this, ""+Gagal_LOGIN, Toast.LENGTH_SHORT).show();
//                                Log.v("pesan",Gagal_LOGIN);
//                                userLogin.login_gagal(Gagal_LOGIN);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Ada Masalah Internet";
                        progressDialog.dismiss();
//                        myDevice.NoInternet(error_message);
                        Toast.makeText(context, ""+error_message, Toast.LENGTH_SHORT).show();
//                        loading.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Server Tidak Merespon";
                    progressDialog.dismiss();
//                    myDevice.NoInternet(error_message);
//                    userLogin.login_gagal(error_message);
                    Toast.makeText(context, ""+error_message, Toast.LENGTH_SHORT).show();
//                    loading.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });

    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.packagename)
        TextView Packagename;
        @BindView(R.id.gambarlaporan)
        ImageView Gambarlaporan;
        @BindView(R.id.terima)
        Button Terima;
        @BindView(R.id.nama_tugas)
        TextView Nama;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}

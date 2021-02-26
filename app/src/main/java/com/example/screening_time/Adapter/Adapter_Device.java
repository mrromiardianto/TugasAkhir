package com.example.screening_time.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.screening_time.Anak.Features.Menu_Statistic;
import com.example.screening_time.Fitur.Anak.Menu_DashboarAnak;
import com.example.screening_time.Fitur.OrangTua.Jadwal_Aplikasi;
import com.example.screening_time.Fitur.OrangTua.Laporan_Tugas;
import com.example.screening_time.Fitur.OrangTua.Statistik;
import com.example.screening_time.Fitur.OrangTua.Tugas;
import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_Device extends RecyclerView.Adapter<Adapter_Device.MyViewHolder> {
    Context context;
    List<Model_Device> menu;
    public Adapter_Device(Context context, List<Model_Device> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_device, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.imei.setText(menu.get(position).getNama());
//        holder.harga.setText(menu.get(position).getHarga());
//        final String urlGambar = InitRetrofit.BASE_URL+"../Images/" + menu.get(position).getFoto();
//        Picasso.with(context).load(urlGambar).into(holder.gambarmenu);
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

        holder.Jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent varIntent = new Intent(context, Jadwal_Aplikasi.class);
                varIntent.putExtra("imei", menu.get(position).getImei());
               context.startActivity(varIntent);

            }
        });

        holder.Statistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent varIntent = new Intent(context, Menu_Statistic.class);
                varIntent.putExtra("imei", menu.get(position).getImei());
                context.startActivity(varIntent);

            }
        });

        holder.Tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent varIntent = new Intent(context, Tugas.class);
                varIntent.putExtra("imei", menu.get(position).getImei());
                context.startActivity(varIntent);

            }
        });

        holder.LaporanTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent varIntent = new Intent(context, Laporan_Tugas.class);
                varIntent.putExtra("imei", menu.get(position).getImei());
                context.startActivity(varIntent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imei)
        TextView imei;
        @BindView(R.id.jadwal)
        LinearLayout Jadwal;
        @BindView(R.id.statistik)
        LinearLayout Statistik;
        @BindView(R.id.tugas)
        LinearLayout Tugas;
        @BindView(R.id.laporantugas)
        LinearLayout LaporanTugas;




        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
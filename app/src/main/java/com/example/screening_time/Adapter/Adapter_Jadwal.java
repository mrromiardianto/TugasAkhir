package com.example.screening_time.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screening_time.Controller.Device;
import com.example.screening_time.Model.Item_Jadwal;
import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.Model.Model_tugas;
import com.example.screening_time.R;
import com.example.screening_time.View.MyDevice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_Jadwal extends RecyclerView.Adapter<Adapter_Jadwal.MyViewHolder> implements MyDevice {
    Context context;
    List<Item_Jadwal> menu;
    ProgressDialog loading;
    Device controller;


    public Adapter_Jadwal(Context context, List<Item_Jadwal> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    @Override
    public Adapter_Jadwal.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_jadwal, parent, false);
        Adapter_Jadwal.MyViewHolder holder = new Adapter_Jadwal.MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(Adapter_Jadwal.MyViewHolder holder, final int position) {
        holder.nama.setText(menu.get(position).getNama());
        holder.Package.setText(menu.get(position).getJammulai()+"--"+menu.get(position).getJamakhir());
//        holder.jammulai.setText(menu.get(position).getJammulai());
//        holder.jamakhir.setText(menu.get(position).getJamakhir());
        String pkg =menu.get(position).getJsonMemberPackage();
        Drawable icon = null;
        try {
            icon = context.getPackageManager().getApplicationIcon(pkg);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        holder.Image.setImageDrawable(icon);

        holder.upadate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(context, Menu_SettingJadwal.class);
//                intent.putExtra("Package",menu.get(position).getJsonMemberPackage());
//                intent.putExtra("Nama",menu.get(position).getNama());
//                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=menu.get(position).getId();
                PopupDelete(id);

            }
        });
    }

    private void PopupDelete(String id) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setMessage("Apakah Anda yakin Akan Menghapus Aplikasi dari daftar Jadwal?");
        dialog.setTitle("Pemberitahuan");
        dialog.setPositiveButton("IYA", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which) {
                   delete(id);
                    }
                });
        dialog.setNegativeButton("TIDAK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    private void delete(String id) {
        loading=new ProgressDialog(context);
        loading.show();
        loading.setMessage("Mohon Tunggu.....");
        loading.setCancelable(false);
        controller=new Device(this);
        controller.deletejadwal(id);

    }


    @Override
    public int getItemCount() {
        return menu.size();
    }

    @Override
    public void truedata(List<Model_Device> devices) {

    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void ImeiTidakTerdaftar(String Message) {

    }

    @Override
    public void NoInternet(String Message) {

    }

    @Override
    public void suksesgetdata(List<Model_tugas> tugases) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.Nama_aplikasi)
        TextView nama;
        @BindView(R.id.Package_aplikasi)
        TextView Package;
//        @BindView(R.id.Jammulai_aplikasi)
//        TextView jammulai;
//        @BindView(R.id.Jamakhir_aplikasi)
//        TextView jamakhir;
        @BindView(R.id.Delete_Jadwal)
        Button delete;
        @BindView(R.id.Upadate_Jadwal)
        Button upadate;
        @BindView(R.id.gambar_aplikasi)
        ImageView Image;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
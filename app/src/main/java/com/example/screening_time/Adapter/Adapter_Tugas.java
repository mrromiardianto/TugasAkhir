package com.example.screening_time.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.screening_time.Model.Model_tugas;
import com.example.screening_time.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_Tugas extends RecyclerView.Adapter<Adapter_Tugas.MyViewHolder> {
        Context context;
        List<Model_tugas> menu;
        ProgressDialog loading;
//        Device controller;


public Adapter_Tugas(Context context, List<Model_tugas> data_menu) {
        this.context = context;
        this.menu= data_menu;
        }

@Override
public Adapter_Tugas.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_tugas, parent, false);
    Adapter_Tugas.MyViewHolder holder = new Adapter_Tugas.MyViewHolder(view);
        return holder;
        }
@Override
public void onBindViewHolder(Adapter_Tugas.MyViewHolder holder, final int position) {
        holder.nama.setText(menu.get(position).getNamaTugas());
        holder.imei.setText(menu.get(position).getImei());

}
@Override
public int getItemCount() {
        return menu.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.namatugas)
    TextView nama;
    @BindView(R.id.imeitugas)
    TextView imei;
    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
}




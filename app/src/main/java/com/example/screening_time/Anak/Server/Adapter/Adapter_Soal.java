package com.example.screening_time.Anak.Server.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.screening_time.Anak.Features.Menu_ListSoal;
import com.example.screening_time.Anak.Server.Item.Item_Soal;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter_Soal extends RecyclerView.Adapter<Adapter_Soal.MyViewHolder> {
    Context context;
    List<Item_Soal> menu;
    SharedPrefManager sharedPrefManager;


    public Adapter_Soal (Context context, List<Item_Soal> data_menu) {
        this.context = context;
        this.menu= data_menu;
    }

    public Adapter_Soal(Menu_ListSoal context, List<Item_Soal> soal) {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.soal_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        sharedPrefManager=new SharedPrefManager(context);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.nama.setText(menu.get(position).getSoal());
        holder.PilihanA.setText(menu.get(position).getA());
        holder.PilihanB.setText(menu.get(position).getB());
        holder.PilihanC.setText(menu.get(position).getC());
        holder.PilihanD.setText(menu.get(position).getD());
        try {
            String JawabanBenar1=menu.get(0).getJawaban();
            String JawabanBenar2=menu.get(1).getJawaban();
            String JawabanBenar3=menu.get(2).getJawaban();
            String JawabanBenar4=menu.get(3).getJawaban();
            String JawabanBenar5=menu.get(4).getJawaban();
            String JawabanBenar6=menu.get(5).getJawaban();
            String JawabanBenar7=menu.get(6).getJawaban();
            String JawabanBenar8=menu.get(7).getJawaban();
            String JawabanBenar9=menu.get(8).getJawaban();
            String JawabanBenar10=menu.get(9).getJawaban();
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar1, JawabanBenar1);
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar2, JawabanBenar2);
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar3, JawabanBenar3);
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar4, JawabanBenar4);
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar5, JawabanBenar5);
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar6, JawabanBenar6);
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar7, JawabanBenar7);
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar8, JawabanBenar8);
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar9, JawabanBenar9);
            sharedPrefManager.saveSPString(SharedPrefManager.JawabanBenar10, JawabanBenar10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.PilihanA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;
                String Jawaban = holder.PilihanA.getText().toString();
                if (position == 0) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban1, Jawaban);
                } else if (position==1) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban2, Jawaban);
                } else if (position==2) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban3, Jawaban);
                }else if (position==3) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban4, Jawaban);
                }else if (position==4) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban5, Jawaban);
                }else if (position==5) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban6, Jawaban);
                }else if (position==6) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban7, Jawaban);
                }else if (position==7) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban8, Jawaban);
                }else if (position==8) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban9, Jawaban);
                }else if (position==9) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban10, Jawaban);
                }else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.PilihanB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;
                String Jawaban = holder.PilihanB.getText().toString();
                if (position == 0) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban1, Jawaban);
                } else if (position==1) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban2, Jawaban);
                } else if (position==2) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban3, Jawaban);
                }else if (position==3) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban4, Jawaban);
                }else if (position==4) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban5, Jawaban);
                }else if (position==5) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban6, Jawaban);
                }else if (position==6) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban7, Jawaban);
                }else if (position==7) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban8, Jawaban);
                }else if (position==8) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban9, Jawaban);
                }else if (position==9) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban10, Jawaban);
                }else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.PilihanC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;
                String Jawaban = holder.PilihanC.getText().toString();
                if (position == 0) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban1, Jawaban);
                } else if (position==1) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban2, Jawaban);
                } else if (position==2) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban3, Jawaban);
                }else if (position==3) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban4, Jawaban);
                }else if (position==4) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban5, Jawaban);
                }else if (position==5) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban6, Jawaban);
                }else if (position==6) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban7, Jawaban);
                }else if (position==7) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban8, Jawaban);
                }else if (position==8) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban9, Jawaban);
                }else if (position==9) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban10, Jawaban);
                }else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.PilihanD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;
                String Jawaban = holder.PilihanD.getText().toString();
                if (position == 0) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban1, Jawaban);
                } else if (position==1) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban2, Jawaban);
                } else if (position==2) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban3, Jawaban);
                }else if (position==3) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban4, Jawaban);
                }else if (position==4) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban5, Jawaban);
                }else if (position==5) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban6, Jawaban);
                }else if (position==6) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban7, Jawaban);
                }else if (position==7) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban8, Jawaban);
                }else if (position==8) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban9, Jawaban);
                }else if (position==9) {
                    sharedPrefManager.saveSPString(SharedPrefManager.Jawaban10, Jawaban);
                }else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.kuis)
        TextView nama;
        @BindView(R.id.pilihanA)
        TextView PilihanA;
        @BindView(R.id.pilihanB)
        TextView PilihanB;
        @BindView(R.id.pilihanC)
        TextView PilihanC;
        @BindView(R.id.pilihanD)
        TextView PilihanD;
        @BindView(R.id.pilihan)
        RadioGroup Pilihan;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
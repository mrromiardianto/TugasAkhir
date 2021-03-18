package com.example.screening_time.Anak.Features;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.screening_time.Anak.Fragment.Menu_Utama;
import com.example.screening_time.Anak.Server.Controller.Controller;
import com.example.screening_time.Anak.Server.Controller.MyController;
import com.example.screening_time.Anak.Server.Koneksi_RMQ;
import com.example.screening_time.Anak.Server.MyRmq;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.Anak.Session.SharedPreference;
import com.example.screening_time.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Menu_SettingJadwal extends AppCompatActivity implements MyController, MyRmq {
    SharedPreference sharedPreference;
    Koneksi_RMQ koneksi_rmq;
    SharedPrefManager sharedPrefManager;
    private SimpleDateFormat dateFormatter;
    private TimePickerDialog timePickerDialog;
    @BindView(R.id.Jam_mulai)
    EditText jammulai;
    @BindView(R.id.Jam_akhir)
    EditText jamakhir;
    @BindView(R.id.simpanjadwal)
    Button Simpan;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__setting_jadwal);
        ButterKnife.bind(this);
        sharedPreference=new SharedPreference();
        koneksi_rmq=new Koneksi_RMQ(Menu_SettingJadwal.this);
        sharedPrefManager=new SharedPrefManager(Menu_SettingJadwal.this);
//        Context context = getApplicationContext();
//        ArrayList<String> pakageName = sharedPreference.getLocked(context);
//        Toast.makeText(this, ""+pakageName, Toast.LENGTH_SHORT).show();
        jamakhir.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                showTimestop();
            }
        });
        jammulai.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                showTimestart();
            }
        });
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validasi();
            }
        });
    }

    private void Validasi() {
        if (jammulai.getText().toString().equals("")){
            Toast.makeText(this, "Harap Masukan Jam Mulai", Toast.LENGTH_SHORT).show();
        }else if (jamakhir.getText().toString().equals("")){
            Toast.makeText(this, "Harap Masukan Jam Akhir", Toast.LENGTH_SHORT).show();
        }else {
            Simpan_Jadwal();
            kirim_ToRMQ();
        }
    }

    private void Simpan_Jadwal() {
        loading = ProgressDialog.show(Menu_SettingJadwal.this,"Loading.....",null,true,true);
        loading.setMessage("Mohon Tunggu...");
        loading.setCancelable(false);
        loading.show();
        String Imei=sharedPrefManager.getSP_IMEI();
        String JamMulai=jammulai.getText().toString();
        String JamAkhir=jamakhir.getText().toString();
        String Package=getIntent().getExtras().getString("Package");
        String Nama=getIntent().getExtras().getString("Nama");
        Toast.makeText(this, ""+Package, Toast.LENGTH_SHORT).show();
        Controller controller=new Controller(Menu_SettingJadwal.this);
        controller.Edit_Jadwal(Imei,JamMulai,JamAkhir,Nama,Package);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showTimestart() {
        Calendar calendar = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
        }else  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            calendar = Calendar.getInstance();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            timePickerDialog = new TimePickerDialog(Menu_SettingJadwal.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    jammulai.setText(hourOfDay+"."+minute);
                }
            },
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                    DateFormat.is24HourFormat(Menu_SettingJadwal.this));
        }
        timePickerDialog.show();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showTimestop() {
        Calendar calendar = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            calendar = Calendar.getInstance();
        }else  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            calendar = Calendar.getInstance();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            timePickerDialog = new TimePickerDialog(Menu_SettingJadwal.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    jamakhir.setText(hourOfDay+"."+minute);
                }
            },
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                    DateFormat.is24HourFormat(Menu_SettingJadwal.this));
        }
        timePickerDialog.show();
    }

    @Override
    public void onBackPressed(){
        Menu_utama();
    }

    private void Menu_utama() {
        Intent intent=new Intent(Menu_SettingJadwal.this, Menu_Utama.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void gagalmasuk(String Message) {
        loading.dismiss();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ImeiTidakTerdaftar() {

    }

    @Override
    public void berhasilmasuk(String Message) {
//        loading.dismiss();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
        String Package=getIntent().getExtras().getString("Package");
        Log.d("nama apk",Package);
        sharedPreference.addLocked(Menu_SettingJadwal.this, Package);
        kirim_ToRMQ();
    }
    private void kirim_ToRMQ() {
        String Imei=sharedPrefManager.getSP_IMEI();
        String JamMulai=jammulai.getText().toString();
        String JamAkhir=jamakhir.getText().toString();
        String Package=getIntent().getExtras().getString("Package");
        String Nama=getIntent().getExtras().getString("Nama");
        String channel="jadwal";
        koneksi_rmq.setupConnectionFactory();
        JSONObject obj=new JSONObject();
        try {
            obj.put("imei", Imei);
            obj.put("jammulai",JamMulai);
            obj.put("jamakhir", JamAkhir);
            obj.put("package", Package);
            obj.put("nama", Nama);
            String message=obj.toString();
            koneksi_rmq.publish(message,channel);
            Log.d("datapublish",message);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void GotoListApk() {
        Intent intent=new Intent(Menu_SettingJadwal.this,Menu_Utama.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void TidakAdaKoneksi(String error_message) {
        loading.dismiss();
        Toast.makeText(this, ""+error_message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void gagalupdate(String pesan) {

    }

    @Override
    public void berhasilupdate(String pesan) {

    }

    @Override
    public void Berhasil(String message) {
        loading.dismiss();
        GotoListApk();

    }

    @Override
    public void Gagal() {

    }
}
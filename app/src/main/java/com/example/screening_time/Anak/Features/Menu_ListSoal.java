package com.example.screening_time.Anak.Features;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.screening_time.Anak.Server.Adapter.Adapter_Soal;
import com.example.screening_time.Anak.Server.ApiServices;
import com.example.screening_time.Anak.Server.Controller.Controller;
import com.example.screening_time.Anak.Server.Controller.MyController;
import com.example.screening_time.Anak.Server.InitRetrofit;
import com.example.screening_time.Anak.Server.Item.Item_Soal;
import com.example.screening_time.Anak.Server.Response.Response_Soal;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_ListSoal extends AppCompatActivity implements MyController {
    @BindView(R.id.list_soal)
    RecyclerView recyclerView;
    ProgressDialog loading;
    @BindView(R.id.Proses_Quiz)
    Button Proses;
    SharedPrefManager sharedPrefManager;
    @BindView(R.id.FormHasil)
    LinearLayout Hasil;
    @BindView(R.id.Form_Proses)
    LinearLayout Form_Proses;
    @BindView(R.id.Hasil_Quiz)
    TextView Total_Nilai;
    @BindView(R.id.Keluar_Quiz)
    Button Exit;
    @BindView(R.id.Ulangi_Quiz)
    Button Requiz;
    @BindView(R.id.Title_Quiz)
    TextView Judul_quiz;
    Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__list_soal);
        ButterKnife.bind(this);
        controller=new Controller(Menu_ListSoal.this);
        Hasil.setVisibility(View.GONE);
        sharedPrefManager=new SharedPrefManager(this);
        loading=new ProgressDialog(Menu_ListSoal.this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(llm);
        loading.setMessage("Mohon Tunggu Sebentar.....");
        loading.setCancelable(false);
        loading.show();
        Total_Nilai.setText("0");
//        sharedPrefManager.saveSPString(SharedPrefManager.Total,"0");
//        Toast.makeText(this, ""+sharedPrefManager.Total, Toast.LENGTH_SHORT).show();
        Reset();
        tampil_soal();
        Proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jawaban1 =sharedPrefManager.getJawaban1();
                String jawabanbenar1=sharedPrefManager.getJawabanBenar1();
                String jawaban2 =sharedPrefManager.getJawaban2();
                String jawabanbenar2=sharedPrefManager.getJawabanBenar2();
                String jawaban3 =sharedPrefManager.getJawaban3();
                String jawabanBenar3=sharedPrefManager.getJawabanBenar3();
                String jawaban4 =sharedPrefManager.getJawaban4();
                String jawabanBenar4=sharedPrefManager.getJawabanBenar4();
                String jawaban5 =sharedPrefManager.getJawaban5();
                String jawabanBenar5=sharedPrefManager.getJawabanBenar5();
                String jawaban6 =sharedPrefManager.getJawaban6();
                String jawabanBenar6=sharedPrefManager.getJawabanBenar6();
                String jawaban7 =sharedPrefManager.getJawaban7();
                String jawabanBenar7=sharedPrefManager.getJawabanBenar7();
                String jawaban8 =sharedPrefManager.getJawaban8();
                String jawabanBenar8=sharedPrefManager.getJawabanBenar8();
                String jawaban9 =sharedPrefManager.getJawaban9();
                String jawabanBenar9=sharedPrefManager.getJawabanBenar9();
                String jawaban10=sharedPrefManager.getJawaban10();
                String jawabanBenar10=sharedPrefManager.getJawabanBenar10();
                Log.e("jawaban1",jawaban1);
                Log.e("jawabanbenar1",jawabanbenar1);
                Log.e("jawaban2",jawaban2);
                Log.e("jawabanbenar2",jawabanbenar2);
                Log.e("jawaban3",jawaban3);
                Log.e("jawabanbenar3",jawabanBenar3);
                Log.e("jawaban4",jawaban4);
                Log.e("jawabanbenar4",jawabanBenar4);
                Log.e("jawaban5",jawaban5);
                Log.e("jawabanbenar5",jawabanBenar5);
                Log.e("jawaban6",jawaban6);
                Log.e("jawabanbenar6",jawabanBenar6);
                Log.e("jawaban7",jawaban7);
                Log.e("jawabanbenar7",jawabanBenar7);
                Log.e("jawaban8",jawaban8);
                Log.e("jawabanbenar8",jawabanBenar8);
                Log.e("jawaban9",jawaban9);
                Log.e("jawabanbenar9",jawabanBenar9);
                Log.e("jawaban10",jawaban10);
                Log.e("jawabanbenar10",jawabanBenar10);
                ChexQuiz();
            }
        });
        Requiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Reset();
                loading.setMessage("Mohon Tunggu Sebentar.....");
                loading.setCancelable(false);
                loading.show();
                tampil_soal();
                recyclerView.setVisibility(View.VISIBLE);
                Requiz.setVisibility(View.GONE);
                Exit.setVisibility(View.GONE);
                Form_Proses.setVisibility(View.VISIBLE);
                Hasil.setVisibility(View.GONE);
            }
        });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exit_Menu();
            }
        });
    }

    private void Exit_Menu() {
        Reset();
        Keluar();
    }

    private void Reset() {
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban1, "ABC");
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban2, "ABC");
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban3, "ABC");
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban4, "ABC");
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban5, "ABC");
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban6, "ABC");
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban7, "ABC");
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban8, "ABC");
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban9, "ABC");
        sharedPrefManager.saveSPString(SharedPrefManager.Jawaban10,"ABC");

    }

    private void Keluar() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void ChexQuiz() {
        recyclerView.setVisibility(View.GONE);
        Hasil.setVisibility(View.VISIBLE);
        Form_Proses.setVisibility(View.GONE);
//        loading.setMessage("Sedang Di Proeses");
//        loading.setCancelable(true);
//        loading.show();
        if (sharedPrefManager.getJawaban1().equals(sharedPrefManager.getJawabanBenar1())){
            sharedPrefManager.saveSPString(SharedPrefManager.point1,"10");
        }else {
            sharedPrefManager.saveSPString(SharedPrefManager.point1,"0");
        }
        if (sharedPrefManager.getJawaban2().equals(sharedPrefManager.getJawabanBenar2())){
            sharedPrefManager.saveSPString(SharedPrefManager.point2,"10");
        }else {
            sharedPrefManager.saveSPString(SharedPrefManager.point2,"0");
        }
        if (sharedPrefManager.getJawaban3().equals(sharedPrefManager.getJawabanBenar3())){
            sharedPrefManager.saveSPString(SharedPrefManager.point3,"10");
        }else {
            sharedPrefManager.saveSPString(SharedPrefManager.point3,"0");
        }
        if (sharedPrefManager.getJawaban4().equals(sharedPrefManager.getJawabanBenar4())){
            sharedPrefManager.saveSPString(SharedPrefManager.point4,"10");
        } else {
            sharedPrefManager.saveSPString(SharedPrefManager.point5,"0");
        }
        if (sharedPrefManager.getJawaban5().equals(sharedPrefManager.getJawabanBenar5())){
            sharedPrefManager.saveSPString(SharedPrefManager.point5,"10");
        } else {
            sharedPrefManager.saveSPString(SharedPrefManager.point6,"0");
        }
        if (sharedPrefManager.getJawaban6().equals(sharedPrefManager.getJawabanBenar6())){
            sharedPrefManager.saveSPString(SharedPrefManager.point6,"10");
        }else {
            sharedPrefManager.saveSPString(SharedPrefManager.point6,"0");
        }
        if (sharedPrefManager.getJawaban7().equals(sharedPrefManager.getJawabanBenar7())){
            sharedPrefManager.saveSPString(SharedPrefManager.point7,"10");
        }else {
            sharedPrefManager.saveSPString(SharedPrefManager.point7,"0");
        }
        if (sharedPrefManager.getJawaban8().equals(sharedPrefManager.getJawabanBenar8())){
            sharedPrefManager.saveSPString(SharedPrefManager.point8,"10");
        }else {
            sharedPrefManager.saveSPString(SharedPrefManager.point8,"0");
        }
        if (sharedPrefManager.getJawaban9().equals(sharedPrefManager.getJawabanBenar9())){
            sharedPrefManager.saveSPString(SharedPrefManager.point9,"10");
        }else {
            sharedPrefManager.saveSPString(SharedPrefManager.point9,"0");
        }
        if (sharedPrefManager.getJawaban10().equals(sharedPrefManager.getJawabanBenar10())){
            sharedPrefManager.saveSPString(SharedPrefManager.point10,"10");
        }else {
            sharedPrefManager.saveSPString(SharedPrefManager.point10,"0");
        }

        String point1 = sharedPrefManager.getPoint1();
        String point2 = sharedPrefManager.getPoint2();
        String point3 = sharedPrefManager.getPoint3();
        String point4 = sharedPrefManager.getPoint4();
        String point5 = sharedPrefManager.getPoint5();
        String point6= sharedPrefManager.getPoint6();
        String point7= sharedPrefManager.getPoint7();
        String point8= sharedPrefManager.getPoint8();
        String point9= sharedPrefManager.getPoint9();
        String point10= sharedPrefManager.getPoint10();
        Log.e("1", String.valueOf(point1));
        Log.e("2", String.valueOf(point2));
        Log.e("3", String.valueOf(point3));
        Log.e("4", String.valueOf(point4));
        Log.e("5", String.valueOf(point5));
        Log.e("6", String.valueOf(point6));
        Log.e("7", String.valueOf(point7));
        Log.e("8", String.valueOf(point8));
        Log.e("9", String.valueOf(point9));
        Log.e("10", String.valueOf(point10));
        try {
            int nilai1= Integer.parseInt(point1);
            int nilai2= Integer.parseInt(point2);
            int nilai3= Integer.parseInt(point3);
            int nilai4= Integer.parseInt(point4);
            int nilai5 = Integer.parseInt(point5);
            int nilai6= Integer.parseInt(point6);
            int nilai7= Integer.parseInt(point7);
            int nilai8= Integer.parseInt(point8);
            int nilai9= Integer.parseInt(point9);
            int nilai10= Integer.parseInt(point10);
            int Hasil=nilai1+nilai2+nilai3+nilai4+nilai5+nilai6+nilai7+nilai8+nilai9+nilai10;
            Log.i("Hasil", String.valueOf(Hasil));
            String HasilAkhir= Integer.toString(Hasil);
            sharedPrefManager.saveSPString(SharedPrefManager.Total,HasilAkhir);
            Total_Nilai.setText(sharedPrefManager.getTotal());
            String Nilai=Total_Nilai.getText().toString();
            int Nilai_Point= Integer.parseInt(Nilai);
            if (Nilai_Point > 70){
                Judul_quiz.setText("Selamat Kamu Berhasil");
                Requiz.setVisibility(View.GONE);
                Exit.setVisibility(View.GONE);
                UpdateJadwal();
//                Goto_apk();
            }else if (Nilai_Point ==70) {
                Judul_quiz.setText("Selamat Kamu Berhasil");
                Requiz.setVisibility(View.GONE);
                Exit.setVisibility(View.GONE);
                UpdateJadwal();
//                Goto_apk();
            }else if (Nilai_Point==0) {
                Requiz.setVisibility(View.VISIBLE);
                Exit.setVisibility(View.VISIBLE);
                Judul_quiz.setText("Kamu Belum Menjawab Quiz");
            }else if (Nilai_Point < 70){
                Requiz.setVisibility(View.VISIBLE);
                Exit.setVisibility(View.VISIBLE);
                Judul_quiz.setText("Mohon Maaf Kamu Belum Berhasil");
            }else {
                Requiz.setVisibility(View.VISIBLE);
                Exit.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Terjadi Kesahalan", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void UpdateJadwal() {
        String Package=getIntent().getExtras().getString("PACKAGE");
        String Imei =sharedPrefManager.getSP_IMEI();
        String status="buka";
        controller.UpdateJadwal(Imei,Package,status);

    }

    private void Goto_apk() {
       String Package=getIntent().getExtras().getString("PACKAGE");
        Intent intent = getPackageManager().getLaunchIntentForPackage(Package);
//        Toast.makeText(Menu_ListAplikasi.this, installedApps.get(i).packages, Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }

    private void tampil_soal() {
//        Total_Nilai.setText("0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point1,"0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point2,"0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point3,"0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point4,"0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point5,"0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point6,"0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point7,"0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point8,"0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point9,"0");
//        sharedPrefManager.saveSPString(SharedPrefManager.point10,"0");
        sharedPrefManager.saveSPString(SharedPrefManager.SP_LOGIN_APP, String.valueOf(true));
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Soal> menuCall = api.tampil_soal();
        menuCall.enqueue(new Callback<Response_Soal>() {
            @Override
            public void onResponse(Call<Response_Soal> call, Response<Response_Soal> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_Soal> soal= response.body().getSoal();
                    boolean status = response.body().isStatus();
                    if (status){
                        loading.dismiss();
                        Adapter_Soal adapter = new Adapter_Soal(Menu_ListSoal.this, soal);
                        recyclerView.setAdapter(adapter);
                    } else {
                        try {
                            loading.dismiss();
                            Toast.makeText(Menu_ListSoal.this, "Tidak Ada Soal saat ini", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Soal> call, Throwable t) {
                try {
                    loading.dismiss();
                    Toast.makeText(Menu_ListSoal.this, "Server Tidak Merespon", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
       Exit_Menu();
    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void gagalmasuk(String Message) {

    }

    @Override
    public void ImeiTidakTerdaftar() {

    }

    @Override
    public void berhasilmasuk(String Message) {

    }

    @Override
    public void TidakAdaKoneksi(String error_message) {
        Toast.makeText(this, ""+error_message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void gagalupdate(String pesan) {
        Toast.makeText(this, ""+pesan, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void berhasilupdate(String pesan) {
        Goto_apk();

    }
}
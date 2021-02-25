package com.example.screening_time.Anak.Features;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.screening_time.Anak.Server.Controller.Controller;
import com.example.screening_time.Anak.Server.Controller.MyController;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.VISIBLE;

public class Menu_PopUp extends AppCompatActivity implements MyController {
    String Package;
    @BindView(R.id.linearPopUp)
    LinearLayout PopUp;
    @BindView(R.id.Quiz)
    ImageView quiz;
    @BindView(R.id.IconExit)
    ImageView exit;
    @BindView(R.id.Pesan)
    TextView pesan;
    SharedPrefManager sharedPrefManager;
    Controller controller;
    ProgressDialog loading;
    @BindView(R.id.AKU)
    CardView setting;
    @BindView(R.id.kamu)
    CardView Out;
    @BindView(R.id.linearLog)
    LinearLayout log;
    @BindView(R.id.password)
    EditText Pass;
    @BindView(R.id.BtnSubmit)
    Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__pop_up);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        controller =new Controller(this);
        loading=new ProgressDialog(this);
        PopUp.setVisibility(VISIBLE);
        log.setVisibility(View.GONE);
        Package=getIntent().getExtras().getString("PACKAGE");
//        Toast.makeText(this, "Mau ngapain Buka Buka Whatsapp??", Toast.LENGTH_SHORT).show();
        String Imei=sharedPrefManager.getSP_IMEI();
        RequestJadwal(Imei,Package);
        String Setting="com.android.settings";
        pesan.setText("");
        if (Package.equals(Setting)){
            pesan.setText("");
//            PopUp.setVisibility(View.GONE);
            setting.setVisibility(View.GONE);
            Out.setVisibility(View.GONE);
            log.setVisibility(VISIBLE);

        }

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoQuiz();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoExit();
            }
        });
    }

    private void GotoExit() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void GotoQuiz() {
        Package=getIntent().getExtras().getString("PACKAGE");
        Intent i=new Intent(Menu_PopUp.this,Menu_Tugas.class);
        i.putExtra("PACKAGE",Package );
        startActivity(i);
        finish();
    }

    private void RequestJadwal(String imei, String Package) {
        loading.setMessage("Mohon Tunggu.....");
        loading.setCancelable(false);
        loading.show();
        controller.RequestJadwal(imei,Package);

    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void gagalmasuk(String Message) {
        loading.dismiss();
        Package=getIntent().getExtras().getString("PACKAGE");
        Intent intent = getPackageManager().getLaunchIntentForPackage(Package);
//        Toast.makeText(Menu_ListAplikasi.this, installedApps.get(i).packages, Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();

    }
    @Override
    public void ImeiTidakTerdaftar() {

    }
    @Override
    public void berhasilmasuk(String Message) {
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
        loading.dismiss();
        PopUp.setVisibility(VISIBLE);
        pesan.setText(Message);
    }
    @Override
    public void TidakAdaKoneksi(String error_message) {
        try {
            loading.dismiss();
            Toast.makeText(this, ""+error_message, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gagalupdate(String pesan) {

    }

    @Override
    public void berhasilupdate(String pesan) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
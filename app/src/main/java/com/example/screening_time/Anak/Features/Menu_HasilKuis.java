package com.example.screening_time.Anak.Features;//package omkabel.project.timeup.Features;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import omkabel.project.timeup.R;
//
//public class Menu_HasilKuis extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu__hasil_kuis);
//        TextView hasil = (TextView) findViewById(R.id.hasil);
//        TextView nilai = (TextView) findViewById(R.id.nilai);
//        hasil.setText("Jawaban Benar :" + Menu_Quiz.benar + "\nJawaban Salah :" + Menu_Quiz.salah);
//        nilai.setText("" + Menu_Quiz.hasil);
//    }
//
//    public void ulangi(View view) {
//        finish();
//        Intent a = new Intent(getApplicationContext(), Menu_Quiz.class);
//        startActivity(a);
//    }
//}
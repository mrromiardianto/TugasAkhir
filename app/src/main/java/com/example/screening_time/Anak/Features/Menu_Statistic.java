package com.example.screening_time.Anak.Features;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.screening_time.Anak.Fragment.Menu_Utama;
import com.example.screening_time.Anak.Server.Adapter.Adapter_Usage;
import com.example.screening_time.Anak.Server.ApiServices;
import com.example.screening_time.Anak.Server.InitRetrofit;
import com.example.screening_time.Anak.Server.Item.Item_Jadwal;
import com.example.screening_time.Anak.Server.Response.Response_Jadwal;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.Anak.Utils.MyMarkerView;
import com.example.screening_time.R;
import com.github.mikephil.charting.charts.LineChart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

public class Menu_Statistic extends AppCompatActivity {
    @BindView(R.id.chart)
    LineChart mChart;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;
    @BindView(R.id.list_usage)
    RecyclerView recyclerView;
    @BindView(R.id.Tanggal_statistic)
    EditText Tanggal;
    List<String> x = new ArrayList<String>();
    List<String> y = new ArrayList<String>();
    public static List<Item_Jadwal> dataIndikator;
    List<String> datax = new ArrayList<String>();
    String deskripsi;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__statistic);
        ButterKnife.bind(this);
        loading =new ProgressDialog(this);
        sharedPrefManager=new SharedPrefManager(this);
        dataIndikator = new ArrayList<>();
        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);
        MyMarkerView mv = new MyMarkerView(getApplicationContext(), R.layout.custom_marker_view);
        mv.setChartView(mChart);
        mChart.setMarker(mv);
        renderData();
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(llm);
        loading.setMessage("Mohon Tunggu Sebentar.....");
        loading.setCancelable(false);
        loading.show();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Date FormatTanggal = new Date();
        Tanggal.setText(dateFormatter.format(FormatTanggal));
        Tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDateDialog();
            }
        });
        try {
            Tampil_Usage(Tanggal.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void Tampil_Usage(String Tanggal) {
        String imei=getIntent().getStringExtra("imei");;
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Jadwal> menuCall = api.tampil_usage(imei,Tanggal);
        menuCall.enqueue(new Callback<Response_Jadwal>() {
            @Override
            public void onResponse(Call<Response_Jadwal> call, Response<Response_Jadwal> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_Jadwal> jadwal= response.body().getJadwal();
                    boolean status = response.body().isStatus();
                    if (status){
                    try {
                    dataIndikator = response.body().getJadwal();
                    List<Entry> yVals = new ArrayList<>();
                    for (int i = 0; i < dataIndikator.size(); i++) {
                       String total=dataIndikator.get(i).getTotal();
//                       int id=dataIndikator.get(i);
                       deskripsi=dataIndikator.get(i).getNama();
//                        datax.add(dataIndikator.get(i).getTotal());
                        float a12 = Float.parseFloat(total);
//                        int a22 = Integer.parseInt(id);
                        yVals.add(new Entry(i, a12));
                        Log.i("datax", String.valueOf(a12));
//                        yVals.add(new Entry(10, 250));
//                        Toast.makeText(Menu_Statistic.this, ""+i, Toast.LENGTH_SHORT).show();
                    }
                    ArrayList<String> xVals = new ArrayList<String>();
                    for (int i = 0; i < dataIndikator.size(); i++) {
                        String total=dataIndikator.get(i).getTotal();
//                        datax.add(dataIndikator.get(i).getTotal());
                        float a12 = Float.parseFloat(total);
//                        int a22 = Integer.parseInt(datax.get());

                    }
//                    LineDataSet dataSet = new LineDataSet(yVals, dataIndikator.get(0).getId());
//                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//                    LineData data = new LineData(dataSet);
//                    LimitLine line = new LimitLine(12f, dataIndikator.get(0).getId());
//                    line.setTextSize(12f);
//                    line.setLineWidth(4f);
//                    YAxis leftAxis = mChart.getAxisLeft();
//                    leftAxis.addLimitLine(line);
//                    mChart.setData(data);
////                    mChart.setDescription(getString(R.string.ss));
//                    mChart.animateY(2000);
                    LineDataSet set1;
                    if (mChart.getData() != null &&
                            mChart.getData().getDataSetCount() > 0) {
                        set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
                        set1.setValues(yVals);
                        mChart.getData().notifyDataChanged();
                        mChart.notifyDataSetChanged();
                    } else {
                        set1 = new LineDataSet(yVals, "Statistik Penggunaan Aplikasi");
                        set1.setDrawIcons(true);
                        set1.enableDashedLine(10f, 5f, 0f);
                        set1.enableDashedHighlightLine(10f, 5f, 0f);
                        set1.setColor(Color.DKGRAY);
                        set1.setCircleColor(Color.DKGRAY);
                        set1.setLineWidth(1f);
                        set1.setCircleRadius(3f);
                        set1.setDrawCircleHole(false);
                        set1.setValueTextSize(9f);
                        set1.setDrawFilled(true);
                        set1.setFormLineWidth(1f);
                        set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                        set1.setFormSize(15.f);
                        if (Utils.getSDKInt() >= 18) {
                            Drawable drawable = ContextCompat.getDrawable(Menu_Statistic.this, R.drawable.fade_blue);
                            set1.setFillDrawable(drawable);
                        } else {
                            set1.setFillColor(Color.DKGRAY);
                        }
                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(set1);
                        LineData data = new LineData(dataSets);
                        mChart.setData(data);
                    }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                        loading.dismiss();
                        Adapter_Usage adapter = new Adapter_Usage(Menu_Statistic.this, jadwal);
                        recyclerView.setAdapter(adapter);
                    } else {
                        try {
                            loading.dismiss();
                            Toast.makeText(Menu_Statistic.this, "Tidak Ada data saat ini", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Response_Jadwal> call, Throwable t) {
                try {
                    loading.dismiss();
                    Toast.makeText(Menu_Statistic.this, "Server Tidak Merespon", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void renderData() {
        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(10f);
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawLimitLinesBehindData(true);

//        LimitLine ll1 = new LimitLine(215f, "Maximum Limit");
//        ll1.setLineWidth(4f);
//        ll1.enableDashedLine(10f, 10f, 0f);
//        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
//        ll1.setTextSize(10f);
//
//        LimitLine ll2 = new LimitLine(70f, "Minimum Limit");
//        ll2.setLineWidth(4f);
//        ll2.enableDashedLine(10f, 10f, 0f);
//        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//        ll2.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
//        leftAxis.addLimitLine(ll1);
//        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(350f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);

        mChart.getAxisRight().setEnabled(false);
        setData();
//        Tampil_Usage(Tanggal.getText().toString());
    }

    private void setData() {
//        List = new ArrayList<>();

        List<Entry> values = new ArrayList<>();
        values.add(new Entry(1,200));
        values.add(new Entry(2,300));
//        values.add(new I(2, 2));
//        values.add(Item_Jadwal(3, 20));

        LineDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "Statistik Penggunaan Aplikasi");
            set1.setDrawIcons(false);
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.DKGRAY);
            set1.setCircleColor(Color.DKGRAY);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.DKGRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);
            mChart.setData(data);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(Menu_Statistic.this, Menu_Utama.class);
        startActivity(intent);
        finish();
    }
    private void showDateDialog() {
        Calendar newCalendar = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            newCalendar = Calendar.getInstance();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog = new DatePickerDialog(Menu_Statistic.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar newDate = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        newDate = Calendar.getInstance();
                    }
                    newDate.set(year, monthOfYear, dayOfMonth);
                    Tanggal.setText(dateFormatter.format(newDate.getTime()));
                    try {
//                        Tampil_Usage(Tanggal.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        }
        datePickerDialog.show();
    }
}

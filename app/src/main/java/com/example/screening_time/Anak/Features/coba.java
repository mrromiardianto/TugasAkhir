package com.example.screening_time.Anak.Features;//package omkabel.project.timeup.Features;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class MainBarChart extends AppCompatActivity {
//
//    public static List<DataItemJ554IndikatorByTipe> dataIndikator;
//
//
//    DataItemJ54GetAllIndikator dataItemGet;
//    String idTipe;
//    BarChart chart;
//
//    int start, end;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        chart = (BarChart) findViewById(R.id.chart);
//
//        dataIndikator = new ArrayList<>();
//
//
//        int posisi = getIntent().getIntExtra("posisi", 0);
//
//        dataItemGet = MenuTipeIndikatorActivity.dataItemJ23RiwayatLelangs.get(posisi);
//
//        idTipe = dataItemGet.getId();
//        Toast.makeText(getApplicationContext(), "id " + idTipe, Toast.LENGTH_LONG).show();
//
//
//        getListData();
//
//    }
//
//    private void getListData() {
//        try {
//            RestApi api = MyRetrofitClient.getInstanceRetrofit2();
//
//
//            Call<RssJ544IndikatorByTipe> call = api.getIndikatorByTipe(idTipe);
//            call.enqueue(new Callback<RssJ544IndikatorByTipe>() {
//                @Override
//                public void onResponse(Call<RssJ544IndikatorByTipe> call, Response<RssJ544IndikatorByTipe> response) {
////                    Log.d("onResponse", response.body().toString());
//
//                    String r = response.body().getResult();
//                    Log.d("adaa22", response.body().toString());
//
//                    if (r.equalsIgnoreCase("true")) {
//
//
//                        dataIndikator = response.body().getData();
//
//                        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
//
//
//                        for (int i = 0; i < dataIndikator.size(); i++) {
//                            DataItemJ554IndikatorByTipe x = dataIndikator.get(i);
//                            float a12 = Float.parseFloat(x.getJmlIndikator());
//                            int a22 = Integer.parseInt(x.getId());
//                            yVals.add(new BarEntry(a12, i));
//                        }
//
//
//                        ArrayList<String> xVals = new ArrayList<String>();
//                        for (int i = 0; i < dataIndikator.size(); i++) {
//                            DataItemJ554IndikatorByTipe x = dataIndikator.get(i);
//                            float a12 = Float.parseFloat(x.getJmlIndikator());
//                            String a22 = x.getThIndikator();
//
//                        }
//
//                        BarDataSet dataSet = new BarDataSet(yVals, dataIndikator.get(0).getTipeIndikator());
//                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//
//                        BarData data = new BarData(xVals, dataSet);
//
//
//                        LimitLine line = new LimitLine(12f, dataIndikator.get(0).getTipeIndikator());
//                        line.setTextSize(12f);
//                        line.setLineWidth(4f);
//                        YAxis leftAxis = chart.getAxisLeft();
//                        leftAxis.addLimitLine(line);
//
//                        chart.setData(data);
//                        chart.setDescription(dataIndikator.get(0).getTipeIndikator());
//                        chart.animateY(2000);
//
//
//                    } else {
//
//                    }
//
//
//                }
//
//                @Override
//                public void onFailure(Call<RssJ544IndikatorByTipe> call, Throwable t) {
//
//                }
//            });
//
////
//        } catch (Exception e) {
//        }
//    }
//
//
//}

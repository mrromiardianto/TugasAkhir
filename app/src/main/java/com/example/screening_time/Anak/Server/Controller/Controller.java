package com.example.screening_time.Anak.Server.Controller;

import android.util.Log;

import com.example.screening_time.Anak.Server.InitRetrofit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Controller {
    final MyController myController;
    public Controller(MyController myController) {
        this.myController = myController;
    }

    public void ChexImei(String Imei){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().ChexEmai(Imei);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
//                            String Imei=jsonRESULTS.getString("imei");
                            myController.ImeiTerdaftar(Imei);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                myController.ImeiTidakTerdaftar();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Ada Masalah Internet";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void Masuk(String imei, String password){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().UserMasuk(imei,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.berhasilmasuk(message);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String message=jsonRESULTS.getString("message");
                                myController.gagalmasuk(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        myController.TidakAdaKoneksi(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void Daftar(String Imei, String Nama, String Password, String NamaAnak){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().UserDaftar(Imei,Nama,Password,NamaAnak);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.berhasilmasuk(message);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String message=jsonRESULTS.getString("message");
                                myController.gagalmasuk(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        myController.TidakAdaKoneksi(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void SimpanJadwal(String Imei, String jamMulai, String jamAkhir, String Nama, String aPackage){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().SimpanJadwal(Imei,jamMulai,jamAkhir,Nama,aPackage);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.berhasilmasuk(message);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String message=jsonRESULTS.getString("message");
                                myController.gagalmasuk(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        myController.TidakAdaKoneksi(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void RequestJadwal(String imei, String aPackage){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().RequestJadwal(imei,aPackage);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.berhasilmasuk(message);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
//                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String message=jsonRESULTS.getString("message");
                                myController.gagalmasuk(message);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        myController.TidakAdaKoneksi(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void RequestPengingat(String imei, String pengingat){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().RequestPengingat(imei,pengingat);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.berhasilmasuk(message);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.gagalmasuk(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        myController.TidakAdaKoneksi(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void RequestResetPassword(String sp_imei, String nama, String password){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().ResetPassword(sp_imei,nama,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.berhasilmasuk(message);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String message=jsonRESULTS.getString("message");
                                myController.gagalmasuk(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        myController.TidakAdaKoneksi(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void deletejadwal(String id){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().deletejadwal(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.berhasilmasuk(message);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String message=jsonRESULTS.getString("message");
                                myController.gagalmasuk(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        myController.TidakAdaKoneksi(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void UpdateJadwal(String Imei, String Package, String Status){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().JadwalUpdate(Imei,Package,Status);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.berhasilupdate(message);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String message=jsonRESULTS.getString("message");
                                myController.gagalupdate(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        myController.TidakAdaKoneksi(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void SimpanUsage(String Imei , String Package, String Nama){
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().SimpanUsage(Imei,Package,Nama);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            myController.berhasilupdate(message);
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String message=jsonRESULTS.getString("message");
                                myController.gagalupdate(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        myController.TidakAdaKoneksi(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    myController.TidakAdaKoneksi(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}

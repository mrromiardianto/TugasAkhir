package com.example.screening_time.Controller;

import android.util.Log;

import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.Response.Response_Device;
import com.example.screening_time.Server.ApiServices;
import com.example.screening_time.Server.InitRetrofit;
import com.example.screening_time.View.MyDevice;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Device {
    final MyDevice myDevice;

    public Device(MyDevice myDevice) {
        this.myDevice = myDevice;
    }

    public void CheckImei(String Imei){
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().CheckImei(Imei);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Berhasil=jsonRESULTS.getString("message");
                            myDevice.ImeiTerdaftar(Berhasil);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Gagal=jsonRESULTS.getString("message");
                               myDevice.ImeiTidakTerdaftar(Gagal);
//                                Toast.makeText(MenuRegist.this, ""+Gagal_LOGIN, Toast.LENGTH_SHORT).show();
//                                Log.v("pesan",Gagal_LOGIN);
//                                userLogin.login_gagal(Gagal_LOGIN);
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
                        myDevice.NoInternet(error_message);
//                        Toast.makeText(MenuRegist.this, ""+error_message, Toast.LENGTH_SHORT).show();
//                        loading.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Server Tidak Merespon";
                    myDevice.NoInternet(error_message);
//                    userLogin.login_gagal(error_message);
//                    Toast.makeText(MenuRegist.this, ""+error_message, Toast.LENGTH_SHORT).show();
//                    loading.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });
    }

    public void sinkron(String imei, String email){
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().addsinkron(imei, email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Berhasil=jsonRESULTS.getString("message");
                            myDevice.ImeiTerdaftar(Berhasil);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Gagal=jsonRESULTS.getString("message");
                                myDevice.ImeiTidakTerdaftar(Gagal);
//                                Toast.makeText(MenuRegist.this, ""+Gagal_LOGIN, Toast.LENGTH_SHORT).show();
//                                Log.v("pesan",Gagal_LOGIN);
//                                userLogin.login_gagal(Gagal_LOGIN);
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
                        myDevice.NoInternet(error_message);
//                        Toast.makeText(MenuRegist.this, ""+error_message, Toast.LENGTH_SHORT).show();
//                        loading.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Server Tidak Merespon";
                    myDevice.NoInternet(error_message);
//                    userLogin.login_gagal(error_message);
//                    Toast.makeText(MenuRegist.this, ""+error_message, Toast.LENGTH_SHORT).show();
//                    loading.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });

    }
    public void GetDevice(String email){
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Device> menuCall = api.getSikron(email);
        menuCall.enqueue(new Callback<Response_Device>() {
            @Override
            public void onResponse(Call<Response_Device> call, Response<Response_Device> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Model_Device> device= response.body().getDevice();
                    boolean status = response.body().isStatus();
                    if (status){
                        try {
                            myDevice.truedata(device);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            String Message="Tidak Ada data";
                            myDevice.ImeiTidakTerdaftar(Message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Response_Device> call, Throwable t) {
                try {
                    String Message="Tidak Ada data";
                    myDevice.NoInternet(Message);
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

}

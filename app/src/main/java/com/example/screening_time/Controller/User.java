package com.example.screening_time.Controller;

import android.util.Log;
import android.widget.Toast;

import com.example.screening_time.Server.InitRetrofit;
import com.example.screening_time.Session.SharedPrefManager;
import com.example.screening_time.View.MyUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User {
    MyUser myUser;
    SharedPrefManager sharedPrefManager;

    public User (MyUser myUser){
        this.myUser = myUser;
    }

    public void register(String Imei, String Email, String Password, String Confirm_password, String Role ,String Kata_pengingat){

        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().userRegister(Imei, Email, Password, Confirm_password, Role, Kata_pengingat);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Berhasil=jsonRESULTS.getString("message");

                            myUser.berhasilregister(Berhasil);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Gagal=jsonRESULTS.getString("message");
                                myUser.registergagal(Gagal);
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
                        myUser.nointernet(error_message);
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
                    myUser.nointernet(error_message);
//                    userLogin.login_gagal(error_message);
//                    Toast.makeText(MenuRegist.this, ""+error_message, Toast.LENGTH_SHORT).show();
//                    loading.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });



    }

    public void userlogin (String imei, String password) {
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().userLogin(imei, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Berhasil=jsonRESULTS.getString("message");
//                            String Berhasil=jsonRESULTS.getString("message");
                            String id = jsonRESULTS.getString("id");
                            String imei = jsonRESULTS.getString("imei");
                            String email = jsonRESULTS.getString("email");
                            String password = jsonRESULTS.getString("password");
                            String role = jsonRESULTS.getString("role");
                            String kata_pengingat = jsonRESULTS.getString("kata_pengingat");
                            myUser.berhasilregister(Berhasil,id, imei, email, password,role, kata_pengingat);
                            myUser.berhasilregister(Berhasil);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Gagal=jsonRESULTS.getString("message");
                                myUser.registergagal(Gagal);
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
                        myUser.nointernet(error_message);
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
                    myUser.nointernet(error_message);
//                    userLogin.login_gagal(error_message);
//                    Toast.makeText(MenuRegist.this, ""+error_message, Toast.LENGTH_SHORT).show();
//                    loading.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });


    }
}

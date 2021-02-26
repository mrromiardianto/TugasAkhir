package com.example.screening_time.Server;

import com.example.screening_time.UrlServer.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {
    //ip jaringan, url di isi dengan nama folder yang ada pada htdocs
    public static final  String IP= Url.IP+"Skripsi/";
    public static final String BASE_URL =IP+"Fungsi/";
    private static InitRetrofit mInstance;
    private Retrofit retrofit;

    private InitRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized InitRetrofit getInstance(){
        if (mInstance == null ){
            mInstance = new InitRetrofit();
        }
        return mInstance;
    }
    public ApiServices getApi(){
        return retrofit.create(ApiServices.class);
    }
}

package com.example.screening_time.Server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {
    //ip jaringan, url di isi dengan nama folder yang ada pada htdocs
    public static final String BASE_URL ="http://192.168.43.80/Skripsi/Fungsi/";
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

package com.example.screening_time.Anak.Server;
import com.example.screening_time.UrlServer.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {
    public static final String BASE_URL = Url.IP +"Skripsi/Fungsi/";
//    public  static final String Url_Provinsi="http://www.emsifa.com/api-wilayah-indonesia/api/";
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

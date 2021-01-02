package com.example.screening_time.Server;

//import com.example.latihan.Respon.Response_aplikasi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//
public interface ApiServices {
    @FormUrlEncoded
    //memanggil file text editor (sublime)
    @POST("login.php")
    Call<ResponseBody> userLogin(
            @Field("imei") String imei,
            @Field("password") String password
    );

    @FormUrlEncoded
    //memanggil file text editor (sublime)
    @POST("register.php")
    Call<ResponseBody> userRegister(
            @Field("imei") String imei,
            @Field("email") String email,
            @Field("password") String password,
            @Field("confirm_password") String confirm_password,
            @Field("role") String role,
            @Field("kata_pengingat") String kata_pengingat
    );

    @POST("getimei.php")
    Call<ResponseBody> CheckImei(
            @Field("imei") String imei
    );

//    @GET("tampilapk.php/{imei}")
//    //function baru
//    Call<Response_aplikasi> tampil_aplikasi(
//        @Query("imei") String imei
//    );
}

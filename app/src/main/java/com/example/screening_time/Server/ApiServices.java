package com.example.screening_time.Server;

//import com.example.latihan.Respon.Response_aplikasi;

import com.example.screening_time.Response.Response_Device;
import com.example.screening_time.Response.Response_Jadwal;
import com.example.screening_time.Response.Response_tugas;

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
    @POST("tambah_tugas.php")
    Call<ResponseBody> addtugas(
            @Field("imei") String imei,
            @Field("email") String email,
            @Field("nama_tugas") String tugas
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
    @FormUrlEncoded
    @POST("getimei.php")
    Call<ResponseBody> CheckImei(
            @Field("imei") String imei
    );

    @GET("get_sinkron.php/{email}")
    //function baru
    Call<Response_Device> getSikron(
        @Query("email") String email
    );
    @GET("tampil_tugas.php/{imei}")
        //function baru
    Call<Response_tugas> gettugas(
            @Query("imei") String imei
    );
    @FormUrlEncoded
    //memanggil file text editor (sublime)
    @POST("sinkron_ponsel.php")
    Call<ResponseBody> addsinkron(
            @Field("imei") String imei,
            @Field("email") String email
    );

    @GET("tampil_jadwal.php/{imei}")
        //function baru
    Call<Response_Jadwal> getJadwal(
            @Query("imei") String imei
    );
    @FormUrlEncoded
    @POST("UpdateJadwal.php")
    Call<ResponseBody> JadwalUpdate(
            @Field("imei") String imai,
            @Field("package") String Package,
            @Field("status") String status
    );
    @FormUrlEncoded
    @POST("DeleteJadwal.php")
    Call<ResponseBody> deletejadwal(
            @Field("id") String id
    );
}

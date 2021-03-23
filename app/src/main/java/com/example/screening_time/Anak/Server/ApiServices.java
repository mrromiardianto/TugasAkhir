package com.example.screening_time.Anak.Server;

import com.example.screening_time.Anak.Server.Response.Response_Jadwal;
import com.example.screening_time.Anak.Server.Response.Response_Soal;
import com.example.screening_time.Anak.Server.Response.Response_Tugas;

import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {
    @FormUrlEncoded
    @POST("getimei.php")
    Call<ResponseBody> ChexEmai(
            @Field("imei") String emai
    );
    @FormUrlEncoded
    @POST("Masuk.php")
    Call<ResponseBody> UserMasuk(
            @Field("Imei") String emai,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("RegisterImei.php")
    Call<ResponseBody> UserDaftar(
            @Field("Imei") String emai,
            @Field("kata_pengingat") String nama,
            @Field("password") String password,
            @Field("nama") String namaanak
    );

    @FormUrlEncoded
    @POST("simpanjadwal.php")
    Call<ResponseBody> SimpanJadwal(
            @Field("imei") String imei,
            @Field("jammulai") String jammulai,
            @Field("jamakhir") String jamakhir,
            @Field("nama") String nama,
            @Field("packeg") String packeg
    );
    @FormUrlEncoded
    @POST("edit_jadwal.php")
    Call<ResponseBody> SimpanEditJadwal(
            @Field("imei") String imei,
            @Field("jammulai") String jammulai,
            @Field("jamakhir") String jamakhir,
            @Field("nama") String nama,
            @Field("packeg") String packeg
    );
    @FormUrlEncoded
    @POST("cek_jadwal.php")
    Call<ResponseBody> RequestJadwal(
            @Field("imei") String Imai,
            @Field("package") String Package
    );
    @GET("TampilSoal.php")
    Call<Response_Soal> tampil_soal();
    @GET("getJawaban.php")
    Call<Response_Soal> tampil_pilihan();

    @FormUrlEncoded
    @POST("LupaPassword.php")
    Call<ResponseBody> RequestPengingat(
            @Field("imei") String Imei,
            @Field("nama") String Nama
    );
    @FormUrlEncoded
    @POST("ResetPassword.php")
    Call<ResponseBody> ResetPassword(
            @Field("Imei") String emai,
            @Field("nama") String nama,
            @Field("password") String password
    );

    @GET("tampil_jadwal.php/{imei}")
    Call<Response_Jadwal> tampil_jadwal(
            @Query("imei") String imei
            );
    @FormUrlEncoded
    @POST("DeleteJadwal.php")
    Call<ResponseBody> deletejadwal(
            @Field("id") String id
    );
    @GET("TampilUsage.php/{imei}{Tanggal}")
    Call<Response_Jadwal> tampil_usage(
            @Query("imei") String imei,
            @Query("Tanggal") String Tanggal
    );
    @FormUrlEncoded
    @POST("UpdateJadwal.php")
    Call<ResponseBody> JadwalUpdate(
            @Field("imei") String imai,
            @Field("package") String Package,
            @Field("status") String status
    );

    @GET("notifikasi.php/{imei}")
    Call<Response_Jadwal> getnotifikasi(
            @Query("imei") String imei
    );

    @FormUrlEncoded
    @POST("SimpanUsage.php")
    Call<ResponseBody> SimpanUsage(
            @Field("imei") String imai,
            @Field("package") String Package,
            @Field("nama") String nama
    );

    @GET("tampil_tugas.php/{imei}")
    Call<Response_Tugas> tampil_tugas(
            @Query("imei") String imei
    );
    @FormUrlEncoded
    @POST("kirim_tugas.php")
    Call<ResponseBody> KirimTugas(
            @Field("id_tugas") String id_tugas,
            @Field("imei") String imei,
            @Field("file") String file,
            @Field("package") String pakage,
            @Field("nama") String nama
    );
}

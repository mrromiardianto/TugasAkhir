package com.example.screening_time.Anak.Server.Controller;

public interface MyController {
    void ImeiTerdaftar(String imei);

    void gagalmasuk(String Message);

    void ImeiTidakTerdaftar();

    void berhasilmasuk(String Message);

    void TidakAdaKoneksi(String error_message);


    void gagalupdate(String pesan);

    void berhasilupdate(String pesan);
}

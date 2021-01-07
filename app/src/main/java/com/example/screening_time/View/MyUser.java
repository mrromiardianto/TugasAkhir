package com.example.screening_time.View;

public interface MyUser {
    void berhasilregister(String berhasil, String id, String imei, String email, String password, String role, String kata_pengingat);

    void berhasilregister(String Pesan);

    void registergagal(String Pesan);

    void nointernet(String Pesan);

    void saveuser(String id, String imei, String email, String password, String role, String kata_pengingat);
}

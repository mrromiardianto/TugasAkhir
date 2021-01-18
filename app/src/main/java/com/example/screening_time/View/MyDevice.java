package com.example.screening_time.View;

import com.example.screening_time.Model.Model_Device;
import com.example.screening_time.Model.Model_tugas;

import java.util.List;

public interface MyDevice {
    void truedata(List<Model_Device> devices);

    void ImeiTerdaftar(String Message);
    void ImeiTidakTerdaftar(String Message);
    void NoInternet(String Message);

    void suksesgetdata(List<Model_tugas> tugases);
}

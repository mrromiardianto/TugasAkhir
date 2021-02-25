package com.example.screening_time.Anak.Server.Item;

import com.google.gson.annotations.SerializedName;

public class Item_Soal {
    @SerializedName("id_soal")
    private String id_soal;

    @SerializedName("imei")
    private String imei;

    @SerializedName("nama")
    private String nama;

    @SerializedName("soal")
    private String soal;

    @SerializedName("jawaban")
    private String jawaban;

    @SerializedName("status")
    private String status;
    @SerializedName("a")
    private String a;
    @SerializedName("b")
    private String b;
    @SerializedName("c")
    private String c;
    @SerializedName("d")
    private String d;


    public void setId_soal(String id_soal){
        this.id_soal = id_soal;
    }

    public String getId_soal(){
        return id_soal;
    }
    public void setImei(String imei){
        this.imei = imei;
    }

    public String getImei(){
        return imei;
    }
    public void setSoal(String soal){
        this.soal = soal;
    }

    public String getSoal(){
        return soal;
    }
    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public void setJawaban(String jawaban){
        this.jawaban = jawaban;
    }

    public String getJawaban(){
        return jawaban;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
    public void setA(String a){
        this.a = a;
    }

    public String getA(){
        return a;
    }
    public void setB(String b){
        this.b = b;
    }

    public String getB(){
        return b;
    }
    public void setC(String c){
        this.c = c;
    }
    public String getC(){
        return c;
    }
    public void setD(String d){
        this.d = d;
    }

    public String getD(){
        return d;
    }
    @Override
    public String toString(){
        return
                "Item_Device{" +
                        "id_soal= '" + id_soal+ '\'' +
                        "nama= '" + nama+ '\'' +
                        ",status= '" + status+ '\'' +
                        ",soal= '" + soal+ '\'' +
                        ",jawaban= '" + jawaban+ '\'' +
                        ",status= '" + status+ '\'' +
                        "}";
    }

}

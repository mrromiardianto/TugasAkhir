package com.example.screening_time.Anak.Server.Response;

import com.example.screening_time.Anak.Server.Item.Item_Soal;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class Response_Soal {
    @SerializedName("soal")
    private List<Item_Soal> soal;
    @SerializedName("status")
    private boolean status;

    public void setSoal(List<Item_Soal> soal) {
        this.soal = soal;
    }

    public List<Item_Soal> getSoal()
    {
        return soal;
    }

    public void setStatus(boolean status){

        this.status = status;
    }

    public boolean isStatus(){

        return status;
    }

    @Override
    public String toString(){
        return
                "Response_Soal{" +
                        "soal = '" +soal+ '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}

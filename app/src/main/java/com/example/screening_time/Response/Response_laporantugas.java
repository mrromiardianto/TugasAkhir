package com.example.screening_time.Response;

import java.util.List;
//import javax.annotation.Generated;
import com.example.screening_time.Model.Model_laporantugas;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

//@Generated("com.robohorse.robopojogenerator")
public class Response_laporantugas implements Serializable {

	@SerializedName("status")
	private boolean status;

	@SerializedName("laporan_tugas")
	private List<Model_laporantugas> laporanTugas;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setLaporanTugas(List<Model_laporantugas> laporanTugas){
		this.laporanTugas = laporanTugas;
	}

	public List<Model_laporantugas> getLaporanTugas(){
		return laporanTugas;
	}

	@Override
 	public String toString(){
		return 
			"Response_laporantugas{" + 
			"status = '" + status + '\'' + 
			",laporan_tugas = '" + laporanTugas + '\'' + 
			"}";
		}
}
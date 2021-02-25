package com.example.screening_time.Response;

import java.util.List;
//import javax.annotation.Generated;
import com.example.screening_time.Fitur.OrangTua.Tugas;
import com.example.screening_time.Model.Model_tugas;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

//@Generated("com.robohorse.robopojogenerator")
public class Response_tugas implements Serializable {

	@SerializedName("status")
	private boolean status;

	@SerializedName("tugas")
	private List<Model_tugas> tugas;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setTugas(List<Model_tugas> tugas){
		this.tugas = tugas;
	}

	public List<Model_tugas> getTugas(){
		return tugas;
	}

	@Override
 	public String toString(){
		return 
			"Response_tugas{" + 
			"status = '" + status + '\'' + 
			",tugas = '" + tugas + '\'' + 
			"}";
		}
}
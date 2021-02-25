package com.example.screening_time.Anak.Server.Response;

import com.example.screening_time.Anak.Server.Item.Item_Tugas;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


//import javax.annotation.Generated;

//@Generated("com.robohorse.robopojogenerator")
public class Response_Tugas implements Serializable {

	@SerializedName("status")
	private boolean status;

	@SerializedName("tugas")
	private List<Item_Tugas> tugas;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setTugas(List<Item_Tugas> tugas){
		this.tugas = tugas;
	}

	public List<Item_Tugas> getTugas(){
		return tugas;
	}

	@Override
 	public String toString(){
		return 
			"Response_Tugas{" + 
			"status = '" + status + '\'' + 
			",tugas = '" + tugas + '\'' + 
			"}";
		}
}
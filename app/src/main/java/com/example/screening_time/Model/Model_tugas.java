package com.example.screening_time.Model;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

//@Generated("com.robohorse.robopojogenerator")
public class Model_tugas implements Serializable {

	@SerializedName("id")
	private String id;

	@SerializedName("nama_tugas")
	private String namaTugas;

	@SerializedName("imei")
	private String imei;

	@SerializedName("email")
	private String email;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNamaTugas(String namaTugas){
		this.namaTugas = namaTugas;
	}

	public String getNamaTugas(){
		return namaTugas;
	}

	public void setImei(String imei){
		this.imei = imei;
	}

	public String getImei(){
		return imei;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"Model_tugas{" + 
			"id = '" + id + '\'' + 
			",nama_tugas = '" + namaTugas + '\'' + 
			",imei = '" + imei + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
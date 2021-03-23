package com.example.screening_time.Model;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

//@Generated("com.robohorse.robopojogenerator")
public class Model_laporantugas implements Serializable {

	@SerializedName("id")
	private String id;

	@SerializedName("id_tugas")
	private String idTugas;

	@SerializedName("imei")
	private String imei;

	@SerializedName("file")
	private String file;

	@SerializedName("package")
	private String jsonMemberPackage;

	@SerializedName("nama_tugas")
	private String nama;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIdTugas(String idTugas){
		this.idTugas = idTugas;
	}

	public String getIdTugas(){
		return idTugas;
	}

	public void setImei(String imei){
		this.imei = imei;
	}

	public String getImei(){
		return imei;
	}

	public void setFile(String file){
		this.file = file;
	}

	public String getFile(){
		return file;
	}

	public void setJsonMemberPackage(String jsonMemberPackage){
		this.jsonMemberPackage = jsonMemberPackage;
	}

	public String getJsonMemberPackage(){
		return jsonMemberPackage;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	@Override
 	public String toString(){
		return 
			"Model_laporantugas{" + 
			"id = '" + id + '\'' + 
			",id_tugas = '" + idTugas + '\'' + 
			",imei = '" + imei + '\'' + 
			",file = '" + file + '\'' +
					",nama = '" + nama + '\'' +
					",package = '" + jsonMemberPackage + '\'' +
			"}";
		}
}
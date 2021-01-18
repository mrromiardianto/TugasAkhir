package com.example.screening_time.Model;
import com.google.gson.annotations.SerializedName;

public class Item_Jadwal {
	@SerializedName("id")
	private String id;

	@SerializedName("imei")
	private String imei;

	@SerializedName("jammulai")
	private String jammulai;

	@SerializedName("jamakhir")
	private String jamakhir;

	@SerializedName("nama")
	private String nama;

	@SerializedName("package")
	private String jsonMemberPackage;
	@SerializedName("total")
	private String total;

	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setImei(String imei){
		this.imei = imei;
	}
	public String getImei(){
		return imei;
	}
	public void setJammulai(String jammulai){
		this.jammulai = jammulai;
	}
	public String getJammulai(){
		return jammulai;
	}
	public void setJamakhir(String jamakhir){
		this.jamakhir = jamakhir;
	}

	public String getJamakhir(){
		return jamakhir;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setJsonMemberPackage(String jsonMemberPackage){
		this.jsonMemberPackage = jsonMemberPackage;
	}

	public String getJsonMemberPackage(){
		return jsonMemberPackage;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	@Override
 	public String toString(){
		return 
			"Item_Jadwal{" + 
			"id = '" + id + '\'' + 
			",imei = '" + imei + '\'' + 
			",jammulai = '" + jammulai + '\'' + 
			",jamakhir = '" + jamakhir + '\'' + 
			",nama = '" + nama + '\'' + 
			",package = '" + jsonMemberPackage + '\'' +
					",total = '" + total + '\'' +
					"}";
		}
}
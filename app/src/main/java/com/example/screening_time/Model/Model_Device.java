package com.example.screening_time.Model;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

//@Generated("com.robohorse.robopojogenerator")
public class Model_Device implements Serializable {

	@SerializedName("id")
	private String id;

	@SerializedName("imei")
	private String imei;

	@SerializedName("nama")
	private String nama;

	@SerializedName("email")
	private String email;

	@SerializedName("password")
	private String password;

	@SerializedName("role")
	private String role;

	@SerializedName("kata_pengingat")
	private String kataPengingat;

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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setKataPengingat(String kataPengingat){
		this.kataPengingat = kataPengingat;
	}

	public String getKataPengingat(){
		return kataPengingat;
	}

	public void setNama(String Nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	@Override
 	public String toString(){
		return 
			"Model_Device{" + 
			"id = '" + id + '\'' + 
			",imei = '" + imei + '\'' +
					",nama = '" + nama + '\'' +

					",email = '" + email + '\'' +
			",password = '" + password + '\'' + 
			",role = '" + role + '\'' + 
			",kata_pengingat = '" + kataPengingat + '\'' + 
			"}";
		}
}
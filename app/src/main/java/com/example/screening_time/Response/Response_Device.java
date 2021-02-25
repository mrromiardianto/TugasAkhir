package com.example.screening_time.Response;

import java.util.List;
//import javax.annotation.Generated;
import com.example.screening_time.Model.Model_Device;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

//@Generated("com.robohorse.robopojogenerator")
public class Response_Device implements Serializable {

	@SerializedName("status")
	private boolean status;

	@SerializedName("device")
	private List<Model_Device> device;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setDevice(List<Model_Device> device){
		this.device = device;
	}

	public List<Model_Device> getDevice(){
		return device;
	}

	@Override
 	public String toString(){
		return 
			"Response_Device{" + 
			"status = '" + status + '\'' + 
			",device = '" + device + '\'' + 
			"}";
		}
}
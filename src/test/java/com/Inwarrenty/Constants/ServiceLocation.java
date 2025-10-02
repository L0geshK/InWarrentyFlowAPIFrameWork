package com.Inwarrenty.Constants;

public enum ServiceLocation {
	SERVICE_lOCATION_A(0),
	SERVICE_lOCATION_B(1),
	SERVICE_lOCATION_C(2),
	SERVICE_lOCATION_D(3);
	
	int code;
	ServiceLocation(int code){
		this.code=code;
	}
	
	public int getlocationcode() {
		return code;
	}
	

}

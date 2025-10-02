package com.Inwarrenty.Constants;

public enum OemId {
	GOOGLE(1),
	APPLE(2);
	
	
	
	int code;
	OemId(int code){
		this.code=code;
		
		
	}
	
	public int getomeidcode() {
		return code;
	}
}

package com.Inwarrenty.Constants;

public enum WarrentyStatus {
	IN_WARRENTY(1),
	OUT_WARRENTY(2);
	
	
	int code;
	WarrentyStatus(int code){
		this.code=code;
		
	}
	
	
	public int getwarrentcode() {
		return code;
	}
}

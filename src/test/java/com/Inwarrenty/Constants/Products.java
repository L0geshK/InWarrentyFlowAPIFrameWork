package com.Inwarrenty.Constants;

public enum Products {
	NEXUS_2(1),PIXEL(2);

	
	int code;
	private Products(int code) {
		this.code=code;
		
	}
	
	public int getcode() {
		return code;
	}
}

package com.Inwarrenty.Constants;

public enum Models {
	NEXUS_BLUE(1),GALAXIY(2);
	
	int code;
	Models(int code ){
		this.code=code;
		
	}
	
	public int getmodelcode() {
		return code;
	}
	
		

}

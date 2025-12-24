package com.Inwarrenty.Utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtils {
	private static Dotenv dotenv;
	
	static {
		dotenv=Dotenv.load();
	}
	
	private EnvUtils() {
		
	}
	
	public static String getValue(String varName) {
		return dotenv.get(varName);
	}

}

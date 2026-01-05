package com.Inwarrenty.Utils;

import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtils {
	private static Dotenv dotenv;
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(EnvUtils.class);

	
	static {
		log.info("Loading the .env file");
		dotenv=Dotenv.load();
	}
	
	private EnvUtils() {
		
	}
	
	public static String getValue(String varName) {
		log.info("Get the varname {}",varName);
		return dotenv.get(varName);
	}

}

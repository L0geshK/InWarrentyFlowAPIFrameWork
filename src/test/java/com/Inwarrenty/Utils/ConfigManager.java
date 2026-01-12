package com.Inwarrenty.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import io.qameta.allure.Step;

public class ConfigManager {

	private static Properties prop = new Properties();
	private static String path = "Config/config.properties";
	public static String env;
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(ConfigManager.class);

	private ConfigManager() {

	}

	
	static {
		log.info("Enter : the Static block");
		log.info("UserDir Path {} ",System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.dir"));

		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();
		log.info("Running the Test in the Env {} ",env);
		switch (env) {
		case "dev" -> path = "Config/config.dev.properties";

		case "qa" -> path = "Config/config.qa.properties";

		default -> path = "Config/config.qa.properties";
		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		log.info("File path {}",input);
		if (input == null) {
			log.error("File cannot be Null input {}",input);
			throw new RuntimeException("File cannot be Null" + input);
		}
		try {

			prop.load(input);
		} catch (FileNotFoundException e) {
			

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		
		log.info("BaseURl url {}",prop.getProperty("BASE_URL"));

	}

//	@Step("Getting the Property Value from 	the Config File")
//	public static String getProperty(String Key) throws IOException {
//
//		return prop.getProperty(Key);
//
//	}
	@Step("Getting the Property Value from the Config File")
	public static String getProperty(String key) {

	    try {
	        return prop.getProperty(key);
	    } catch (Exception e) {
	    	log.error("Failed to read property value for key {}",key);
	        throw new RuntimeException(
	                "Failed to read property value for key: " + key, e);
	    }
	}

}

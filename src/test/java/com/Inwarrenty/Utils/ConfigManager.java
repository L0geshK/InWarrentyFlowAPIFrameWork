package com.Inwarrenty.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class ConfigManager {

	private static Properties prop = new Properties();
	private static String path = "Config/config.properties";
	private static String env;

	private ConfigManager() {

	}

	static {
		System.out.println(System.getProperty("user.dir"));

		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();
		switch (env) {
		case "dev" -> path = "Config/config.dev.properties";

		case "qa" -> path = "Config/config.qa.properties";

		default -> path = "Config/config.qa.properties";
		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (input == null) {
			throw new RuntimeException("File cannot be Null" + input);
		}
		try {

			prop.load(input);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println(prop.getProperty("BASE_URL"));

	}

	public static String getProperty(String Key) throws IOException {

		return prop.getProperty(Key);

	}

}

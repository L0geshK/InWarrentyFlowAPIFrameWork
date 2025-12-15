package com.Inwarrenty.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Inwarrenty.Utils.ConfigManager;

public class DataBaseManager {
	private static String DB_URL;
	private static String DB_USERNAME;
	private static String DB_PASSWORD;
	private volatile static Connection conn;

	private DataBaseManager() {

	}

	static {
		try {
			DB_URL = ConfigManager.getProperty("DB_URL");
			DB_USERNAME = ConfigManager.getProperty("DB_USERNAME");
			DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void CreateConnection() {
		try {
			if (conn == null) {
				synchronized (DataBaseManager.class) {
					if (conn == null) {
						conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
						System.out.println(conn);

					}

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

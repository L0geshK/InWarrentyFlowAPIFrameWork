package com.Inwarrenty.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Inwarrenty.Utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBaseManagerWithHikariCP {
	private static String DB_URL;
	private static String DB_USERNAME;
	private static String DB_PASSWORD;
	private static int MAXIMUN_POOL_SIZE;
	private static int MINIMUM_IDLE_COUNT;
	private static int CONNECTION_TIMEOUT_IN_SEC;
	private static int IDLE_TIMEOUT_SEC;
	private static int MAX_LIFE_TIME_IN_MINS;
	private static String HIKARI_CP_POOL_NAME;
	private volatile static HikariDataSource ds;
	private static Connection conn;

	private DataBaseManagerWithHikariCP() {

	}

	static {
		try {
			DB_URL = ConfigManager.getProperty("DB_URL");
			DB_USERNAME = ConfigManager.getProperty("DB_USERNAME");
			DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
			MAXIMUN_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAXIMUN_POOL_SIZE"));
			MINIMUM_IDLE_COUNT = Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));
			CONNECTION_TIMEOUT_IN_SEC = Integer.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SEC"));
			IDLE_TIMEOUT_SEC = Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_SEC"));
			MAX_LIFE_TIME_IN_MINS = Integer.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MINS"));
			HIKARI_CP_POOL_NAME = ConfigManager.getProperty("HIKARI_CP_POOL_NAME");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initializepool() {
		if (ds == null) {
			synchronized (DataBaseManagerWithHikariCP.class) {
				if (ds == null) {
					HikariConfig hikarconfig = new HikariConfig();
					hikarconfig.setJdbcUrl(DB_URL);
					hikarconfig.setUsername(DB_USERNAME);
					hikarconfig.setPassword(DB_PASSWORD);
					hikarconfig.setMaximumPoolSize(MAXIMUN_POOL_SIZE);
					hikarconfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikarconfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SEC * 1000);
					hikarconfig.setIdleTimeout(IDLE_TIMEOUT_SEC * 1000);
					hikarconfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS * 60 * 1000);
					hikarconfig.setPoolName(HIKARI_CP_POOL_NAME);

					ds = new HikariDataSource(hikarconfig);

				}

			}

		}

	}

	public static Connection getConnecction() throws SQLException {
		conn = null;
		if (ds == null) {
			initializepool();

		} else if (ds.isClosed()) {
			throw new SQLException("Hikari Connection is closed");
		}

		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

}

package com.Inwarrenty.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Inwarrenty.Utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPConn {
	public static void main(String[] args) {
		HikariConfig hikarconfig = new HikariConfig();
		HikariDataSource ds = null;
		Connection conn = null;
		Statement statement=null;
		
		try {
			hikarconfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
			hikarconfig.setUsername(ConfigManager.getProperty("DB_USERNAME"));
			hikarconfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
			hikarconfig.setMaximumPoolSize(10);
			hikarconfig.setMinimumIdle(2);
			hikarconfig.setConnectionTimeout(10000);
			hikarconfig.setIdleTimeout(10000);
			hikarconfig.setMaxLifetime(1800000);
			hikarconfig.setPoolName("Inwarrenty FlowAPI Testing Automaction!!!");
			
			ds = new HikariDataSource(hikarconfig);
			conn = ds.getConnection();
			statement = conn.createStatement();
			ResultSet rs=statement.executeQuery("select first_name, last_name ,mobile_number from tr_customer;");
			while(rs.next()) {
				System.out.println(rs.getString("first_name")+" "+rs.getString("last_name")+" "+rs.getString("mobile_number"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ds.close();
		}
		
	}

}

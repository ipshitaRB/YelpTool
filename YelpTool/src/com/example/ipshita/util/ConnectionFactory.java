package com.example.ipshita.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	String driverClassName = "oracle.jdbc.driver.OracleDriver";
	String host = "localhost";
	String dbName = "orcl";
	int port = 1521;
	String oracleURL = "jdbc:oracle:thin:@" + host + ":" + port + "/" + dbName;
	String dbUser = "ipshita";
	String dbPwd = "ipshita123";

	private static ConnectionFactory connectionFactory = null;

	private ConnectionFactory() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(oracleURL, dbUser, dbPwd);
		return conn;
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}

}

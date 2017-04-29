package com.app.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {

	protected static Connection cx;

	public static Connection conectar() {
		if (cx != null) {
			return cx;
		}

		InputStream inputStream = Conexion.class.getClassLoader().getResourceAsStream("db.properties");
		
		Properties properties = new Properties();
		
		String driver;
        String url;
        String user;
        String password;
		
		if (inputStream != null) {
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("No se pudo leer el archivo db.properties");
			}
			driver = properties.getProperty("DRIVER");
            url = properties.getProperty("DBURL");
            user = properties.getProperty("USER");
            password = properties.getProperty("PASSWORD");
		} else {
			driver = System.getenv("DRIVER");
            url = System.getenv("DBURL");
            user = System.getenv("USER");
            password = System.getenv("PASSWORD");
		}
		
			try {
				Class.forName(driver);
				cx = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("No se pudo conectar a la base de datos");
			}

		return cx;
	}

}

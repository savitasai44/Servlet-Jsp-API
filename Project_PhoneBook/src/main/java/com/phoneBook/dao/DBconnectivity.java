package com.phoneBook.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnectivity {
static Connection c;
	public static Connection conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		 c=DriverManager.getConnection("jdbc:mysql://localhost:3360/phonebook","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
		
	}
}

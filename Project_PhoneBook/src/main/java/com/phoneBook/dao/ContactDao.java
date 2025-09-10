package com.phoneBook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.phoneBook.model.Contact;

public class ContactDao {
public static List<Contact> getAll() {
	List <Contact> list=new ArrayList<>();
	Connection c=DBconnectivity.conn();
	if(c==null) {
		System.out.println("fail connection");
	}
	try {
		Statement ps=c.createStatement();
		ResultSet rs=ps.executeQuery("select * from contact");
          	
		while(rs.next()) {
			Contact c1=new Contact();
			c1.setId(rs.getInt("id")) ;
			c1.setName(rs.getString("name"));
			c1.setPhone(rs.getLong("phone"));
			c1.setEmail(rs.getString("email"));
			c1.setDob(rs.getDate("dob"));
			list.add(c1);
		}
	} catch (Exception e) {
				e.printStackTrace();
	}
	return list;
}

public static Boolean insertContact(Contact c1) {
	Connection c=DBconnectivity.conn();
	try {
		PreparedStatement ps= c.prepareStatement("insert into contact(name, phone, email, dob) values(?,?,?,?)");
	    ps.setString(1, c1.getName() );
	    ps.setLong(2, c1.getPhone());
	    ps.setString(3, c1.getEmail());
	    if (c1.getDob() != null) {
	        ps.setDate(4, c1.getDob());
	    } else {
	        ps.setNull(4, java.sql.Types.DATE);
	    }
	    ps.executeUpdate();
	    return true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}

public static Contact getContactById(int id) {
    Contact c1 = null;
    Connection c=DBconnectivity.conn();
    
	try {
		PreparedStatement 	ps = c.prepareStatement("SELECT * FROM contact WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            c1 = new Contact();
            c1.setId(rs.getInt("id"));
            c1.setName(rs.getString("name"));
            c1.setPhone(rs.getLong("phone"));
            c1.setEmail(rs.getString("email"));
            c1.setDob(rs.getDate("dob"));
}
	}     catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
return c1;
}

public static boolean updateContact(Contact c1) {
	Connection c=DBconnectivity.conn();
try {
	PreparedStatement ps= c.prepareStatement("update contact set name=?, phone=?, email=?, dob=? where id=?");
	ps.setString(1, c1.getName() );
    ps.setLong(2, c1.getPhone());
    ps.setString(3, c1.getEmail());
    if (c1.getDob() != null) {
        ps.setDate(4, c1.getDob());
    } else {
        ps.setNull(4, java.sql.Types.DATE);
    }
    ps.setInt(5, c1.getId());
    return ps.executeUpdate()>0;  
    
}
catch (SQLException e) {
	e.printStackTrace();
}
return false;
}

public static boolean deleteContact(int id) {

	Connection c=DBconnectivity.conn();
	try {
		PreparedStatement ps=c.prepareStatement("delete from contact where id=?");
	ps.setInt(1, id);
	return ps.executeUpdate()>0;
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return false;
}
}

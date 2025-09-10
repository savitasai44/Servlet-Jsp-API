package com.phoneBook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import org.apache.catalina.connector.Response;

import com.phoneBook.dao.ContactDao;
import com.phoneBook.model.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddContactServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Contact c1=new Contact();
		c1.setName(req.getParameter("name"));
		long p=Long.parseLong((req.getParameter("phone")));
		c1.setPhone(p);
		c1.setEmail(req.getParameter("email"));
		String dobStr = req.getParameter("dob"); // Should be yyyy-MM-dd
		Date dob = null;

		try {
		    if (dobStr != null && !dobStr.isEmpty()) {
		        dob = Date.valueOf(dobStr); // Works only for yyyy-MM-dd
		        c1.setDob(dob);
		    }
		} catch (IllegalArgumentException e) {
		    System.out.println("Invalid date format received: " + dobStr);
		    e.printStackTrace();
		}
		ContactDao.insertContact(c1);
		resp.sendRedirect("dashboard.jsp");
	
	}
}

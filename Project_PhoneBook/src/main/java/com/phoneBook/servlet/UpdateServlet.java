package com.phoneBook.servlet;

import java.io.IOException;
import java.sql.Date;

import com.phoneBook.dao.ContactDao;
import com.phoneBook.model.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class UpdateServlet extends HttpServlet {
	 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        Contact c1 = new Contact();
	        c1.setId(Integer.parseInt(req.getParameter("id")));
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

	        ContactDao.updateContact(c1);
	        res.sendRedirect("dashboard.jsp");
	    }
}

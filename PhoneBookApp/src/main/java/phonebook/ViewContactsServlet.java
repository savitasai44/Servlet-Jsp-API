package phonebook;


import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

@WebServlet("/view")
public class ViewContactsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contacts");

           // out.println("<h2>Contact List</h2>");
            out.println("<div class='container mt-5'>");
            out.println("<h2>Contact List</h2>");
            out.println("<table class='table table-striped table-bordered'>");
            
            out.println("<br><a href='index.jsp'>Search Result</a>");
            
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Phone</th><th>Email</th><th>Delete</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("phone") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
            //    out.println("<td><a href='delete?id=" + rs.getInt("id") + "'>Delete</a></td>");
                out.println("<td><a href='edit?id=" + rs.getInt("id") + "'>Edit</a> | " +
                        "<a href='delete?id=" + rs.getInt("id") + "'>Delete</a></td>");

                out.println("</tr>");
            }

           // out.println("</table>");
           // out.println("<br><a href='index.html'>Add New Contact</a>");
            out.println("</table>");
            out.println("<a href='index.html' class='btn btn-success'>Add New Contact</a>");
            out.println("</div>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


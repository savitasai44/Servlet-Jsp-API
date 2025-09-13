package phonebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class UpdateContactServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM contacts WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
             
                out.println("<h2>Edit Contact</h2>");
                out.println("<div class='container mt-5'>");
                out.println("<form action='edit' method='post' class='card p-4 shadow'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("Name: <input name='name' value='" + rs.getString("name") + "'><br>");
                out.println("Phone: <input name='phone' value='" + rs.getString("phone") + "'><br>");
                out.println("Email: <input name='email' value='" + rs.getString("email") + "'><br>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE contacts SET name=?, phone=?, email=? WHERE id=?");
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setInt(4, id);
            ps.executeUpdate();
            response.sendRedirect("view");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

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

@WebServlet("/search")
public class SearchContactServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        String keyword = request.getParameter("keyword");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM contacts WHERE name LIKE ?");
            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            out.println("<h2>Search Results</h2><table border='1'>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("name") + "</td><td>" + rs.getString("phone") +
                        "</td><td>" + rs.getString("email") + "</td></tr>");
            }
            out.println("</table><a href='index.html'>Back</a>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

<%@ page import="com.phoneBook.dao.ContactDao, com.phoneBook.model.Contact" %>
<%
    String idStr = request.getParameter("id");
    Contact c = null;
    if (idStr != null && !idStr.isEmpty()) {
        try {
            int id = Integer.parseInt(idStr);
            c = ContactDao.getContactById(id);
        } catch (NumberFormatException e) {
            out.println("<p style='color:red;'>Invalid ID format.</p>");
        }
    } else {
        out.println("<p style='color:red;'>Missing ID in URL.</p>");
    }
%><!DOCTYPE html>
<html>
<head>
    <title>Edit Contact</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
</head>
<body class="container mt-5">
    <h2>Edit Contact</h2>
    <% if (c != null) { %>
    <form method="post" action="edit">
        <input type="hidden" name="id" value="<%=c.getId()%>">
        <input type="text" name="name" class="form-control mb-2" value="<%=c.getName()%>" required>
        <input type="text" name="phone" class="form-control mb-2" value="<%=c.getPhone()%>" required>
        <input type="email" name="email" class="form-control mb-2" value="<%=c.getEmail()%>" required>
        <input type="date" name="dob" class="form-control mb-2" value="<%=c.getDob()%>" required>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
    <% } else { %>
    <p class="text-danger">Contact not found or invalid ID. Please go back and try again.</p>
<% } %>
</body>
</html>

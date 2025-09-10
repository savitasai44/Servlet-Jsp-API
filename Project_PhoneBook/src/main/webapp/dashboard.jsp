<%@ 
page
	import="java.util.List, java.text.SimpleDateFormat, com.phoneBook.dao.ContactDao, com.phoneBook.model.Contact"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PhoneBook Dashboard</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
</head>
<body class="container mt-5">
	<h2>PhoneBook</h2>
	<a href="add.jsp" class="btn btn-success mb-3 add">Add Contact</a>
	<table class="table table-bordered text-center">
		<thead>
			<tr>
				<th>Name
				</th>
				<th>Phone
				</th>
				<th>Email
				</th>
				<th>DOB
				</th>
				<th>Action
				</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Contact> list = ContactDao.getAll();
			for (Contact c : list) {
			%>
			<tr>
				<td><%=c.getName()%></td>
				<td><%=c.getPhone()%></td>
				<td><%=c.getEmail()%></td>
				<%
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String formattedDob = c.getDob() != null ? sdf.format(c.getDob()) : "";
%>
				<td><%= formattedDob %></td>
				<td>
				<div class="d-flex gap-2">
					
				<a href="edit.jsp?id=<%=c.getId()%>" class="btn btn-warning btn-sm w-50">Edit</a>
					 <a href="delete?id=<%=c.getId()%>" class="btn btn-danger btn-sm w-50" >Delete</a>
				</div>
				</td>
			</tr>
			<%
			}
			%>


		</tbody>
	</table>
</body>
</html>
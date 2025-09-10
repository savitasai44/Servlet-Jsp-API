<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add contact</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">

</head>
<body>
<h2>Add New Contact</h2>
<form action ="add" method="post" class="col-md-4 mx-auto">
<input type="text" placeholder="Name" name="name"  class="form-control mb-2" required>
<input type="tel" placeholder="Phone" name="phone"  class="form-control mb-2" required>
<input type="email" placeholder="Email" name="email"  class="form-control mb-2" required>
<input type="date" placeholder="DOB" name="dob"  class="form-control mb-2" required>
<button type="submit" class="btn btn-success w-100">Save</button>


</form>
</body>
</html>
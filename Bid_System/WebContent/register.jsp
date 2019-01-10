<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
<%@ include file='header.jsp' %>
  <br />
  <br />
  <br />
  <br />
  <div class="col-md-8 order-md-1">
	<h3 align="center">Register</h3>
  
  <form action="RegisterServlet" method="post">
  <div class="container" class="mb-3">
    <h1>Sign Up</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
	
	<label for="name"><b>Name</b></label>
    <input type="text" class="form-control" placeholder="Enter Full Name" name="name" required>
	<br />
	
	<label for="phone"><b>Phone</b></label>
    <input type="text" class="form-control" placeholder="Enter Phone Number" name="phone" required>
	<br />
	
	<label for="address"><b>Address</b></label>
    <input type="text" class="form-control" placeholder="Enter Address" name="address" required>
	<br />
	
    <label for="email"><b>Email</b></label>
    <input type="text" class="form-control" placeholder="Enter Email" name="email" required>
	<br />
	
	<label for="username"><b>Username</b></label>
    <input type="text" class="form-control" placeholder="Enter Username" name="username" required>
	<br />
	
    <label for="psw"><b>Password</b></label>
    <input type="password" class="form-control" placeholder="Enter Password" name="psw" required>
	<br />
    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" class="form-control" placeholder="Repeat Password" name="psw-repeat" required>
	<br />
    <div class="mb-4">
      <button type="button" class="cancelbtn">Cancel</button>
      <button type="submit" class="signupbtn">Sign Up</button>
    </div>
  </div>
</form>
      </div>
      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
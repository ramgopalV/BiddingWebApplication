<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile Information</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- <script type="text/javascript"
		src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			alert("Pass 11");
			
			$.ajax({
				url : "FetchProfileServlet343",
				type : "GET",
				data: {value:option_value},
				success : function(data) {
					alert("Pass");
					console.log(data);
					/*var json = jQuery.parseJSON(data);
					$('#name').html = data.name;
					$('#phone').html = data.phone;
					$('#address').html = data.address;
					$('#email').html = data.email;
					$('#username').html = data.USERNAME;*/
				},
				error : function(data) {
					alert("Fail");
				}
			});
		});
	</script> -->
</head>
<body>
	<%@ include file='header.jsp' %>
	<br />
	<br />
	<br />
	<br />
	<div class="col-md-8 order-md-1">
		<h3 align="center">Profile Information</h3>

		<form action="UpdateProfileServlet" method="post">
			<div class="container" class="mb-3">
				<hr>

				<label for="name"><b>Name</b></label> <input type="text"
					class="form-control" name="name"  id="text_name" required> <br /> 
					<label
					for="phone"><b>Phone</b></label> 
					<input type="text"
					class="form-control" name="phone" id="text_phone" required> <br /> <label
					for="address"><b>Address</b></label> 
					<input type="text"
					class="form-control" name="address" id="text_address" required> <br /> <label
					for="email"><b>Email</b></label> 
					<input type="text"
					class="form-control" name="email" id="text_email" required> <br /> <label
					for="username"><b>Username</b></label> <input type="text"
					class="form-control" id="text_username" name="username" required> <br /> <br />
				<div class="mb-4">
					<button type="reset" class="cancelbtn">Cancel</button>
					<button type="submit" class="signupbtn">Save</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var data = xhr.responseText;
				var jsonArray = eval ("(" + data + ")");
				var jsonObject = jsonArray[0];
								
				document.getElementById("text_name").value = jsonObject.name;
				document.getElementById("text_phone").value = jsonObject.phone;
				document.getElementById("text_address").value = jsonObject.address;
				document.getElementById("text_email").value = jsonObject.email;
				document.getElementById("text_username").value = jsonObject.USERNAME;
			}
		}
		xhr.open('GET', 'FetchProfileServlet', true);
		xhr.send(null);
	</script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
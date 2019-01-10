<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Post Item</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css"
	rel="Stylesheet" type="text/css" />
</head>
<body>
	<%@ include file='header.jsp' %>
	<br />
	<br />
	<br />
	<br />
	<div class="col-md-7 order-md-1">
		<br /> <br />
		<h3 class="mb-3" align="center">Post Item</h3>
		<form class="needs-validation" name="PostItem"
			enctype="multipart/form-data" action="postItem" method="post"
			novalidate>

			<div class="mb-3">
				<label for="item">Item Title</label> <input type="text"
					class="form-control" name="item" placeholder="Wall Clock" required>
				<div class="invalid-feedback">Please enter item title.</div>
			</div>

			<div class="mb-3">
				<label for="description">Item Description</label>
				<textarea class="form-control" name="description" rows="5" cols="70"
					placeholder="" required></textarea>
				<div class="invalid-feedback">Please enter item description.</div>
			</div>

			<div class="mb-3">
				<label for="price">Initial Bidding Price</label> <input type="text"
					class="form-control" name="price" placeholder="$20" required>
				<div class="invalid-feedback">Please enter item
					initial/minimum bidding price.</div>
			</div>

			<div class="row">
				<div class="col-md-3 mb-3">
					<label for="available-date">Date</label> <input type="text"
						class="form-control" name="available-date" id="available-date"
						placeholder="2018-09-04" required>
					<div class="invalid-feedback">Please enter date.</div>
				</div>
				<div class="col-md-3 mb-3">
					<label for="available-time">Time</label>
					<!--   <input type="text"
						class="form-control" name="available-time" id="available-time" placeholder="10:00:00"
						required>-->
					<select id="available-time" name="available-time"></select>
					<div class="invalid-feedback">Please enter time.</div>
				</div>
			</div>

			<div class="mb-3">
				<label for="file-upload">Upload Item Photo</label> <input
					type="file" class="form-control" name="file-upload" placeholder=""
					required>
				<div class="invalid-feedback">Please upload item photo.</div>
			</div>
			<hr class="mb-4">
			<br />
			<button class="btn btn-primary btn-lg" type="reset">Reset</button>
			<button class="btn btn-primary btn-lg" type="submit">Submit</button>
		</form>
	</div>
	</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"
		type="text/javascript"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"
		type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			$("#available-date").datepicker({
			    onSelect: function(date) {
			    	var params = 'date=' + date;
			    	var xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						if (xhr.readyState == 4) {
							var data = xhr.responseText;
					    		var arr = data.split(",");
					    		arr.sort();
					    		var text;
					    		var i;
					    		for(i=0; i<arr.length; i++){ 
					    			$("#available-time").append('<option value ="' + arr[i] + '">' + arr[i] + '</option>');
					    		}	    	
						}
					}
					xhr.open('POST', 'FetchSlotsServlet', true);
					xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					xhr.send(params);
			    }
			});
		});
	</script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
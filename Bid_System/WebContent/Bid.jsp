<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bid Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
	<%@ include file='header.jsp'%>
	<br />
	<br />
	<br />
	<br />

	<div>
		<h3 align="center">Bid Information</h3>
		<br /> <br />
		<form action="postBidServlet" method="post">
			<fieldset>
				<div class="row col-md-12">
					<div class="column col-md-3">
						<label for="itemTitle"><b>Item Title</b></label> <input
							type="text" name="text_itemTitle" id="text_itemTitle" required
							readonly>
					</div>
					<br />

					<div class="column col-md-3">

						<label for="itemDesc"><b>Item Desc</b></label> <input type="text"
							name="text_itemdesc" id="text_itemdesc" required readonly>
					</div>
					<br />
					<div class="column col-md-3">
						<label for="itemPrice"><b>Initial Bid</b></label> <input
							type="text" name="text_itemPrice" id="text_itemPrice" required
							readonly>
					</div>
					<br />

					<div class="column col-md-3">
						<label for="bidValue"><b>Bid Value</b></label> <input type="text"
							name="text_bidValue" id="text_bidValue" required>
					</div>

				</div>
				<br /> <br /> <br />
				<div>
					<button type="reset" class="cancelbtn">Cancel</button>
					<button type="submit" class="signupbtn">Submit Bid</button>
				</div>
			</fieldset>
		</form>
	</div>
	<br />
	<br />

	<h3 align="center">Search All Bids</h3>
	<div>
		<br /> <br />
		<div class="row col-md-12">
			<div class="column col-md-3">
				<label for="itemName">Item Name</label> <input type="text"
					class="form-control" name="itemName" id="itemName" required>
				<div class="invalid-feedback">Please enter item name.</div>
			</div>

			<div class="column col-md-3">
				<br /> <br />
				<button type="submit" onclick="fetchBidFunction()">Submit</button>
			</div>
		</div>
		<br />
		<hr class="mb-4">
		<br />

	</div>
	<div class="table-responsive container">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>#</th>
					<th>Item Title</th>
					<th>username</th>
					<th>biddingPrice</th>
				</tr>
			</thead>
			<tbody id="bid_body">
			</tbody>
		</table>
	</div>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		setInterval(
				function() {
					var minutes = (new Date()).getMinutes()
					if (minutes % 15 == 0) {
						window.location.href = "https://localhost:8443/Bid_System/Bid.jsp";
					}
				}, 60000);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var data = xhr.responseText;
				var jsonArray = eval("(" + data + ")");
				var jsonObject = jsonArray[0];

				if (data != '[{}]') {
					document.getElementById("text_itemTitle").value = jsonObject.itemTitle;
					document.getElementById("text_itemdesc").value = jsonObject.itemDesc;
					document.getElementById("text_itemPrice").value = jsonObject.initialBid;
					document.getElementById("text_bidValue").value = jsonObject.biddingPrice;
				} else {
					document.getElementById("text_itemTitle").value = "";
					document.getElementById("text_itemdesc").value = "";
					document.getElementById("text_itemPrice").value = "";
					document.getElementById("text_bidValue").value = "";
				}
			}
		}
		xhr.open('GET', 'FetchBidServlet', true);
		xhr.send(null);
	</script>
	<script>
		function fetchBidFunction() {

			var itemName = "itemName=" + $("#itemName").val();

			var xhr_bid = new XMLHttpRequest();
			xhr_bid.onreadystatechange = function() {
				if (xhr_bid.readyState == 4) {
					var data = xhr_bid.responseText;
					var jsonArray = JSON.parse(data);
					var str = bid_body.innerHTML;

					for (var i = 0; i < jsonArray.length; i++) {
						var jsonObject = JSON.parse(jsonArray[i]);

						if (data != '[{}]') {
							str = str + "<tr>";
							str = str + "<td>" + (i + 1) + "</td><td>"
									+ jsonObject.itemName + "</td> <td>"
									+ jsonObject.username + "</td> <td>"
									+ jsonObject.biddingPrice + "</td>";
							str = str + "</tr>";
						}
					}
					bid_body.innerHTML = str;
				}
			}
			xhr_bid.open('POST', 'SearchAllUserBidServlet', true);
			xhr_bid.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhr_bid.send(itemName);
		}
	</script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
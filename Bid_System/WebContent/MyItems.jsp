<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Items</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"</script>
<script>
	function callServlet() {
		var itemTitle = $('tr').find('input[type=radio]:checked').closest('tr')
				.find('td:eq(0)').text();
		var itemDesc = $('tr').find('input[type=radio]:checked').closest('tr')
				.find('td:eq(1)').text();
		var auctionDate = $('tr').find('input[type=radio]:checked').closest(
				'tr').find('td:eq(2)').text();
		var auctionTime = $('tr').find('input[type=radio]:checked').closest(
				'tr').find('td:eq(3)').text();

		var params = "itemTitle=" + itemTitle + "&itemDesc=" + itemDesc
				+ "&auctionDate=" + auctionDate + "&auctionTime=" + auctionTime;

		var http = new XMLHttpRequest();
		http.open("POST", "DeleteItemServlet", true);
		http.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		http.send(params);
	}
</script>

</head>
<body>
	<%@ include file='header.jsp'%>
	<br />
	<br />
	<br />
	<br />

	<div>
		<h3 align="center">My Items</h3>
		<br /> <br />
		<form name="DeleteItem" method="post">
			<div class="table-responsive container">
				<table class="table" id="itemTable">
					<thead class="thead-dark">
						<tr>
							<th>Item Title</th>
							<th>Item Desc</th>
							<th>Auction Date</th>
							<th>Auction Time</th>
							<th>Item Bid Price</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody id="table_body">
					</tbody>
				</table>
				<button class="btn btn-primary btn-lg" type="submit"
					onclick="callServlet()">Delete</button>
			</div>
		</form>
	</div>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var jsonArray = JSON.parse(this.responseText);
				var str = "";
				
				/* var date = new Date();
				var d = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate(); */
				
				for (var i = 0; i < jsonArray.length; i++) {
					var jsonObject = JSON.parse(jsonArray[i]);
					str = str + "<tr>";
					str = str + "<td>" + jsonObject.itemTitle + "</td> <td>"
							+ jsonObject.itemDesc + "</td> <td>"
							+ jsonObject.auctionDate + "</td> <td>"
							+ jsonObject.auctionTime + "</td> <td>"
							+ jsonObject.biddingPrice + "</td>";
					
					
					str = str
							+ "<td><input type='radio' name='deleteItem' value='true'> </td>";
					str = str + "</tr>";
				}
				table_body.innerHTML = str;
			}
		}
		xhr.open('GET', 'FetchMyItemsServlet', true);
		xhr.send(null);
	</script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
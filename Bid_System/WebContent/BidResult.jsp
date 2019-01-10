<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bid Summary</title>
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
		<h3 align="center">Bid Summary</h3>
		<br /> <br />

		<div class="table-responsive container">
			<table class="table tablesorter search-table">
				<thead class="thead-dark">
					<tr>
						<th>#</th>
						<th>Owner User</th>
						<th>Item Title</th>
						<th>Item Desc</th>
						<th>Initial Bid</th>
						<th>Winner User</th>
						<th>Final Bid</th>
					</tr>
				</thead>
				<tbody id="summary_body">
				</tbody>
			</table>
		</div>

	</div>
	<br />
	<br />

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="html-table-search.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('table.search-table').tableSearch({
				searchText : 'Search',
				searchPlaceHolder : 'Input Value'
			});
		});
	</script>
	<script>
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				var jsonArray = JSON.parse(this.responseText);
				var str = "";

				for (var i = 0; i < jsonArray.length; i++) {

					var jsonObject = JSON.parse(JSON.stringify(jsonArray[i]));

					str = str + "<tr>";
					str = str + "<td>" + (i + 1) + "</td><td>"
							+ jsonObject.owner + "</td> <td>"
							+ jsonObject.itemTitle + "</td> <td>"
							+ jsonObject.itemDesc + "</td> <td>"
							+ jsonObject.initialbid + "</td> <td>"
							+ jsonObject.winner + "</td> <td>"
							+ jsonObject.finalbid + "</td>";
					str = str + "</tr>";
				}
				summary_body.innerHTML = str;
				sortFunction();
			}
		}
		xhr.open('GET', 'BidResultServlet', true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhr.send(null);
	</script>
	<script src="jquery.tablesorter.min.js"></script>
	<script src="jquery.tablesorter.widgets.min.js"></script>

	<script>
		function sortFunction() {
			$('table').tablesorter();
		};
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Item</title>
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

	<div class="col-md-7 order-md-1">
		<h3 align="center">Search Item</h3>
		<br /> <br />
		<div class="row">
			<div class="col-md-3 mb-3">
				<label for="start-date">Start Date</label> <input type="date"
					class="form-control" name="start-date" id="start-date"
					placeholder="yyyy-mm-dd" required>
				<div class="invalid-feedback">Please enter date.</div>
			</div>

			<div class="col-md-3 mb-3">
				<label for="end-date">End Date</label> <input type="date"
					class="form-control" name="end-date" id="end-date"
					placeholder="yyyy-mm-dd" required>
				<div class="invalid-feedback">Please enter date.</div>
			</div>

			<div class="col-md-2 mb-2">
				<button class="btn btn-primary btn-lg" type="submit"
					onclick="myFunction()">Submit</button>
			</div>
		</div>

		<hr class="mb-4">
		<br />

	</div>
	<div class="table-responsive container">
		<table class="table tablesorter search-table">
			<thead class="thead-dark">
				<tr>
					<th>#</th>
					<th>Item Title</th>
					<th>Item Desc</th>
					<th>Auction Date</th>
					<th>Auction Time</th>
					<th>Item Bid Price</th>
				</tr>
			</thead>
			<tbody id="table_body">
			</tbody>
		</table>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="html-table-search.js"></script>
	<script type="text/javascript">
      $(document).ready(function(){
        $('table.search-table').tableSearch({
          searchText:'Search',
          searchPlaceHolder:'Input Value'
        });
      });
    </script>
	<script>
		function myFunction() {
			var data = "startDate=" + $("#start-date").val() + "&endDate=" + $("#end-date").val();
			
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					var jsonArray = JSON.parse(this.responseText);
					var str = table_body.innerHTML;
					
					for(var i=0; i<jsonArray.length; i++){
						var jsonObject = JSON.parse(jsonArray[i]);
						str = str + "<tr>";
						str = str + "<td>" + (i+1) + "</td><td>" + jsonObject.itemTitle + "</td> <td>" + jsonObject.itemDesc + "</td> <td>" + jsonObject.auctionDate + "</td> <td>" + jsonObject.auctionTime + "</td> <td>" + jsonObject.biddingPrice + "</td>";
						str = str + "</tr>";
					}
					table_body.innerHTML = str;
					sortFunction();
				}
			}
			xhr.open('POST', 'SearchItemServlet', true);
			xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhr.send(data);
		}
	</script>
	<script src="jquery.tablesorter.min.js"></script>
	<script src="jquery.tablesorter.widgets.min.js"></script>
	
	<script>
  		function sortFunction(){
    			$('table').tablesorter();
		};
  </script>
</body>
</html>
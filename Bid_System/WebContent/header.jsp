<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Header</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
	<header> <nav
		class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarCollapse" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="welcome-page.jsp">Home <span class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a class="nav-link" href="profileInfo.jsp">Profile
					Info</a></li>
			<li class="nav-item"><a class="nav-link" href="Bid.jsp">Bid</a></li>
			<li class="nav-item"><a class="nav-link"
				href="SearchItem.jsp">Search Item</a></li>
			<li class="nav-item"><a class="nav-link" href="postItem.jsp">Post
					Item</a></li>
			<li class="nav-item"><a class="nav-link" href="MyItems.jsp">My
					Item</a></li>
		</ul>
		<a href='InvalidateSessionServlet'>Logout</a>
	</div>
	</nav> </header>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<link rel="icon" href="/PhimLor/img/favicon.png">

<!-- CDN Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.4.js"
	integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="/PhimLor/css/style.css">
<script src="/PhimLor/js/controller.js"></script>
</head>

<body>
	<!-- Header -->
	<c:if test="${empty sessionScope.user.id}">
		<jsp:include page="headerUser.jsp"></jsp:include>
	</c:if>
	<c:if
		test="${sessionScope.user.admin ==true && !empty sessionScope.user.id}">
		<jsp:include page="header.jsp"></jsp:include>
	</c:if>
	<c:if
		test="${sessionScope.user.admin ==false && !empty sessionScope.user.id}">
		<jsp:include page="headerUser.jsp"></jsp:include>
	</c:if>

	<div class="container" style="margin-top: 5rem;">
		<div class="row">
			<c:forEach var="item" items="${videos}">
				<div class="col-12 col-md-4">
					<a href="/PhimLor/xemVideo/xem/${item.id}" style="color: black">
						<div class="panel panel-heading text-center">
							<img
								src="https://img.youtube.com/vi/${item.poster}/maxresdefault.jpg" width="95%">
						</div>
						<div class="panel panel-title" style="padding-top: 5px; padding-left: 25px; padding-top: 15px">
							<h6 data-bs-toggle="tooltip"
								data-bs-placement="bottom" title="${item.title}"
								style="line-height: 25px; max-height: 55px; text-overflow: ellipsis; overflow: hidden;"><b>${item.title}</b></h6>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
	</div>

	<br>
	<br>
	<ul class="pagination justify-content-center" style="z-index: -99999;">
		<c:forEach begin="1" end="${listPages}" var="i">
			<li class="page-item"><a class="page-link"
				href="/PhimLor/index/page/${i}">${i}</a></li>
		</c:forEach>
	</ul>

	<br>
	<br>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
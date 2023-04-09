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
 <style>
        * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.dialog {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 10;
    display: flex;
    align-items: center;
    justify-content: center;
    visibility: hidden;
    opacity: 0;
    transition: opacity linear 0.2s;
}

.overlay-close {
    position: absolute;
    width: 100%;
    height: 100%;
    cursor: default;
}

.dialog:target {
    visibility: visible;
    opacity: 1;
}


.overlay {
    background-color: rgba(0, 0, 0, 0.3);
}

.dialog-body {
   	width: 620px;
    height: 270px;
    position: relative;
    padding: 16px;
    background-color: #fff;
}

.dialog-close-btn {
    position: absolute;
    top: 2px;
    right: 6px;
    text-decoration: none;
    color: #333;
}
    </style>
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

	<div class="container" style="margin-top: 7rem;">
		<div class="row">
			<center>
				<div class="col-12">
					<iframe width="100%" height="600px"
						src="https://www.youtube.com/embed/${item.id}?rel=0&autoplay=1"
						title="YouTube video player" frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
						allowfullscreen></iframe>
				</div>
			</center>
			<div class="panel-title" style="margin-top: 10px;">
				<h3>${item.title}</h3>
			</div>
			<div class="panel-body">
				<small>${item.description}</small>
			</div>
			<div class="panel panel-footer" style="margin-top: 10px;">
				<c:if test="${sessionScope.user.admin == false && empty sessionScope.user.id || !empty sessionScope.user.id && sessionScope.user.admin == false }">
				<a href="/PhimLor/xemVideo/like/${item.id}"> 
					<c:if test="${empty checked}"><span class="bi bi-hand-thumbs-up-fill"> Like</span></c:if>
					<c:if test="${!empty checked}"><span class="bi bi-hand-thumbs-down-fill"> Dislike</span></c:if>
				</a> &nbsp; 
				<a href="#my-dialog" class="bi bi-share-fill"> Share</a> 
				<div class="dialog overlay" id="my-dialog">
            <a href="#" class="overlay-close"></a>
            <div class="dialog-body text-dark">
                <a class="dialog-close-btn" href="#"><span class="bi bi-x-lg"></span></a>
                <div class="row" style="margin-top: 10px">
                 <div class="col-3">
                 <img src="https://img.youtube.com/vi/${item.poster}/maxresdefault.jpg"  style="border-radius: 10px"> 
                 </div>
                 <div class="col-9">
                 	<h6><b>${item.title}</b></h6>
                 	<small>${item.views} Lượt xem</small> <br>
                 	<small>Được thích bởi: ${sessionScope.user.fullname}</small>
                 </div>
                </div>
                <br>
                <label><h4>Hãy nhập email người bạn muốn chia sẻ!</h4></label> <br> 
                <form class="input-group" method="post">
                	<input type="email" class="form-control" placeholder="Email Address..." name="toMail">
                	<button formaction="/PhimLor/xemVideo/share/${item.id}" class="btn btn-outline-success" type="submit"><span class="bi bi-send-fill"> Send </span></button>
                </form>
            </div>
        	</div>
				</c:if>
				<i class="bi bi-eye text-start"> ${item.views} Lượt xem</i>
				<c:if test="${sendMail==true}">
  						<div style="margin-top: 10px; width: 340px" class="alert alert-success alert-dismissible fade show" role="alert">
 						 Chia sẻ thành công!
 						 <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
						</div>
  			</c:if>
  			<c:if test="${sendMail==false}">
  						<div style="margin-top: 10px; width: 340px" class="alert alert-danger alert-dismissible fade show" role="alert">
 						 Chia sẻ không thành công!
 						 <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
						</div>
  			</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<h2>Video có thể bạn sẽ xem</h2>
				<hr>
			</div>
			<c:forEach var="item" items="${videos}">
				<div class="col-12 col-md-4">
					<a href="/PhimLor/xemVideo/xem/${item.id}" style="color: black">
						<div class="panel panel-heading text-center">
							<img
								src="https://img.youtube.com/vi/${item.poster}/maxresdefault.jpg" width="95%">
						</div>
						<div class="panel panel-title"
							style="padding-top: 5px; padding-left: 25px; padding-top: 15px">
							<h6 data-bs-toggle="tooltip" data-bs-placement="bottom"
								title="${item.title}"
								style="line-height: 25px; max-height: 55px; text-overflow: ellipsis; overflow: hidden;">
								<b>${item.title}</b>
							</h6>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		<center>
			<div style="margin-top: 30px">
				<h3>
					<a href="/PhimLor/index">Xem thêm...</a>
				</h3>
			</div>
		</center>
	</div>
	<br>
	<br>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
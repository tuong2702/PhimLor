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

	<div class="container" style="margin-top: 3rem;">
    <div class="row">
        <c:if test="${!empty favorites}">
        	<div class="col-5" style="background-color: #3d3751; width: 380px; height: 550px; margin-top: 5rem; border-radius: 20px;">
           <center>
            <img src="https://img.youtube.com/vi/${form.video.poster}/maxresdefault.jpg" style="border-radius: 10px; margin-top: 25px;" width="90%">
           </center>
           <div style="margin-left: 18px; margin-top: 15px; cursor: default;"  class="text-white">
            <h4 data-bs-toggle="tooltip" data-bs-placement="bottom" title="${form.video.title}"
								style="line-height: 25px; max-height: 51px; text-overflow: ellipsis; overflow: hidden;"><b>${form.video.title}</b></h4> <br>
            <h5>${form.video.views} Lượt xem</h5>
            <h5>Ngày thích: ${form.likeDate}</h5> <br><br>
            <form method="post">
            	 <a class="btn btn-outline-primary me-3" style="color: white;" href="/PhimLor/xemVideo/xem/${form.video.id}">Phát video</a>
           		 <a style="color: white;" class="dialog-btn btn btn-outline-success me-3" href="#my-dialog">Chia sẻ</a>
           		 <button class="btn btn-outline-danger" formaction="/PhimLor/favorite/dislike/${form.video.id}" style="color: white;">Bỏ thích</button>
            </form>
            <c:if test="${sendMail==true}">
  						<h6 style="margin-top: 10px;">Chia sẻ thành công!</h6>
  					</c:if>
  					<c:if test="${sendMail==false}">
  						<h6 style="margin-top: 10px;">Chia sẻ không thành công!</h6>
  					</c:if>
            <!-- Modal -->
            <div class="dialog overlay" id="my-dialog">
            <a href="#" class="overlay-close"></a>
            <div class="dialog-body text-dark">
                <a class="dialog-close-btn" href="#"><span class="bi bi-x-lg"></span></a>
                <div class="row" style="margin-top: 10px">
                 <div class="col-3">
                 <img src="https://img.youtube.com/vi/${form.video.poster}/maxresdefault.jpg"  style="border-radius: 10px"> 
                 </div>
                 <div class="col-9">
                 	<h6><b>${form.video.title}</b></h6>
                 	<small>${form.video.views} Lượt xem</small> <br>
                 	<small>Được thích bởi: ${sessionScope.user.fullname}</small>
                 </div>
                </div>
                <br>
                <label><h4>Hãy nhập email người bạn muốn chia sẻ!</h4></label> <br> 
                <form class="input-group" method="post">
                	<input type="email" class="form-control" placeholder="Email Address..." name="toMail">
                	<button formaction="/PhimLor/favorite/share/${form.video.id}" class="btn btn-outline-success" type="submit"><span class="bi bi-send-fill"> Send </span></button>
                </form>
            </div>
        </div>
           </div>
        </div>
        <div class="col-7" style="overflow-y: hidden;overflow-y: scroll; height: 77vh; margin-top: 5rem; margin-left: 30px; width: 900px;">
           <div class="row">
           <c:forEach var="item" items="${favorites}">
           	<div class="col-3 text-end" style="margin-top: 10px;">
           	<a href="/PhimLor/favorite/edit/${item.video.id}">
            	 <img src="https://img.youtube.com/vi/${item.video.poster}/maxresdefault.jpg" style="border-radius: 10px;" width="85%">
           	</a>
           </div>
           <div class="col-9" style="margin-top: 11px;">
            	<a href="/PhimLor/favorite/edit/${item.video.id}">
            	 <h6><b>${item.video.title}</b></h6> 
            	</a>
            	<small>${item.video.views} Lượt xem</small>
           </div>
           </c:forEach>
           </div>
        </div>
        </c:if>
        <c:if test="${empty favorites}">
        	<center>
        		<h1 class="text-dark" style="margin-top: 300px; margin-bottom: 200px ">Bạn chưa có video yêu thích nào!</h1>
        	</center>
        </c:if>
    </div>
  </div>

	<br>
	<br>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
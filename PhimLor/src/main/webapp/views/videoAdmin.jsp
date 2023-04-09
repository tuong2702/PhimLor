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
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
    integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
    integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
    crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
    crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
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
 <div class="container" style="margin-top: 8rem;">
    <div class="row">
      <div class="col-4">
        <form style="padding: 10px;" method="post">
        <div class="mb-3">
          <label>Photo name</label>
          <input type="text" class="form-control" value="${form.poster}" name="poster">
          </div>
          <div class="mb-3">
          <label>Video ID</label>
          <input type="text" class="form-control" value="${form.id}" name="id">
          </div>
          <div class="mb-3">
            <label>Title</label>
          <input type="text" class="form-control" value="${form.title}" name="title">
          </div>
         <div class="mb-3">
          <label>Views count</label>
          <input type="number" class="form-control" value="${form.views}" name="views">
         </div>
          <div class="mb-3">
            <label><input ${form.active?'checked':''} type="radio" name="active" value="true"> Active</label> &nbsp; &nbsp;
          <label><input ${form.active?'':'checked'} type="radio" name="active" value="false"> Inactive</label> <br>
          </div>
          <div class="mb-3">
            <label>Description</label>
          <textarea cols="48" rows="4" name="description">${form.description}</textarea>
          </div>
          <div class="mb-3">
          	${success} ${message}
          </div>
          <button class="btn btn-outline-primary me-2" formaction="/PhimLor/videoAdmin/create">Create</button>
          <button class="btn btn-outline-success me-2" formaction="/PhimLor/videoAdmin/update">Update</button>
          <button class="btn btn-outline-danger me-2" formaction="/PhimLor/videoAdmin/delete">Delete</button>
          <a href="/PhimLor/videoAdmin" type="submit" class="btn btn-outline-info me-2">Reset</a>
        </form>
      </div>
      <div class="col-8">
      <table style="margin-top: 20px;" class="table table-bordered border-primary">
        <thead>
          <tr>
            <th style="width: 10%;">Video ID</th>
            <th>Video Title</th>
            <th style="width: 20%;">Views Count</th>
            <th>Status</th>
            <th class="text-center" style="width: 5%;">#</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="item" items="${videos}">
          <tr>
            <td>${item.id}</td>
            <td>${item.title}</td>
            <td>${item.views} <i class="bi bi-eye"></i></td>
            <td>${item.active?'Active':'Inactive'}</td>
            <td class="text-center"><a href="/PhimLor/videoAdmin/edit/${item.id}" class="btn btn-outline-primary">Edit</a></td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      <ul class="pagination pagination-sm justify-content-center" style="z-index: -99999;">
    <c:forEach begin="1" end="${index}" var="i">
    	<li class="page-item"><a class="page-link" href="/PhimLor/videoAdmin/page/${i}">${i}</a></li>
    </c:forEach>
  </ul>
      </div>
    </div>
    </div>
  </div>
  </div>
  <br><br>
  
  <br><br>
  <!-- Footer -->
  <jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
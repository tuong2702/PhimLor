<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <!-- Preloader -->
  <div class="preloader d-flex align-items-center justify-content-center">
    <div class="lds-ellipsis">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>

 <!-- Header -->
  <header style="z-index: 9999">
    <div class="p-3 text-white">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <img src="/PhimLor/img/LogoPL1.png" width="30%">
        </a>
        <nav class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <a href="/PhimLor/index" class="nav-link px-2 text-white">HOME</a>
          <c:if test="${!empty sessionScope.user.id}">
          	<a href="/PhimLor/favorite/userid/${sessionScope.user.id}" class="nav-link px-2 text-white" >FAVORITE</a>
          </c:if>
          <a href="#" class="dropdown-toggle text-white bg-dark nav-link px-2" data-bs-toggle="dropdown"
            aria-expanded="false">
            <c:if test="${empty sessionScope.user.id}">
            		MORE:
            </c:if>
            <c:if test="${!empty sessionScope.user.id}">
            		<span class="text-uppercase">${sessionScope.user.id}</span>
            </c:if>
          </a>
          <c:if test="${!empty sessionScope.user.id}">
            <ul class="dropdown-menu dropdown-menu-dark">
            <li><a class="dropdown-item" href="/PhimLor/changePassword">Change Password</a></li>
            <li><a class="dropdown-item" href="/PhimLor/updateAcount">Update account</a></li>
            </ul>
          </c:if>
          <c:if test="${empty sessionScope.user.id}">
            <ul class="dropdown-menu dropdown-menu-dark">
            <li><a class="dropdown-item" href="/PhimLor/login">Login</a></li>
            <li><a class="dropdown-item" href="/PhimLor/register">Register</a></li>
            <li><a class="dropdown-item" href="/PhimLor/forgotPassword">Forgot password</a></li>
            </ul>
          </c:if>
        </nav>
        <div class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
          <form action="" class="input-group">
          	<input type="search" class="form-control form-control-dark" placeholder="Video title..." aria-label="Search">
          <button class="btn btn-outline-light">Search</button>
          </form>
        </div>
		 
		 <c:if test="${!empty sessionScope.user.id}">
		 	<div class="text-end">
          	<a href="/PhimLor/index/logout" class="text-white me-4 bi bi-box-arrow-right"> Log out</a>
       		 </div> 
		 </c:if>
      </div>
    </div>
  </header>
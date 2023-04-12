<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Preloader -->
  <div class="preloader d-flex align-items-center justify-content-center">
    <div class="lds-ellipsis">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
 <header style="z-index: 9999">
    <div class="p-3 text-white">
      <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
          <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
            <img src="/PhimLor/img/LogoPL1.png" width="30%">
          </a>
          <nav class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
            <a href="/PhimLor/index" class="nav-link px-2 text-white">HOME</a>
            <a href="/PhimLor/videoAdmin" class="nav-link px-2 text-white">VIDEOS</a>
            <a href="/PhimLor/userAdmin" class="nav-link px-2 text-white">USERS</a>
            <a href="#" class="dropdown-toggle text-white bg-dark nav-link px-2" data-bs-toggle="dropdown"
              aria-expanded="false">
              REPORTS
            </a>
            <ul class="dropdown-menu dropdown-menu-dark">
              <li><a class="dropdown-item" href="/PhimLor/reportFavorite">Favorite</a></li>
              <li><a class="dropdown-item" href="/PhimLor/reportFavoriteUser">Favorite User</a></li>
              <li><a class="dropdown-item" href="/PhimLor/reportFavoriteShare">Shared Friends</a></li>
            </ul>
          </nav>
          <div class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
          <form action="" class="input-group">
          	<input type="search" class="form-control form-control-dark" placeholder="Video title..." aria-label="Search">
          <button class="btn btn-outline-light">Search</button>
          </form>
        </div>
          <div class="text-end">
            <a href="/PhimLor/index/logout" class="text-white me-2 bi bi-box-arrow-right"> Log out</a>
          </div>
        </div>
      </div>
  </header>
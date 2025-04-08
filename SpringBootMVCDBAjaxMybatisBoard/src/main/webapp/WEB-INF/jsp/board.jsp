<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mycom.myapp.user.dto.UserDto"%>
<%
    UserDto userDto = (UserDto) session.getAttribute("userDto");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>게시판</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="#">
            <img src="/assets/img/user/<%=userDto.getUserProfileImage() %>" style="width:24px; height: 24px; border-radius: 50%;">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
<%--                    <a class="nav-link" href="/pages/logout">Logout</a>--%>
                    <a class="nav-link" href="/auth/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

    <script>
        // logout 처리 방식 구분
        // #1. Javascript 를 이용한 비동기 처리
        //     Logout 이 클릭되면 이벤트핸들러에서 비동기로 logout 요청 ( 서버에서 로그아웃 처리 후 result:success 전달)
        //     페이지를 window.location.href 로 login 페이지로 이동
        // #2. a 의 href 에 page 요청 ( 서버에서 로그아웃 처리 후 바로 페이지를 이동 ) <= 2번으로 진행
    </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
<%-- validation check
    브라우저 내장 유효성 검사 X <= <form novalidate>
    Bootstrap 제공 유효성 검사 O
    --%>
<div class="container">

    <div class="mb-3 mt-5 d-flex justify-content-center">
        <h2 class="display-4">LG U+ URECA</h2>
    </div>
    <div class="mb-3">
        <h1>로그인</h1>
    </div>

    <form novalidate>
        <div class="mb-3">
            <label for="userEmail" class="form-label">User Email:</label>
            <input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="Enter Email" value="dskim@dskim.com">
        </div>
        <div class="mb-3">
            <label for="userPassword" class="form-label">Password:</label>
            <input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="Enter Password" value="1234">
        </div>
    </form>
    <div>
        <button type="button" id="btnLogin" class="btn btn-success">로그인</button>
    </div>
</div>

<script>
    window.onload = function(){
        // btnRegister 처리
        document.querySelector("#btnLogin").onclick = function(){
            // validation check
            if( document.querySelector("#userEmail").value == '' || document.querySelector("#userPassword").value == '' ) {
                alert("입력이 올바르지 않습니다.");
                return;
            }

            login();
        }
    }

    // 로그인
    async function login() {
        // 사용자 입력 값
        let userEmail = document.querySelector("#userEmail").value;
        let userPassword = document.querySelector("#userPassword").value;

        // x-www-form-urlencoded
        let urlParams = new URLSearchParams({
            userEmail: userEmail,
            userPassword: userPassword
        });

        // fetch Options
        let fetchOptions = {
            method: "POST",
            body: urlParams
        }

        let response = await fetch("/auth/login", fetchOptions);
        let data = await response.json();

        if( data.result == "success" ) {
            // 게시판 페이지 이동
            window.location.href="/pages/board"
        } else {
            alert("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
    }
</script>
</body>
</html>
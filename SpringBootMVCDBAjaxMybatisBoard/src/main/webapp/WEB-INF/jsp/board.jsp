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

<div class="container mt-3">
    <h4 class="text-center">게시판</h4>

    <div class="input-group mb-3">
        <input id="inputSearchWord" type="text" class="form-control" placeholder="검색어를 입력하세요.">
        <button id="btnSearchWord" class="btn btn-success" type="button">검색</button>
    </div>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일시</th>
            <th>조회수</th>
        </tr>
        </thead>
        <tbody id="boardTbody">
        </tbody>
    </table>
</div>

    <script src="/assets/js/util.js"></script>
    <script>
        // logout 처리 방식 구분
        // #1. Javascript 를 이용한 비동기 처리
        //     Logout 이 클릭되면 이벤트핸들러에서 비동기로 logout 요청 ( 서버에서 로그아웃 처리 후 result:success 전달)
        //     페이지를 window.location.href 로 login 페이지로 이동
        // #2. a 의 href 에 page 요청 ( 서버에서 로그아웃 처리 후 바로 페이지를 이동 ) <= 2번으로 진행'

        let LIST_ROW_COUNT = 10; // limit 해당 변수
        let OFFSET = 0; // offset
        let SEARCH_WORD = ''; // searchWord
        let TOTAL_LIST_COUNT = 0; // 조회된 건수

        window.onload = function() {
            // 글 목록
            listBoard();

            // 검색어 처리
            document.querySelector("#btnSearchWord").onclick = function() {
                SEARCH_WORD = document.querySelector("#inputSearchWord").value;
                listBoard();
            }
        }

        async function listBoard() {
            let url = "/boards/list";
            let urlParams = "?limit=" + LIST_ROW_COUNT + "&offset=" + OFFSET + "&searchWord=" + SEARCH_WORD;
            let response = await fetch(url + urlParams);
            let data = await response.json();

            console.log(data);

            if(data.result == "success") {
                makeListHtml(data.list);
                TOTAL_LIST_COUNT = data.count;
            }
        }

        function makeListHtml(list){
            let listHTML = ``;

            list.forEach( el => {
                let boardId = el.boardId;
                let userName = el.userName;
                let title = el.title;
                let content = el.content;
                let regDt = el.regDt;
                // LocalDateTime 객체 --> json 처리 결과물이 gson, jackson 2가지가 다르다.
//          console.log(regDt);
                let regDtStr = makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.'); // 2024.07.11
                let readCount = el.readCount;

                listHTML += `<tr style="cursor:pointer" data-boardId=\${boardId}>
                            <td>\${boardId}</td>
                            <td>\${title}</td>
                            <td>\${userName}</td>
                            <td>\${regDtStr}</td>
                            <td>\${readCount}</td>
                        </tr>`;
            });

            document.querySelector("#boardTbody").innerHTML = listHTML;

            document.querySelectorAll("#boardTbody tr").forEach( el => {
                el.onclick = function(){
                    let boardId = this.getAttribute("data-boardId");
                    detailBoard(boardId);
                }
            } );
        }

        async function detailBoard(boardId) {
            alert(boardId);
        }
    </script>
</body>
</html>
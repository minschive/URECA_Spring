<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  el 사용

  error 페이지에서 어떤 정보를 얼만큼 자세히 보여주는 것이 올바른가 ?
  1. 매우 상세한 예외 정보 ?? <= 보안 이슈
  2. 아무 것도 안 보여준다 ?? <= 내용도 없는데 뭐하러 보여주냐

  업무용 시스템 vs  일반 고객 서비스 시스템
--%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>error.jsp</title>
  </head>
  <body>
    <h1>Error Page</h1>
    <p>요청하신 서비스에 문제가 발생했습니다.</p>
    <p>요청하신 서비스 : ${requestURI}</p>
    <p>발생된 오류 : ${exception}</p>

  </body>
</html>

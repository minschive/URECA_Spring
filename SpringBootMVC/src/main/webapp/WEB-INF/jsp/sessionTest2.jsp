<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
    String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Insert title here</title>
</head>
<body>
    <h1>sessionTest2.jsp</h1>
<%
    if(username == null) {
%>
    <h4>�α��εǾ� ���� �ʽ��ϴ�.</h4>
<%
    } else {
%>
    <h4><%=username %> �� �α��εǾ� �ֽ��ϴ�.</h4>
<%
    }
%>
</body>
</html>

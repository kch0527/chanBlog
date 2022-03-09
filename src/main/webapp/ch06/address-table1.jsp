<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jspservlet.ch06.domain.*" %>
<html>
<head>
    <title>Address Table</title>
</head>
<body>
<h1>Found Name!</h1>
<%--
    Member member = (Member) request.getAttribute("member");
    out.println("Name :" + member.getEmail());
--%>
Name : ${blogger.name}<br/>
Pw : ${requestScope.blogger.pw}<br/>
Email : ${requestScope.blogger.email}<br/>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: chan
  Date: 2021-12-01
  Time: 오후 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    map.put("egyou", new Integer(95));
    map.put("shhan", new Integer(97));
    map.put("sj-ko", new Integer(96));
    request.setAttribute("hashmap", map);
%>

${hashmap.egyou}<br/>
${hashmap["shhan"]}<br/>
${hashmap["sj-ko"]}<br/>
${hashmap.sj-ko} <%-- 정상적으로 처리되지 않음--%>

</body>
</html>

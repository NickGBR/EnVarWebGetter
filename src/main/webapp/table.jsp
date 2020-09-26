<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 2020-09-26
  Time: 4:49 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>variables</title>
</head>
<body>
<a href="javascript:history.back()">back</a>
<table border = "1">
    <% Map<String, String> variables = System.getenv();%>

    <% for (Map.Entry<String, String> pair : variables.entrySet()) {
        out.print("</tr>");
        out.println("<th>" + pair.getKey() + "</th>");
        out.println("<th>" + pair.getValue() + "</th>");
        out.println("</tr>");
    }%>

</table>
</body>
</html>
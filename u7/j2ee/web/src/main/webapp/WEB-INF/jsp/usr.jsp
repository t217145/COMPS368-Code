<%-- 
    Document   : usr
    Created on : Mar 8, 2020, 2:48:19 PM
    Author     : Cyrus Cheng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="logout.jsp" />
        Roles : ${roles} <br />
        UserName by code : ${name} <br />
        UserName by tags : <sec:authentication property="principal.username" /> <br />
    </body>
</html>

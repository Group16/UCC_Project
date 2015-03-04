<%-- 
    Document   : logout
    Created on : 26-Feb-2015, 15:40:29
    Author     : mm37
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                session.invalidate();
                response.sendRedirect( "index.jsp" );
        %>
    </body>
</html>

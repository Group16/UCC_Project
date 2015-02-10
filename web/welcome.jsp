<%-- 
    Document   : welcome
    Created on : 02-Feb-2015, 13:18:49
    Author     : mm37
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        
        <%
        String user=session.getValue("userid").toString();
        if(request.getParameter("submit")!= null){
            response.sendRedirect( "meeting.jsp" );
        }
        %>
        You are logged in. Welcome <%=user %>
        
        <form name='form' action='welcome.jsp' method='POST'>
            <input type="submit" name="submit" value="Arrange a meeting"/>
        </form>
    </body>
</html>

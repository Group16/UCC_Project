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
        String firstName;
        String lastName;
        if( session.getAttribute( "firstName" ) == null ) {
                response.sendRedirect( "login.jsp" );
        }
        else
        {
            firstName=session.getAttribute("firstName").toString();
            lastName=session.getAttribute("lastName").toString();
            out.println("You are logged in. Welcome " + firstName + " " + lastName);
            if(request.getParameter("meetingSubmit")!= null){
                response.sendRedirect( "meeting.jsp" );
            }
            if(request.getParameter("notificationSubmit")!= null){
                response.sendRedirect( "notification.jsp" );
            }
        }
        %>
        
        <form name='form' action='welcome.jsp' method='POST'>
            <input type="submit" name="meetingSubmit" value="Arrange a meeting"/>
            
            <input type="submit" name="notificationSubmit" value="Arrange a meeting"/>
        </form>
    </body>
</html>

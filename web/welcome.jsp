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
        //if the user is not logged in
        if( session.getAttribute( "lastName" ) == null ) {
                response.sendRedirect( "login.jsp" );
        }
        else
        {
            String firstName;
            String lastName;
            firstName=session.getAttribute("firstName").toString();
            lastName=session.getAttribute("lastName").toString();
            out.println("You are logged in. Welcome " + firstName + " " + lastName);
            
            //if the meeting button is pressed
            if(request.getParameter("meetingSubmit")!= null){
                response.sendRedirect( "meeting.jsp" );
            }
            //if the notification button is pressed
            if(request.getParameter("notificationSubmit")!= null){
                response.sendRedirect( "notification.jsp" );
            }
            
            
        
        %>
        <a href="http://localhost:8080/UCC_Scheduler_Program/logout.jsp">Logout</a>
        <form name='form' action='welcome.jsp' method='POST'>
            <input type="submit" name="meetingSubmit" value="Arrange a meeting"/>
            
            <input type="submit" name="notificationSubmit" value="Notifcations"/>
            
        <%
           //if the user is a lecturer
           if(session.getAttribute("p_type").equals("lecturer")){
           %><input type="submit" name="tutorialSubmit" value="Arrange a Tutorial"/><%

                if(request.getParameter("tutorialSubmit")!= null){
                    response.sendRedirect( "tutorial.jsp" );
                }
            }
        }
        %>
        </form>
    </body>
</html>

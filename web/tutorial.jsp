<%-- 
    Document   : tutorial
    Created on : 26-Feb-2015, 16:47:35
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
        <h1>Arrange a tutorial!</h1>
        <jsp:useBean id="meeting" class="control.MeetingChecker" scope="session" />
        <jsp:setProperty name="meeting" property="*"/>
        <%
         UI.GUI gui = new UI.GUI();
         notification.Notify not = new notification.Notify();
         if( session.getAttribute( "firstName" ) == null ) {
                response.sendRedirect( "login.jsp" );
         }
         else
         {
            if(request.getParameter( "submit" ) != null){
                 if( !meeting.validate() ){  
                      out.println(gui.generateTutorialGUI(meeting));
                 }
                 else
                 {
                     out.println("Your tutorial has been set");
                     meeting.insertNotQuery("tutorial");
                     meeting.insertMeetQuery("tutorial", "1");
                 }
             }
             else
             {
                out.println(gui.generateTutorialGUI()); 
             }
         }
        %>
    </body>
</html>

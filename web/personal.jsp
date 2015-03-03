<%-- 
    Document   : personal
    Created on : 03-Mar-2015, 15:29:11
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
        <h1>Hello World!</h1>
        <jsp:useBean id="meeting" class="control.MeetingChecker" scope="session" />
        <jsp:setProperty name="meeting" property="*"/>
        <%
         UI.GUI gui = new UI.GUI();
         if( session.getAttribute( "lastName" ) == null ) {
                response.sendRedirect( "login.jsp" );
         }
         else
         {
            if(request.getParameter( "submit" ) != null){
                 if( !meeting.validate() ){  
                      out.println(gui.generatePersonalGUI(meeting));
                 }
                 else
                 {
                     out.println("Personal event set");
                     meeting.insertPersonalQuery("personal", "1");
                  }
             }
             else
             {
                out.println(gui.generatePersonalGUI()); 
             }
         }
       %>
    </body>
</html>

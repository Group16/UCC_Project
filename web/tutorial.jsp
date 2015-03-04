<%-- 
    Document   : tutorial
    Created on : 26-Feb-2015, 16:47:35
    Author     : mm37
--%>

<%@page import="java.util.ArrayList"%>
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
        
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
        
        <script>
            $( function( ) {
              $( ".datepicker" ).datepicker( { dateFormat: "yy/mm/dd" } );
            });
            
        </script>
        <script>
         $( function( ) {
              $(".timepicker").timepicker({ timeFormat: "H:i:s" });
            });
        </script>
        
  
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
                     String [] people = request.getParameter("recipient").split(","); 
                     ArrayList<String> list = new ArrayList();
                     for(String person : people)
                     {
                         list.add(person.trim());
                     }
                     list.add( session.getAttribute("id").toString() );
                     
                     out.println("Your tutorial has been set");
                     meeting.insertNotQuery("tutorial");
                     meeting.insertMeetQuery("tutorial", "1", list);
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

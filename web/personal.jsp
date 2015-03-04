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
            <link href="MarkUp/css/style.css" rel="stylesheet">
        
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
    </head>
    <body>
       
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
         if( session.getAttribute( "lastName" ) == null ) {
                response.sendRedirect( "login.jsp" );
         }
         else
         {
            if(request.getParameter( "submit" ) != null){
                 if( !meeting.validatePersonal() ){  
                      out.println(gui.generatePersonalGUI(meeting));
                 }
                 else
                 {
                     out.println("Personal event set");
                     meeting.insertPersonalPIMQuery((String)session.getAttribute("id") );
                     meeting.insertPersonalQuery();
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

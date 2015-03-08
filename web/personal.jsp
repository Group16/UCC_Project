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
        <div class='subpage'>
            <h1>Set a Personal Event</h1>
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
         //if the user is not logged in
         if( session.getAttribute( "lastName" ) == null ) {
                response.sendRedirect( "login.jsp" );
         }
         else
         {
            //if the submit button has been pressed
            if(request.getParameter( "submit" ) != null){
                //if the form is not valid
                 if( !meeting.validatePersonal() ){  
                      out.println(gui.generatePersonalGUI(meeting));
                 }
                 else
                 {
                     //insert meeting into the people_in_meeting table
                     meeting.insertPersonalPIMQuery((String)session.getAttribute("id") );
                     //insert personal meeting into the meeting table
                     meeting.insertPersonalQuery();
                     response.sendRedirect("scheduler.jsp");
                  }
             }
             else
             {
                out.println(gui.generatePersonalGUI()); 
             }
         }
       %>
       <p><a href="scheduler.jsp" ><button class='btn btn-danger' >Back</button></a></p>
        </div>
    </body>
</html>

<%-- 
    Document   : meeting
    Created on : 07-Feb-2015, 20:10:42
    Author     : mm37
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            <link href="MarkUp/css/style.css" rel="stylesheet">
                <link href="MarkUp/css/style.css" rel="stylesheet">
    </head>

        
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
  
        <jsp:useBean id="meeting" class="control.MeetingChecker" scope="session" />
        <jsp:setProperty name="meeting" property="*"/>
        <%
         //String user=session.getValue("userid").toString();
         UI.GUI gui = new UI.GUI();
         notification.Notify not = new notification.Notify();
         
         if( session.getAttribute( "lastName" ) == null ) 
         {
                response.sendRedirect( "login.jsp" );
         }
         else
         {
            if(request.getParameter( "submit" ) != null){
                 if( !meeting.validate() )
                 {  
                      out.println(gui.generateMeetingGUI(meeting));
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
                     
                     meeting.insertNotQuery("meeting");
                     meeting.insertOtherPIMQuery( "0", list);
                     meeting.insertMeetQuery("meeting", "0", list);
                     
                     response.sendRedirect("scheduler.jsp");
                 }
             }
             else
             {
                out.println(gui.generateMeetingGUI()); 
             }
         }
       %>
            
        </form>
        
    </body>
</html>

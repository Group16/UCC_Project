<%-- 
    Document   : meeting
    Created on : 07-Feb-2015, 20:10:42
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
            
         //String user=session.getValue("userid").toString();
         UI.GUI gui = new UI.GUI();
          if(request.getParameter( "submit" ) != null){
              if( !meeting.validate() ){  
                   out.println(gui.generateMeetingGUI(meeting));
              }
              else
              {
                  out.println("Your meeting has been set");
                  meeting.insertQuery();
              }
          }
          else
          {
             out.println(gui.generateMeetingGUI()); 
          }
       %>
                       
           <input type='hidden' name='sender' value='Freddy' />

        </form>
        
    </body>
</html>

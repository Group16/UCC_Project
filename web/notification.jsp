<%-- 
    Document   : notification
    Created on : 15-Feb-2015, 19:16:53
    Author     : mm37
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Notification</h1>
        
        
        <%
            Statement statementObject;
            Connection connectionObject;
            
            control.MeetingChecker meeting = new control.MeetingChecker();
            
            if( session.getAttribute( "firstName" ) == null ) {
                response.sendRedirect( "login.jsp" );
            }
            else
            {
                connectionObject = DriverManager.getConnection("jdbc:mysql://"+"cs1.ucc.ie"+"/" + "2016_mm37", "mm37", "uohongah");
                database.DbClass db = new database.DbClass();
                db.setup();
                db.checkQuery("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                
                int count = db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                out.println("<h1>You have " + count + " notifications</h1>");
                
                if(db.queryCorrect==true)     
                { 
                    String output="";
                    try {// Make connection to database
                        statementObject = connectionObject.createStatement();
                        ResultSet statementResult = statementObject.executeQuery("SELECT meetings.m_id, time, meetings.date, location, meetings.type, description FROM meetings JOIN notifications ON meetings.m_id = notifications.m_id WHERE p_id ='" + session.getAttribute("id") + "'");
                        %><form action="notification.jsp" method="POST"><%
                         %><table style="border: 1px solid red; border-collapse: collapse;" ><%
                     
                        while(statementResult.next()){
                          
                            %><tr style="border: 1px solid red; margin-top:20px;"><%
                            %><td><%
                            output = statementResult.getString(2) + ", ";
                            output += statementResult.getString(3) + ", ";
                            output += statementResult.getString(4) + ", ";
                            output += statementResult.getString(5) + ", ";
                            output += statementResult.getString(6) + " ";
                            
                            
                            out.println(output);
                            %><input type='checkbox' name='CheckAllow' value="<%=statementResult.getString(1)%>" /></td></tr><%
                            
                        }
                        
                        %></table></ol><%
                          %><input type='submit' name='submit' value="Accept" /></form><%
                    } catch (SQLException exceptionObject) {

                    }
                }
                
                    if(request.getParameterValues("CheckAllow" )!=null){
                        String[] meetingIDs = request.getParameterValues("CheckAllow" );
                        for(String id: meetingIDs){
                            db.Insert("UPDATE meetings SET confirmed = '1' WHERE m_id = '"+ id +"'");
                            db.Insert("DELETE FROM notifications WHERE m_id = '"+ id +"' ");
                        }
                        response.sendRedirect("notification.jsp");
                    }
        }
     %>
    </body>
</html>

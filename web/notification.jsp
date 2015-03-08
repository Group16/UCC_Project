<%-- 
    Document   : notification
    Created on : 15-Feb-2015, 19:16:53
    Author     : mm37
--%>

<%@page import="database.DbClass"%>
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
        <title>Notifications</title>
        <link href="MarkUp/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class='subpage'>
        <%
            Statement statementObject;
            Connection connectionObject;
            
            control.MeetingChecker meeting = new control.MeetingChecker();
            //if the user is logged
            if( session.getAttribute( "firstName" ) == null ) {
                response.sendRedirect( "login.jsp" );
            }
            else
            {
                // set up the connection
                connectionObject = DriverManager.getConnection("jdbc:mysql://" + DbClass.getHost() + "/" + DbClass.getDatabase(), DbClass.getUser(), DbClass.getPassword());
                database.DbClass db = new database.DbClass();
                db.setup();
                db.checkQuery("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                //get the count of all the notifications
                int count = db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                
                out.println("<h1>You Have " + count + " Notifications</h1>");
                
                //if the checkQuery call returns true
                if(db.queryCorrect==true)     
                { 
                    String output="";
                    try {// Make connection to database
                        statementObject = connectionObject.createStatement();
                        ResultSet statementResult = statementObject.executeQuery("SELECT * FROM meetings JOIN notifications  ON meetings.m_id = notifications.m_id WHERE p_id ='" + session.getAttribute("id") + "'");
                        
                        out.print("<form action=\"notification.jsp\" id=\"forms\" method=\"POST\">");
                        //while there is still results in the result sets
                        while(statementResult.next())
                        {
                            //Get the eight column in the result set
                            output = "<div class=\"notif-row\"><strong>" + statementResult.getString(8) + "</strong> at ";
                            //Get the third column in the result set
                            output += statementResult.getString(3) + " on ";
                            //Get the fourth column in the result set
                            output += statementResult.getString(4) + "";
                            out.println(output);
                            out.print("<input type='checkbox' name='CheckAllow' value=\"" + statementResult.getString(1) + "\" /></div>");
                        }
                        out.print("<input class='btn btn-success' type='submit' name='submit' value=\"Accept\" /></form>");
                          
                    } catch (SQLException exceptionObject) {}
                }
                    //if the user has accepted the notification
                    if(request.getParameterValues("CheckAllow" )!=null){
                        String[] meetingIDs = request.getParameterValues("CheckAllow" );
                        for(String id: meetingIDs){
                            //Update tables
                            db.Insert("UPDATE meetings SET confirmed = '1' WHERE m_id = '"+ id +"'");
                            db.Insert("DELETE FROM notifications WHERE m_id = '"+ id +"' ");
                        }
                        response.sendRedirect("notification.jsp");
                    }
        }
     %>
     <p><a href="scheduler.jsp" ><button class='btn btn-danger' >Back</button></a></p>
     </div>
    </body>
</html>

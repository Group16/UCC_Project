<%-- 
    Document   : addlecture
    Created on : 03-Mar-2015, 20:55:33
    Author     : vnl1
--%>

<%@page import="database.DbClass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a Lecture</title>
                    <link href="MarkUp/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="subpage">
            <h1>Add a Lecture</h1>
        <jsp:useBean id="meeting" class="control.MeetingChecker" scope="session" />
        <jsp:setProperty name="meeting" property="*"/>
        <%
            if ( request.getParameter( "submit" ) != null )
            {
                DbClass db = new DbClass();
                db.setup();
                
                String date = request.getParameter( "startDate" );
                String time = request.getParameter( "time" );
                String module = request.getParameter( "module" );
                
                meeting.insertLectureQuery(date, time);
                db.Insert( "INSERT INTO modules_in_meetings (m_id, mod_id) VALUES ('" + meeting.getMeetingID() + "','" + module + "');" );
                
                out.print("Added the lecture '"+ meeting.getDescription() + "' for the module '" + module + "'");
            }
            else
            {
                out.print("<form action='addlecture.jsp' id='forms'>"
                        + "Title: <input type='text' name='description' />"
                        + "Location: <input type='text' name='location' />"
                        + "Start Date:<input type='text' class='datepicker' name='startDate' placeholder='2015/02/08' />"
                        + "Time: <input type='text' name='time' />"
                        + "Module: <input type='text' name='module' />"
                        + "<input class='btn btn-success' type='submit' name='submit' />"
                        + "</form>");
            }
        %>
        <p><a href="scheduler.jsp" ><button class='btn btn-danger' >Back</button></a></p>
        </div>
    </body>
</html>

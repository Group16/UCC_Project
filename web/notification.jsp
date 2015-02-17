<%-- 
    Document   : notification
    Created on : 15-Feb-2015, 19:16:53
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
        <h1>Notification</h1>
        
        
        <%
            int count=0;
            out.println("You have " + count + " notifications");
            database.DbClass db = new database.DbClass();
            db.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
            db.checkQuery("select * from notifications where p_id='" + session.getAttribute("id") + "'");
            if(db.queryCorrect==true) 
            { 
                out.println("IT WORKED!!!");
            }
        
        
        %>
    </body>
</html>

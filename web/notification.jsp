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
            
            if( session.getAttribute( "firstName" ) == null ) {
                response.sendRedirect( "login.jsp" );
            }
            else
            {
                database.DbClass db = new database.DbClass();
                db.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
                db.checkQuery("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                
                int numRows = db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                
                if(db.queryCorrect==true)     
                { 
                    int i = 0;
                    while(i < numRows){
                        String[] not = db.SelectRow( "SELECT * FROM meetings JOIN notifications ON meetings.m_id = notifications.n_id WHERE p_id ='" + session.getAttribute("id") + "'");
                        String info = not[i];
                        
                        out.println(info);
                        count+=1;
                        i++;
                    }
                }
                
                out.println("<h1>You have " + count + " notifications</h1>");
            }
        
        %>
    </body>
</html>

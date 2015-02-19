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
            String dbserver="cs1.ucc.ie";
            String DSN ="2016_mm37" ;
            String URL = "jdbc:mysql://"+dbserver+"/" + DSN;
            connectionObject = DriverManager.getConnection(URL, "mm37", "uohongah");
            if( session.getAttribute( "firstName" ) == null ) {
                response.sendRedirect( "login.jsp" );
            }
            else
            {
                database.DbClass db = new database.DbClass();
                db.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
                db.checkQuery("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                
                int count = db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                out.println("<h1>You have " + count + " notifications</h1>");
                
                if(db.queryCorrect==true)     
                { 
                     String output="";
                    ArrayList<String> list = new ArrayList<String>();
                    try {// Make connection to database
                        statementObject = connectionObject.createStatement();
                        ResultSet statementResult = statementObject.executeQuery("SELECT time, location, description FROM meetings JOIN notifications ON meetings.m_id = notifications.n_id WHERE p_id ='" + session.getAttribute("id") + "'");

                        while(statementResult.next()){
                            %><ol><%
                            output = statementResult.getString(1) + " ";
                            output += statementResult.getString(2) + " ";
                            output += statementResult.getString(3) + '\n';
                            
                            out.println(output);
                            %></ol><%
                        }
                    } catch (SQLException exceptionObject) {

                    }
            }
            }
        %>
    </body>
</html>

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
            
            boolean correctBtn = false;
            
            if( session.getAttribute( "firstName" ) == null ) {
                response.sendRedirect( "login.jsp" );
            }
            else
            {
                connectionObject = DriverManager.getConnection("jdbc:mysql://"+"cs1.ucc.ie"+"/" + "2016_mm37", "mm37", "uohongah");
                database.DbClass db = new database.DbClass();
                db.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
                db.checkQuery("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                
                int count = db.getLength("select * from notifications where p_id='" + session.getAttribute("id") + "'");
                out.println("<h1>You have " + count + " notifications</h1>");
                
                int num = 1;
                int num2 = 1;
                if(db.queryCorrect==true)     
                { 
                    String output="";
                    try {// Make connection to database
                        statementObject = connectionObject.createStatement();
                        ResultSet statementResult = statementObject.executeQuery("SELECT time, location, description FROM meetings JOIN notifications ON meetings.m_id = notifications.m_id WHERE p_id ='" + session.getAttribute("id") + "'");
                        %><form action="notification.jsp" method="POST"><%
                         %><table style="border: 1px solid red; border-collapse: collapse;" ><%
                     
                        while(statementResult.next()){
                          
                            %><tr style="border: 1px solid red; margin-top:20px;"><%
                            %><td><%
                            output = statementResult.getString(1) + " ";
                            output += statementResult.getString(2) + " ";
                            output += statementResult.getString(3) + '\n';
                            session.setAttribute("count", count);
                            out.println(output);
                            %><input type='checkbox' name='CheckAllow' value="Accept<%=num%>" /></td></tr><%
                            num++;
                        }
                        
                        %></table></ol></form><%
                    } catch (SQLException exceptionObject) {

                    }
                }
                
               while(!correctBtn && num2 <= count){
                    String strNum = Integer.toString(num2);
                    
                    out.print("AllowSubmit"+strNum);
                    
                    if(request.getParameter("AllowSubmit"+strNum )!=null){
                        correctBtn = true;
                        db.Insert("UPDATE meetings SET confirmed = '1' WHERE m_id = '"+ strNum +"'");
                        db.Insert("DELETE FROM notifications WHERE m_id = '"+ strNum +"' ");
                        response.sendRedirect("notification.jsp");
                    }
                    num2++;
               }
                
        }
     %>
    </body>
</html>

<%--
    Document   : login.jsp
    Created on : 25-Jan-2015, 16:32:43
    Author     : mm37
--%>

<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        
    </head>
    <body>
       <h1>Login</h1>
        <%
            control.CheckerClass checker = new control.CheckerClass();
            //Calling the User Interface.
            UI.GUI ui = new UI.GUI();
            out.println(ui.generateLogGUI());
            String studentID = request.getParameter("studentID"); 
            String pwd=request.getParameter("pword"); 
            database.DbClass db = new database.DbClass();
            db.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
            
            if(request.getParameter("submit")!=null){
                db.checkQuery("select * from people where p_id='" + studentID + "' and password = '" + checker.get_SHA_256_SecurePassword(pwd) + "'");
                if(db.queryCorrect==true) 
                { 
                     String[] data = db.SelectRow( "select * from people where p_id='" + studentID + "' and password = '" + checker.get_SHA_256_SecurePassword(pwd) + "'");
            
                     String firstName = data[2];
                     String lastName = data[3];
                     session.setAttribute("firstName",firstName);
                     session.setAttribute("lastName",lastName);
                     response.sendRedirect( "welcome.jsp" );
                     
                } 
                else 
                {
                    out.println("Wrong username/password");
                }
            }
    %>
    </body>
</html>

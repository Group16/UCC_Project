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
            db.setup();
            //if the submit button is hit
            if(request.getParameter("submit")!=null){
                db.checkQuery("select * from people where p_id='" + studentID + "' and password = '" + checker.get_SHA_256_SecurePassword(pwd) + "'");
                //if the checkQuery method set the queryCorrect variable is true
                if(db.queryCorrect==true) 
                { 
                     String[] data = db.SelectRow( "select * from people where p_id='" + studentID + "' and password = '" + checker.get_SHA_256_SecurePassword(pwd) + "'");
                     
                     //get data from SelectRow query
                     String firstName = data[2];
                     String lastName = data[3];
                     String id = data[0];
                     String p_type = data[1];
                     
                     //Set session attributes
                     session.setAttribute("firstName",firstName);
                     session.setAttribute("lastName",lastName);
                     session.setAttribute("id", id);
                     session.setAttribute("p_type", p_type);
                     response.sendRedirect( "scheduler.jsp" );
                } 
                else 
                {
                    out.println("Wrong username/password");
                }
            }
    %>
    </body>
</html>

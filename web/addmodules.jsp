<%-- 
    Document   : addmodules
    Created on : Mar 3, 2015, 8:22:38 PM
    Author     : daniel
--%>

<%@page import="database.DbClass"%>
<%@page import="UI.GUI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Modules</h1>
        <%
            GUI ui = new GUI();
            ui.generateReg2GUI();
            
            if( request.getParameter( "submit" ) != null )
            {
                DbClass db = new DbClass();
                db.setup("cs1.ucc.ie", "2016_mm37", "mm37", "uohongah");
                
                String[] courseModules = request.getParameterValues("modules");

                if ( courseModules != null ) 
                {
                    for (int i = 0; i < courseModules.length; i++) 
                    {
                        db.Insert( "INSERT INTO people_in_modules (p_id, mod_id) VALUES ('" + session.getAttribute("id") + "','" + courseModules[i] + "');" );
                    }

                    response.sendRedirect("welcome.jsp");
                } 
                else 
                {
                    out.println("Please Check Some Boxes");
                }
            }
            else
            {
            }
        %>
    </body>
</html>

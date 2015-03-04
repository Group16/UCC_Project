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
            if( request.getParameter( "submit" ) != null )
            {
                DbClass db = new DbClass();
                db.setup();
                
                String[] courseModules = request.getParameterValues("module");

                if ( courseModules != null ) 
                {
                    for (int i = 0; i < courseModules.length; i++) 
                    {
                        db.Insert( "INSERT INTO people_in_modules (p_id, mod_id) VALUES ('" + session.getAttribute("id") + "','" + courseModules[i] + "');" );
                    }

                    response.sendRedirect("scheduler.jsp");
                } 
                else 
                {
                    out.println("Please Check Some Boxes");
                }
            }
            else
            {
                GUI ui = new GUI();
                out.print( ui.generateReg2GUI() );
            }
        %>
    </body>
</html>

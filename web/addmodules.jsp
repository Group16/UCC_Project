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
        <title>Set Your Modules</title>
        <link href="MarkUp/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class='subpage'>
            <h1>Set Your Modules</h1>
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
        <p><a href="scheduler.jsp" ><button class='btn btn-danger' >Back</button></a></p>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>       
        <script>window.jQuery || document.write('<script src="MarkUp/js/jquery.min.js"><\/script>')</script>

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script  type="text/javascript" src="MarkUp/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $('select.div-toggler').change(function () {
                var target = $(this).data('target');
                $(target).children().addClass('hide');
                var show = $("option:selected", this).data('show');
                $(show).removeClass('hide');
            });

            $(document).ready(function () {
                $("#forgotbutton").click(function () {
                    $("#forgot").toggle();
                });
            });
        </script>
    </div>
    </body>
</html>

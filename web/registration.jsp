<%-- 
    Document   : Registration
    Created on : 02-Feb-2015, 16:29:02
    Author     : mm37
--%>
<%@page import="database.DbClass"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <!-- Modal more registration details window -->	
    <div class="modal" id="registerdetail" role="dialog">
        <div class = "modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <p>Register</p>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>

                <div class="modal-body">
                    <jsp:useBean id="checker" class="control.CheckerClass" scope="session" />
                    <jsp:setProperty name="checker" property="*"/>
                    <%
                        EmailSender.Email email = new EmailSender.Email();
                        //Calling the User Interface.
                        UI.GUI ui = new UI.GUI();
                        String pwd = request.getParameter("password1");

                        //If the user has clicked the submit button
                        if (request.getParameter("submit") != null) {
                            //If the form is valid
                            if (!checker.validate()) {
                                //print out the 'error' form
                                out.print(ui.generateRegGUI(checker));
                                //if the form is valid
                            } else {
                                
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
                                
                                
                                out.print("You have been registered");
                                //Insert the form into the database
                                checker.get_SHA_256_SecurePassword(pwd);
                                checker.insertQuery();
                                String firstName = request.getParameter("firstName");
                                String lastName = request.getParameter("lastName");
                                String id = request.getParameter("ID");
                                String student = "student";
                                session.setAttribute("firstName", firstName);
                                session.setAttribute("lastName", lastName);
                                session.setAttribute("p_type", student);
                                session.setAttribute("id", id);
                                response.sendRedirect("scheduler.jsp");
                            }
                            
                            
                        } else {
                            //print the standard form
                            out.print(ui.generateRegGUI());
                        }
                    %>

                </div>
            </div>
        </div>	 
    </div>

</body>
</html>


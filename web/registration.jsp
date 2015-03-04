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


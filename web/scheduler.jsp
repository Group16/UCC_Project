<%-- 
    Document   : scheduler
    Created on : 27-Feb-2015, 11:08:14
    Author     : murphy
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="java.util.ArrayList"%>
<%@page import="control.MeetingChecker"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.json.simple.JSONObject"%>";

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="MarkUp/images/logoUC.ico">		
        <meta name="description" content="UCC CONNECT: Scheduling Software" />
        <meta name="keywords" content="connect, scheduling, ucc" />
        <meta name="author" content="Colm Murphy" />
        <title>UCC Connect | Schedules</title>

        <!-- Bootstrap -->
        <!-- Imported through CSS -->>
        <link href="MarkUp/css/style.css" rel="stylesheet">




        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">	


            <a href="#" class="navbar-brand">UCC CONNECT<img src="MarkUp/images/logoUC.ico" height="29px" /></a>
            <div class="navbar-header">
                <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse" >
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>	
            </div>﻿

            <div class="collapse navbar-collapse navHeaderCollapse">

                <ul class="nav navbar-nav navbar-right"> 				
                    <li><a href="#">UCC</a></li>
                    <li><a href="#">Features</a></li>
                    <li><a href="http://localhost:8080/UCC_Scheduler_Program/logout.jsp">Logout</a></li>
                    <li><a href="https://mohittare.wordpress.com/2013/07/28/using-fullcalendarwithjava/">Help</a></li>															
                </ul>							
            </div>	
        </div> <!-- div .navbar navbar-inverse navbar-fixed-top-->

        <!-- container class for three feture icons and paragraphs -->	
        <div class="container" id="scheduler_container">
            <div class="row">
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12 text-center pull-left">



                    <%
                        UI.GUI ui = new UI.GUI();
                        if (session.getAttribute("lastName") == null) {
                            response.sendRedirect("index.jsp");
                        } else {
                            String firstName;
                            String lastName;
                            firstName = session.getAttribute("firstName").toString();
                            lastName = session.getAttribute("lastName").toString();
                            out.println("</br> Welcome " + firstName + " " + lastName);
                    %>
                    <div class="btn-group dropdown">
                        <button type="button" data-toggle="dropdown" class="btn btn-primary dropdown-toggle">Arrange A Meeting ? <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <% out.println(ui.generateMeetingGUI());%>
                        </ul>
                    </div>

                   
                            <% 
                            
                                if (session.getAttribute("p_type").equals("lecturer")) {
                                    out.println(ui.tutorialButton());
                                    }
                                }
                            %>
                  

                    

                    <form name='form' action='welcome.jsp' method='POST'>

                </div>

                <div class="col-lg-10 col-md-10 col-sm-10 text pull-right">
                    <div id="calendar"></div>
                </div>

            </div><!--row-->

        </div>

        <div class="modal " id="createEventModal" role="dialog" aria-hidden="true">
            <div class = "modal-dialog">
                <div class="modal-content">
                    <div class="modal-header"> 
                        <p>Create Your Event !</p>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

                    </div>
                    <div class="modal-body">
                        <form id="createAppointmentForm" class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label" for="eventTitle"></label>
                                <div>
                                    Event Title<input type='text' name='eventTitle' id='eventTitle' placeholder='&nbsp;e.g. DataBase Tutorial' />
                                </div>

                                <label >Location:</label>
                                <div class="controls">
                                    <input type="text" name="patientName" id="patientName"  placeholder='&nbsp;e.g. WGB 1.07' />
                                    <input type="hidden" id="apptStartTime"/>
                                    <input type="hidden" id="apptEndTime"/>
                                    <input type="hidden" id="apptAllDay" />
                                </div>
                            </div>
                            <div class="control-group">


                                <label for="from">From</label>
                                <div class="controls controls-row" id="from" >
                                </div>  

                                <label  for="to">To</label>
                                <div class="controls controls-row" id="to">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                        <button type="submit" class="btn btn-primary" id="submitButton">Save</button>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            UCC CONNECT - A Software Engineering Project &copy; Group 16
        </footer>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!--  Load Google hosted jQuery OR local copy if offline -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>       
        <script>window.jQuery || document.write('<script src="MarkUp/js/jquery.min.js"><\/script>');</script>

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="MarkUp/js/moment.min.js"></script> 
        <script src="MarkUp/js/fullcalendar.min.js"></script>

        <script src="MarkUp/js/bootstrap.min.js"></script>
 
        <script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
        <script type="text/javascript">


            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                defaultTimedEventDuration: "01:00:00",
                events: 'json.jsp'
            });


            $(function( ) {
                $(".datepicker").datepicker({dateFormat: "yy/mm/dd"});
            });


            $(function( ) {
                $(".timepicker").timepicker({timeFormat: "H:i:s"});
            });
        </script>


    </body>
</html>


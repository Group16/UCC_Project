<%-- 
    Document   : scheduler
    Created on : 27-Feb-2015, 11:08:14
    Author     : murphy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="control.MeetingChecker"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.SQL"%>
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


            <a href="#" class="navbar-brand">UCC CONNECT</a>	
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


                    <div class="btn-group dropdown">
                        <button type="button" data-toggle="dropdown" class="btn btn-primary dropdown-toggle">Your Calenders <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="#">Acedemic</a></li>
                            <li><a href="#">Personal</a></li>                      

                        </ul>
                    </div>
                    
                        <%
                                if (session.getAttribute("lastName") == null) {
                                    response.sendRedirect("index.jsp");
                                } else {
                                    String firstName;
                                    String lastName;
                                    firstName = session.getAttribute("firstName").toString();
                                    lastName = session.getAttribute("lastName").toString();
                                    out.println("You are logged in. Welcome " + firstName + " " + lastName);

                                    if (request.getParameter("meetingSubmit") != null) {
                                        response.sendRedirect("meeting.jsp");
                                    }

                                    if (request.getParameter("notificationSubmit") != null) {
                                        response.sendRedirect("notification.jsp");
                                    }



                            %>
                            
                            <form name='form' action='welcome.jsp' method='POST'>
                                <input type="submit" name="meetingSubmit" value="Arrange a meeting"/>

                                <input type="submit" name="notificationSubmit" value="Notifcations"/>

                                <%
                                    if (session.getAttribute("p_type").equals("lecturer")) {
                                %><input type="submit" name="tutorialSubmit" value="Arrange a Tutorial"/><%

                                               if (request.getParameter("tutorialSubmit") != null) {
                                                   response.sendRedirect("tutorial.jsp");
                                               }
                                           }
                                       }
                                %>
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

        <script type="text/javascript">

            var matchingDaysBetween = function(start, end, test) {
                var days = [];
                for (var day = moment(start); day.isBefore(end); day.add(1, 'd')) {
                    if (test(day)) {
                        days.push(moment(day)); // push a copy of day
                    }
                }
                return days;
            }
            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                eventSources: [{
                        // Every sunday as a background event
                        events: function(start, end, timezone, callback) {

                            // Get the days
                            var days = matchingDaysBetween(start, end, function(day) {
                                return day.format('dddd') === 'Sunday'; //test function
                            });

                            // Map days to events
                            callback(days.map(function(day) {
                                return {
                                    start: moment(day).startOf('day'),
                                    end: moment(day).endOf('day'),
                                    title: "sunday",
                                    rendering: 'background'
                                };
                            }));
                        }
                    }, {
                        //Every tuesday noon to 2pm
                        events: function(start, end, timezone, callback) {
                            var days = matchingDaysBetween(start, end, function(day) {
                                return day.format('dddd') === 'Tuesday'; //test function
                            });
                            callback(days.map(function(day) { // map days to events
                                return {
                                    start: moment(day).hour(12),
                                    end: moment(day).hour(14),
                                    title: "lunch",
                                };
                            }));
                        }
                    }]
            });
                
        </script>
        
        <%
                 ArrayList<MeetingChecker> meetings = new ArrayList<MeetingChecker>();
                 
                 
                 Statement statementObject;
                 Connection connectionObject;
                 connectionObject = DriverManager.getConnection("jdbc:mysql://"+"cs1.ucc.ie"+"/" + "2016_mm37", "mm37", "uohongah");
                 database.DbClass db = new database.DbClass();
                 
                 try{
                    statementObject = connectionObject.createStatement();
                    ResultSet statementResult = statementObject.executeQuery("SELECT * FROM meetings AS m JOIN people_in_meetings pm WHERE pm.p_id = '" +session.getAttribute("id")+ "' AND m.date BETWEEN '" +"2015/03/02"+ "' AND '" +"2015/03/11"+ "'");
                    
                    while(statementResult.next()){
                        
                        String m_id  = statementResult.getString(0);
                        String time  = statementResult.getString(2);
                        String startDate  = statementResult.getString(3);
                        String location = statementResult.getString(4);
                        String recurring  = statementResult.getString(5);
                        String endDate  = statementResult.getString(6);
                        String type  = statementResult.getString(7);
                        String description  = statementResult.getString(8);
                        
                        MeetingChecker meeting = new MeetingChecker(m_id, time, startDate, location, recurring, endDate, type, description);
                    }
                    
                 }catch(Exception E){
                     
                 }
                 
        
        
        %>

    </body>
</html>


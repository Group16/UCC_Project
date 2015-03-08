<!-- Index.jsp Group 16  -->
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<jsp:useBean id="checker" class="control.CheckerClass" scope="session" />
<jsp:setProperty name="checker" property="*"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="MarkUp/images/logoUC.ico">		
        <meta name="description" content="UCC CONNECT: Scheduling Software" />
        <meta name="keywords" content="connect, scheduling, ucc" />
        <meta name="author" content="Colm Murphy" />
        <title>UCC Connect | Home</title>

        <!-- Bootstrap -->
        <!-- Imported through CSS -->
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
                    <li><a href="#login" data-toggle="modal">Login</a></li>
                    <li><a href="#">Help</a></li>															
                </ul>							
            </div>	
        </div> <!-- End div .navbar navbar-inverse navbar-fixed-top-->

        <!-- container class for landing page and icons -->	
        <div class="container">				
            <div class="landing">
                <p>A simple & elegant way to <b>connect your world</b></p>
                <a href="#register" data-toggle="modal">Get Started</a>
                <img src="MarkUp/images/cpus.png" alt="Icon depicting multiple devices">
            </div>				
        </div> <!-- END container  -->

        <div class="studentlinks">
            <div class="container">
                <div class="logoshold text-center">
                    <a href="http://cs4.ucc.ie/moodle/"><img src="MarkUp/images/moodle-logo.png" width="150" height="auto" alt="Moodle" ></a>
                    <a href="http://www.collegeroad.ie/"><img src="MarkUp/images/SU_logo.png" width="170" height="auto" alt="Student Union" ></a>
                    <a href="http://www.ucc.ie/en/"><img src="MarkUp/images/UCC.png" width="100" height="auto" alt="UCC" ></a>
                </div><!--logos-->
            </div><!--container-->
        </div><!--studentlinks-->

        <div class="bannertext">
            <div class="container">			
                <h1>Not convinced yet ?  check out some of our features to see what we can do for <strong>you!</strong></h1>	
                <hr id="jump"> <a href="#jump" class="godown"><span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></a>
            </div><!--container-->
        </div><!--bannertext-->

        <!-- container class for three feture icons and paragraphs -->	
        <div class="container" >
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-5 col-xs-12 text-center pull-right">
                    <img src="MarkUp/images/newworldmap.jpg"  alt="Icon depicting world map with connection icons"class="img-responsive fadeUp_animate fadeInUp">
                </div>
                <div class="col-lg-6 col-md-6 col-sm-7 text pull-left">
                    <h2>Create schedules and connections with your colleagues</h2>
                    <p class="text-muted">UCC Connect helps you take back control.</p>
                    <p>
                        Use your personal timetable to arrange your week & see what times your are available. 
                        Or select from a list of contacts to arrange meetings. Use Ucc Connect to find out if your contacts are
                        also free and make a schedule request. Simple.
                    </p>	
                    <div class="buttons-hold">
                        <button type="button" class="btn btn-success">Learn More</button>
                    </div><!--buttons-->
                </div><!--Left-->
            </div><!--row-->
            <hr>

            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-5 col-xs-12 text-center pull-left">
                    <img src="MarkUp/images/newcalender.jpg"  alt="Icon depicting Scheduling calender" class="lazy-load img-responsive fadeUp_animate fadeInUp">
                </div>

                <div class="col-lg-6 col-md-6 col-sm-7 text pull-right">
                    <h2>Schedules, schedules &amp; schedules.</h2>
                    <p class="text-muted">Remove the hassle by organising in one place.</p>
                    <p>
                        Quickly create and edit personal and academic time tables for your own use. Allow others to connect 
                        to your schedule and arrange meetings , tutorial or even group meetings. 
                    </p>
                    <div class="buttons-hold">
                        <button type="button" class="btn btn-success">Learn More</button>
                    </div><!--buttons-->
                </div><!--Left-->

            </div><!--row-->
            <hr>

            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-5 col-xs-12 text-center pull-right">
                    <img src="MarkUp/images/clound.jpg"  alt="Icon depicting security" class="lazy-load img-responsive fadeUp_animate fadeInUp">
                </div>
                <div class="col-lg-6 col-md-6 col-sm-7 text pull-left">
                    <h2>Safe & Secure</h2>
                    <p class="text-muted">The correct care given to your personal information.</p>
                    <p>
                        You define the access that others have to your schedule. Unless you have approved
                        a connection, your details are private. Only by being granted permission can someone search 
                        your free time. 
                    </p>
                    <div class="buttons-hold">
                        <button type="button" class="btn btn-success">Learn More</button>
                    </div><!--buttons-->
                </div><!--Left-->
            </div><!--row-->
        </div>

        <!-- Modal login window -->	
        <div class="modal " id="login" role="dialog">
            <div class = "modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <p>Log in </p> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>

                    <div class="modal-body">
                        <!--  login-->
                        <%
                            //Calling the User Interface.
                            UI.GUI ui = new UI.GUI();
                            out.println(ui.generateLogGUI());
                            String studentID = request.getParameter("studentID");
                            String pwd = request.getParameter("pword");
                            database.DbClass db = new database.DbClass();
                            db.setup();
                            
                            //if the subnit button is pressed
                            if (request.getParameter("submit") != null) {
                                db.checkQuery("select * from people where p_id='" + studentID + "' and password = '" + checker.get_SHA_256_SecurePassword(pwd) + "'");
                                if (db.queryCorrect == true) {
                                    String[] data = db.SelectRow("select * from people where p_id='" + studentID + "' and password = '" + checker.get_SHA_256_SecurePassword(pwd) + "'");
                                    String firstName = data[2];
                                    String lastName = data[3];
                                    String id = data[0];
                                    String p_type = data[1];
                                    
                                    //set attributes
                                    session.setAttribute("firstName", firstName);
                                    session.setAttribute("lastName", lastName);
                                    session.setAttribute("id", id);
                                    session.setAttribute("p_type", p_type);
                                    response.sendRedirect("scheduler.jsp");

                                } else {
                                    out.println("Wrong username/password");
                                }
                            }
                        %>

                        <form id="forgot" style="display:none;">
                            <label>Enter your email address and we will email your a new one !<input type="text" name="username" id="username" placeholder="example@email.com" /></label>
                            <div class="action_btns">
                                <button type="button" class="btn btn-success">Send</button>
                            </div>
                        </form>	
                    </div>
                </div>	 
            </div>			
        </div>

        <!-- Modal registration window -->	
        <div class="modal " id="register" role="dialog">
            <div class = "modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <p>Register</p>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>

                    <div class="modal-body">                       
                        <%
                            //If the user has clicked the submit button
                            if (request.getParameter("submit") != null) {
                                //If the form is valid
                                if (!checker.validate()) {
                                    //print out the 'error' form
                                    out.print(ui.generateRegGUI(checker));
                                    //if the form is valid
                                } else {
                                    //set up database
                                    db.setup();
                                    String[] courseModules = request.getParameterValues("module");
                                    out.print("You have been registered");
                                    checker.get_SHA_256_SecurePassword(pwd);
                                    checker.insertQuery();
                                    
                                    //Get inputs
                                    String firstName = request.getParameter("firstName");
                                    String lastName = request.getParameter("lastName");
                                    String id = request.getParameter("ID");
                                    String p_type = "student";
                                    
                                    //set attributes
                                    session.setAttribute("firstName", firstName);
                                    session.setAttribute("lastName", lastName);
                                    session.setAttribute("id", id);
                                    session.setAttribute("p_type", p_type);
                                    response.sendRedirect("scheduler.jsp");

                                    if (courseModules != null) {
                                        for (int i = 0; i < courseModules.length; i++) {
                                            db.Insert("INSERT INTO people_in_modules (p_id, mod_id) VALUES ('" + session.getAttribute("id") + "','" + courseModules[i] + "');");
                                        }

                                        response.sendRedirect("scheduler.jsp");
                                    } else {
                                        out.println("Please Check Some Boxes");
                                    }
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
        <footer>
            UCC CONNECT - A Software Engineering Project &copy; Group 16
        </footer>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!--  Load Google hosted jQuery OR local copy if offline -->
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
    </body>
</html>

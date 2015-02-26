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
                    <li><a href="#login" data-toggle="modal">Login</a></li>
                    <li><a href="#">Help</a></li>															
                </ul>							
            </div>	
        </div> <!-- div .navbar navbar-inverse navbar-fixed-top-->

        <!-- container class for landing page and icons -->	
        <div class="container">				
            <div class="landing">
                <p>A simple & elegant way to <b>connect your world</b></p>

                <a href="#register" data-toggle="modal">Get Started</a>

                <img src="MarkUp/images/cpus.png" alt="Icon depicting multiple devices">
            </div>				
        </div>


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
                <h1>Not convinced yet? check out some of our features to see what we can do for <strong>you!</strong></h1>	
                <hr>	

            </div><!--container-->
        </div><!--bannertext-->

        <!-- container class for three feture icons and paragraphs -->	
        <div class="container">



            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-5 col-xs-12 text-center pull-right">
                    <img src="MarkUp/images/map.png"  alt="Icon depicting world map with connection icons"class="img-responsive fadeUp_animate fadeInUp">
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
                    <img src="MarkUp/images/schedule.png"  alt="Icon depicting Scheduling calender" class="lazy-load img-responsive fadeUp_animate fadeInUp">
                </div>

                <div class="col-lg-6 col-md-6 col-sm-7 text pull-right">
                    <h2>Schedules, schedules &amp; schedules.</h2>
                    <p class="text-muted">Remove the hassle by organising in one place.</p>
                    <p>
                        Quickly create and edit personal and academic time tables for your own use. Allow others to connect 
                        to your schedule to arrange meetings , tutorial or even group meetings. 
                    </p>
                    <div class="buttons-hold">
                        <button type="button" class="btn btn-success">Learn More</button>
                    </div><!--buttons-->
                </div><!--Left-->

            </div><!--row-->
            <hr>

            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-5 col-xs-12 text-center pull-right">
                    <img src="MarkUp/images/security.png"  alt="Icon depicting security" class="lazy-load img-responsive fadeUp_animate fadeInUp">
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

                        <%

                            //Calling the User Interface.
                            UI.GUI ui = new UI.GUI();
                            out.println(ui.generateLogGUI());
                            String studentID = request.getParameter("studentID");
                            String pwd = request.getParameter("pword");
                            database.DbClass db = new database.DbClass();
                            db.setup("cs1.ucc.ie", "2016_mm37", "mm37", "uohongah");

                            if (request.getParameter("submit") != null) {
                                db.checkQuery("select * from people where p_id='" + studentID + "' and password = '" + checker.get_SHA_256_SecurePassword(pwd) + "'");
                                if (db.queryCorrect == true) {
                                    String[] data = db.SelectRow("select * from people where p_id='" + studentID + "' and password = '" + checker.get_SHA_256_SecurePassword(pwd) + "'");

                                    String firstName = data[2];
                                    String lastName = data[3];
                                    String id = data[0];
                                    session.setAttribute("firstName", firstName);
                                    session.setAttribute("lastName", lastName);
                                    session.setAttribute("id", id);
                                    response.sendRedirect("welcome.jsp");

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
                            EmailSender.Email email = new EmailSender.Email();
                            //Calling the User Interface
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
                                    session.setAttribute("firstName", firstName);
                                    session.setAttribute("lastName", lastName);

                                    response.sendRedirect("welcome.jsp");
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

        <!-- Modal more registration details window -->	
        <div class="modal" id="registerdetail" role="dialog">
            <div class = "modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <p>Register</p>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>

                    <div class="modal-body">
                        <form action="none.php" method="POST">

                            <select class="div-toggler" data-target=".streams">
                                <option value="">Choose Your Computer Science Stream</option>
                                <option value="CK401" >CK401 Computer Science</option>
                            </select>
                            </br>
                            <select class="div-toggler" data-target=".streams">
                                <option value="">Choose Your Computer Science Stream</option>
                                <option value="compSciValue" data-show=".compSci">BSc in Computer Science</option>
                                <option value="webSciValue" data-show=".webSys">BSc in Computer Science (Web Systems Engineering)</option>

                            </select>

                            <div class="streams">
                                <div class="compSci hide">
                                    <label>CS3300 Work Placement<input type="checkbox" name="module" value="CS3300" /></label></br>
                                    <label>CS3301 Work Placement<input type="checkbox" name="module" value="CS3301" /></label></br>
                                    <label>CS3306 Workplace Technology and Skills<input type="checkbox" name="module" value="CS3306" /></label></br>
                                    <label>CS3311 Middleware<input type="checkbox" name="module" value="CS3311" /></label><br>
                                    <label>CS3500 Software Engineering<input type="checkbox" name="module" value="CS3500" /></label></br>
                                    <label>CS3505 Web Systems Team Project<input type="checkbox" name="module" value="CS3505" /></label></br>
                                    <label>CS3506 Networks and Data Communications<input type="checkbox" name="module" value="CS3506" /></label></br>
                                    <label>CS3508 Algorithms and Non-linear Data Structures<input type="checkbox" name="module" value="CS3508" /></label></br>
                                    <label>CS3509 Theory of Computation<input type="checkbox" name="module" value="CS3509" /></label></br>
                                    <label>CS3514 C-Programming for Microcontrollers<input type="checkbox" name="module" value="CS3514" /></label></br>
                                </div>
                                <div class="webSys hide">
                                    <label>CS3300 Work Placement<input type="checkbox" name="module" value="CS3300" /></label></br>
                                    <label>CS3301 Work Placement<input type="checkbox" name="module" value="CS3301" /></label></br>
                                    <label>CS3306 Workplace Technology and Skills<input type="checkbox" name="module" value="CS3306" /></label></br>
                                    <label>CS3311 Middleware<input type="checkbox" name="module" value="CS3311" /></label></br>
                                    <label>CS3500 Software Engineering<input type="checkbox" name="module" value="CS3500" /></label></br>
                                    <label>CS3505 Web Systems Team Project<input type="checkbox" name="module" value="CS3505" /></label></br>
                                    <label>CS3510 Advanced Server-Side Programming<input type="checkbox" name="module" value="CS3510" /></label></br>
                                    <label>CS3511 Web Security<input type="checkbox" name="module" value="CS3511" /></label></br>
                                    <label>CS3512 Advanced XML Technologies<input type="checkbox" name="module" value="CS3512" /></label></br>
                                    <label>CS3513 Client-side Programming<input type="checkbox" name="module" value="CS3513" /></label></br>

                                </div>
                            </div>
                            <div class="action_btns">
                                <a href="pages/scheduler.html"><button type="button" class="btn btn-success">Continue</button></a>
                            </div>
                        </form>
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
<%-- 
    Document   : scheduler
    Created on : 27-Feb-2015, 11:08:14
    Author     : murphy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="images/logoUC.ico">		
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
                    <li><a href="#login" data-toggle="modal">Login</a></li>
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
                </div>

                <div class="col-lg-10 col-md-10 col-sm-10 text pull-right">
                    <div id='calendar'>
                        <!--calendar-->
                    </div>
                </div>

            </div><!--row-->

        </div>


        <footer>
            UCC CONNECT - A Software Engineering Project &copy; Group 16

        </footer>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!--  Load Google hosted jQuery OR local copy if offline -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>       
        <script>window.jQuery || document.write('<script src="MarkUp/js/jquery.min.js"><\/script>');</script>

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="MarkUp/js/bootstrap.min.js"></script>
        <script src='MarkUp/js/moment.min.js'></script>
        <script src='MarkUp/js/jquery.min.js'></script>
        <script src='MarkUp/js/fullcalendar.min.js'></script>

        <script type="text/javascript">
    

            $(document).ready(function () {

                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'agendaWeek,agendaDay,month'
                    },
                    defaultDate: '2015-02-12',
                    selectable: true,
                    selectHelper: true,
                    height: 846,
                    
                    select: function (start, end) {
                        var title = prompt('Meeting Event');
                        var eventData;
                        if (title) {
                            eventData = {
                                title: title,
                                start: start,
                                end: end
                            };
                            $('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
                        }
                        $('#calendar').fullCalendar('unselect');
                    },
                    editable: true,
                    eventLimit: true, // allow "more" link when too many events

                });

            });



        </script>

    </body>
</html>


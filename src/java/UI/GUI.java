package UI;
import control.CheckerClass;
import control.MeetingChecker;

/**
 * A class used to output html and check input of forms
 *
 * @author Michael Mullarkey(112457292)
 */
public class GUI {

    // string varaible which ouputs html
    private String result;

    /**
     * A constructor which sets variable to stop null pointers
     */
    public GUI() {
        result = "";
    }

    /**
     * A method for outputting html and checking form input
     *
     * @param checker takes in checker from index.jsp
     * @return returns html form
     */
    public String generateRegGUI(CheckerClass checker) {
        result = "<form name='form'id='forms' action='registration.jsp' method='POST'>";

        //Date: If the date is empty or the date is incorrect format
        if (checker.getDate().equals("") || checker.correctDate == false) {
            result += "<input type='hidden' name='date' value='1993/12/15' />";
            //if not empty and true
        } else {
            result += "<input type='hidden' name='date' value='" + checker.getDate() + "' />";
        }
        // First Name
        if (checker.getFirstName().equals("") || checker.correctFirstName == false) {
            result += "<label>First Name*<input type='text' name='firstName' id='firstname' placeholder='John' /></label>";
            //if not empty and true
        } else {
            result += "<label>First Name*<input type='text' name='firstName' id='firstname'  value='" + checker.getFirstName() + "' /></label>";
        }
        //Last Name
        if (checker.getLastName().equals("") || checker.correctLastName == false) {
            result += "<label>Last Name*<input type='text' name='lastName' id='LastNamre' placeholder='Doe' /></label>";
            //if not empty and true
        } else {
            result += "<label>Last Name*<input type='text' name='lastName' id='LastNamre' value='" + checker.getLastName() + "' /></label>";
        }

        //Student Number: If the student ID is empty or the ID is incorrect format
        if (checker.getID().equals("") || checker.correctID == false) {
            result += "<label>Student Number* <input type='text' name='ID' id='studentNumber' placeholder='123456789' /></label>";
            //if not empty and true
        } else {
            result += "<label>Student Number* <input type='text' name='ID'  value='" + checker.getID() + "' /></label>";
        }

        //Email: If the email is empty or the email is incorrect format
        if (checker.getEmail().equals("") || checker.correctEmail == false) {
            result += "<label>Email Address<input type='text' name='email' id='email' placeholder='1234@gmail.com' /></label>";
            //if not empty and true
        } else {
            result += "<label>Email Address<input type='text' name='email' id='email' value='" + checker.getEmail() + "' /></label>";
        }

        if (checker.getPassword1().equals("") || checker.correctPassword1 == false) {
            result += "<label>Password<input type='password' name='password1' id='password1' /></label>";
            //if not empty and true
        } else {
            result += "<label>Password<input type='password' name='password1' id='password1' value='" + checker.getPassword1() + "'/></label>";
        }

        if (checker.getPassword2().equals("") || checker.correctPassword2 == false) {
            result += "<label>Re-Enter Password<input type='password' name='password2' id='password2' /></label>";
            //if not empty and true
        } else {
            result += "<label>Re-Enter Password<input type='password' name='password2' id='password2' value='" + checker.getPassword2() + "'/></label>";
        }
        result += "<input type='submit' name='submit' />";
        result += "</form>";
        
        //Sets the errors array to new array
        String[] list = checker.getErrors();
        //a enhanced for loop to loop through everything in list
        for (String y : list) {
            //print out erros
            result += "<p>" + y + "</p>";
        }
        //reurns html form
        return result;
    }

    /**
     * Method to create variable to hold HTML form.
     *
     * Registration Form 1
     *
     * @return result
     */
    public String generateRegGUI() {

        result = "<form name='form' action='registration.jsp' method='POST'>"; // start of form 
        result += "<input type='hidden' name='date' value='1993/12/15' />"; // hidden date field
        result += "<label>First Name*<input type='text' name='firstName' id='firstname' placeholder='John' required /></label>"; // first name 
        result += "<label>Last Name*<input type='text' name='lastName' id='secondname' placeholder='Doe' required /></label>";   //last name 
        result += "<label>Student Number* <input type='text' name='ID' id='studentNumber' placeholder='123456789' required/></label>";   // Student Number 
        result += "<label>Email Address<input type='text' name='email' id='email' placeholder='1234@gmail.com' required /></label>"; // email address
        result += "<label>Password<input type='password' name='password1' id='password1' required /></label>"; // password 
        result += "<label>Re-Enter Password<input type='password' name='password2' id='password2'  required/></label>"; // reenter password
        result += "<div class='action_btns'>";
        result += "</div>";
        result += "<div class='action_btns'>";
        result += "<a href='#login' data-toggle='modal' data-dismiss='modal'>Already have an account?</a>";
        result += "</div>";
        result += " <input type='submit' name='submit' />";
        result += " </form>";
        //returns html form
        return result;
    }

    /**
     * Method to create course registration HTML form
     *
     * @return form
     */
    public String generateReg2GUI() {

        result = "<form name='form' id='forms' action='addmodules.jsp' method='POST'>";
        result += "<select class='div-toggler' data-target='.streams'>";
        result += "<option value=''>Choose Your Computer Science Stream</option>";
        result += "<option value='compSciValue' data-show='.compSci'>BSc in Computer Science</option>";
        result += "<option value='webSciValue' data-show='.webSys'>BSc in Computer Science (Web Systems Engineering)</option>";
        result += "</select>";
        result += "<div class='streams'>";
        result += "<div class='compSci hide'>";
        result += " <label>CS3300 Work Placement<input type='checkbox' name='module' value='CS3300' /></label></br>";
        result += " <label>CS3301 Work Placement<input type='checkbox' name='module' value='CS3301' /></label></br>";
        result += "<label>CS3306 Workplace Technology and Skills<input type='checkbox' name='module' value='CS3306' /></label></br>";
        result += " <label>CS3311 Middleware<input type='checkbox' name='module' value='CS3311' /></label><br>";
        result += " <label>CS3500 Software Engineering<input type='checkbox' name='module' value='CS3500' /></label></br>";
        result += " <label>CS3505 Web Systems Team Project<input type='checkbox' name='module' value='CS3505' /></label></br>";
        result += " <label>CS3506 Networks and Data Communications<input type='checkbox' name='module' value='CS3506' /></label></br>";
        result += " <label>CS3508 Algorithms and Non-linear Data Structures<input type='checkbox' name='module' value='CS3508' /></label></br>";
        result += " <label>CS3509 Theory of Computation<input type='checkbox' name='module' value='CS3509' /></label></br>";
        result += " <label>CS3514 C-Programming for Microcontrollers<input type='checkbox' name='module' value='CS3514' /></label></br>";
        result += " </div>";
        result += " <div class='webSys hide'>";
        result += " <label>CS3300 Work Placement<input type='checkbox' name='module' value='CS3300' /></label></br>";
        result += " <label>CS3301 Work Placement<input type='checkbox' name='module' value='CS3301' /></label></br>";
        result += " <label>CS3306 Workplace Technology and Skills<input type='checkbox' name='module' value='CS3306' /></label></br>";
        result += " <label>CS3311 Middleware<input type='checkbox' name='module' value='CS3311' /></label></br>";
        result += "<label>CS3500 Software Engineering<input type='checkbox' name='module' value='CS3500' /></label></br>";
        result += "<label>CS3505 Web Systems Team Project<input type='checkbox' name='module' value='CS3505' /></label></br>";
        result += " <label>CS3510 Advanced Server-Side Programming<input type='checkbox' name='module' value='CS3510' /></label></br>";
        result += " <label>CS3511 Web Security<input type='checkbox' name='module' value='CS3511' /></label></br>";
        result += " <label>CS3512 Advanced XML Technologies<input type='checkbox' name='module' value='CS3512' /></label></br>";
        result += " <label>CS3513 Client-side Programming<input type='checkbox' name='module' value='CS3513' /></label></br>";
        result += " </div>";
        result += " </div>";
        result += " <div class='action_btns'>";
        result += " <input type='submit' class='btn btn-success' name='submit' value='Finish' />";
        result += " </div>";
        result += " </form>";

        return result;
    }

    /**
     * A method for setting up html form
     *
     * @return html form
     */
    public String generateLogGUI() {

        result = "<form id='log' name='form' action='index.jsp' method='POST'>";
        result += "<label>Student Number<input type='text' name='studentID' id='username' placeholder='123456789' /></label>";
        result += "<label>Password<input type='password' name='pword' id='passwordlog' /></label>";

        result += "<div class='action_btns'>";
        result += "<button type='submit' name='submit' class='btn btn-success'>Login</button>";
        result += "<a href='#register' data-toggle='modal' data-dismiss='modal'>Or sign up today</a>";
        result += "</div>";

        result += "<div class='action_btns'>";
        result += "<button type='button' id='forgotbutton'class='btn btn-warning'>Forgot Your Password ? </button>";
        result += "</div>";
        result += "</form>";

        result += "<form id='forgot' style='display:none;'>";
        result += "<label>Enter your email address and we will email your a new one !<input type='text' name='username' id='username' placeholder='example@email.com' /></label>";
        result += "<div class='action_btns'>";
        result += "<button type='button' class='btn btn-success'>Send</button>";
        result += "</div>";
        result += "</form>";

        return result;
    }
    
    /**
     * To check and valadate form entries for meetings
     * <p>
     * To test if form input is valid, if not return the form with the correct input entered held in the form.
     * @param meeting
     * @return html form 
     */
    public String generateMeetingGUI(MeetingChecker meeting) {

        result = "<form name='form'id='forms' action='meeting.jsp' method='POST'>";
        result += "<h1>Arrange A Meeting</h1><hr>";
        if (meeting.correctRecipient == false) {
            result += "ID of person(s) you wish to meetwith <input type='text' name='recipient' placeholder='Enter a users ID' />";
        } else {
            result += "ID of person(s) you wish to meet with <input type='text' name='recipient' value='" + meeting.getRecipient() + "' />";
        }
        if (meeting.correctStartDate == false) {
            result += "Start date<input type='text' name='startDate' class='datepicker' placeholder='2014/02/09' />";
        } else {
            result += "Start date<input type='text' name='startDate' class='datepicker' value='" + meeting.getStartDate() + "' />";
        }

        if (meeting.correctLocation == false) {
            result += "Location <input type='text' name='location' placeholder='WGB G.01' />";

        } else {
            result += "Location <input type='text' name='location' value='" + meeting.getLocation() + "' />";
        }

        result += "Recurring<select name='recurring'>\n"
                + "  <option value='none'>None</option>\n"
                + "  <option value='daily'>Daily</option>\n"
                + "  <option value='weekly'>Weekly</option>\n"
                + "  <option value='fortnight'>Fortnightly</option>\n"
                + "  <option value='monthly'>Monthly</option>\n"
                + "</select>";

        if (meeting.correctDescription == false) {
                    result += "<label>Title:<input type='text' name='description' placeholder='Insert meeting description here...' /></label>";

        } else {
            result += "<label>Title:<input type='text' name='description' placeholder='" + meeting.getDescription() + "' /></label>";
        }

        result += "<input type='submit' name='submit' />";
        result += "</form>";

        String[] list = meeting.getErrors();
        //a enhanced for loop to loop through everything in list
        for (String y : list) {
            //print out erros
            result += "<p>" + y + "</p>";
        }
        //returns html form
        return result;
    }
    
    /**
     * HTML form for creating a meeting 
     * <p>
     * Form for the creation of meeting, and to set and gain time and date information 
     * for use on the calender.
     * @return html form
     */
    public String generateMeetingGUI() {

        result = "<form name='form'id='forms' action='meeting.jsp' method='POST'>";        
        result += "<div class='form-group'>";
        result += " <label for='description'>Title</label>";
        result += " <input id='description' type='text' name='description' placeholder='Insert meeting description here...' />";
        result += "</div>";        
        result += "<div class='form-group'>";
        result += " <label for='recipient'>Person(s) ID Number</label>";
        result += " <input id='recipient' type='text' name='recipient' placeholder='Enter a users ID' />";
        result += "</div>";        
        result += "<div class='form-group'>";
        result += " <label for='startDate'>Start Date</label>";
        result += " <input id='startDate' class='datepicker' type='text' name='startDate' placeholder='2015/02/08' />";
        result += "</div>";        
        result += "<div class='form-group'>";
        result += " <label for='location'>Location</label>";
        result += " <input id='location' type='text' name='location' placeholder='WGB G.01' />";
        result += "</div>";        
        result += "Recurring <select name='recurring'>";
        result += "  <option value='none'>None</option>";
        result += "  <option value='daily'>Daily</option>";
        result += "  <option value='weekly'>Weekly</option>";
        result += "  <option value='fortnight'>Fortnightly</option>";
        result += "  <option value='monthly'>Monthly</option>";
        result += "</select>";
        result += "<input class='btn btn-success' type='submit' name='submit' />";

        //returns html form
        return result;
    }

    /**
     * To check and valadate form entries for tutorials
     * <p>
     * To test if form input is valid, if not return the form with the correct input entered held in the form.
     * @param meeting
     * @return html form 
     */
    public String generateTutorialGUI(MeetingChecker meeting) {

        result = "<form name='form' id='forms' action='tutorial.jsp' method='POST'>";
        result += "<h1>Arrange A Meeting</h1><hr>";
        if (meeting.correctRecipient == false) {
            result += "Group to set tutorial with<input type='text' name='recipient' placeholder='Enter a users ID' />";
        } else {
            result += "Person you wish to meet <input type='text' name='recipient' value='" + meeting.getRecipient() + "' />";
        }
        if (meeting.correctStartDate == false) {
            result += "Start date<input type='text' class='datepicker' name='startDate' placeholder='2014/02/09' />";
        } else {
            result += "Start date<input type='text' class='datepicker' name='startDate' value='" + meeting.getStartDate() + "' />";
        }

        if (meeting.correctLocation == false) {
            result += "Location <input type='text' name='location' placeholder='WGB G.01' />";

        } else {
            result += "Location <input type='text' name='location' value='" + meeting.getLocation() + "' />";
        }

        result += "Recurring<select name='recurring'>\n"
                + "  <option value='none'>None</option>\n"
                + "  <option value='weekly'>Weekly</option>\n"
                + "  <option value='fortnight'>Fortnightly</option>\n"
                + "  <option value='monthly'>Monthly</option>\n"
                + "</select>";

        if (meeting.correctDescription == false) {
            result += "<label>Title:<input type='text' name='description' placeholder='Insert meeting description here...' /></label>";

        } else {
            result += "<label>Title:<input type='text' name='description' placeholder='" + meeting.getDescription() + "' /></label>";
        }

        result += "<input type='submit' name='submit' />";
        result += "</form>";

        String[] list = meeting.getErrors();
        //a enhanced for loop to loop through everything in list
        for (String y : list) {
            //print out erros
            result += "<p>" + y + "</p>";
        }
        //returns html form
        return result;
    }

     /**
     * HTML form for creating a tutorial 
     * <p>
     * Form for the creation of tutorial, and to set and gain time and date information 
     * for use on the calender, also sets recuring events.
     * @return html form
     */
    public String generateTutorialGUI() {

        result += "<form name='forms' id='forms' action='tutorial.jsp' method='POST'>";
        result += "Group to set tutorial with<input type='text' name='recipient' placeholder='Enter a users ID' />";
        result += "Start Date <input type='text' class='datepicker' name='startDate' placeholder='2015/02/08' />";
        result += "Location <input type='text' name='location' placeholder='WGB G.01' />";
        result += "Recurring <select name='recurring'>";
        result += "  <option value='none'>None</option>";
        result += "  <option value='daily'>Daily</option>";
        result += "  <option value='weekly'>Weekly</option>";
        result += "  <option value='fortnight'>Fortnightly</option>";
        result += "  <option value='monthly'>Monthly</option>";
        result += "</select>";
        result += "<label>Title:<input type='text' name='description' placeholder='Insert meeting description here...' /></label>";
        result += "<li role='presentation' class='divider'></li>";
        result += "<input type='submit' name='submit' />";
        result += "</ul>";
        result += "</form>";
        //returns html form
        return result;
    }
    
    /**
     * A form to check and valadate personal events
     * <p>
     * To test if form input is valid, if not return the form with the correct input entered held in the form.
     * 
     * @param meeting
     * @return 
     */
    public String generatePersonalGUI(MeetingChecker meeting) {
        result = "<form id='forms' id='forms' name='forms' action='personal.jsp' method='POST'>";
        result += "<h1>Arrange A Personal Event</h1><hr>";
        if (meeting.correctDescription == false) {
            result += "Personal Event: <input type='text' name='description' placeholder='Doctor Appointment' />";
        } else {
            result += "Personal Event: <input type='text' name='description' value='" + meeting.getDescription() + "' />";
        }
        if (meeting.correctStartDate == false) {
            result += "Start date<input type='text' class='datepicker'  name='startDate' placeholder='2014/02/09' />";
        } else {
            result += "Start date<input type='text' class='datepicker' name='startDate' value='" + meeting.getStartDate() + "' />";
        }

        if (meeting.correctStartTime == false) {
            result += "Start Time <input type='text' class='timepicker' name='time' placeholder='12:00:00' />";
        } else {
            result += "Start Time <input type='text' class='timepicker' name='time' value='" + meeting.getTime() + "' />";
        }
        if (meeting.correctLocation == false) {
            result += "Location <input type='text' name='location' placeholder='WGB G.01' />";

        } else {
            result += "Location <input type='text' name='location' value='" + meeting.getLocation() + "' />";
        }

        result += "Recurring <select name='recurring'>";
        result += "  <option value='none'>None</option>";
        result += "  <option value='daily'>Daily</option>";
        result += "  <option value='weekly'>Weekly</option>";
        result += "  <option value='fortnight'>Fortnightly</option>";
        result += "  <option value='monthly'>Monthly</option>";
        result += "</select>";

        result += "<input type='submit' name='submit' />";

        String[] list = meeting.getErrors();
        //a enhanced for loop to loop through everything in list
        for (String y : list) {
            //print out erros
            result += "<p>" + y + "</p>";
        }
        //returns html form
        return result;
    }
    
    /**
     * Form to create personal events
     * 
     * @return html form 
     */
    public String generatePersonalGUI() {
        result = "<form name='forms' id='forms' action='personal.jsp' method='POST'>";        
        result += "<div class='form-group'>";
        result += " <label for='description'>Personal Event</label>";
        result += " <input id='description' type='text' name='description' placeholder='Doctor Appointment' />";
        result += "</div>";        
        result += "<div class='form-group'>";
        result += " <label for='startDate'>Date</label>";
        result += " <input id='startDate' type='text' class='datepicker' name='startDate' placeholder='2015/02/08' />";
        result += "</div>";        
        result += "<div class='form-group'>";
        result += " <label for='time'>Time</label>";
        result += " <input id='time' type='text' name='time' placeholder='12:00:00' />";
        result += "</div>";        
        result += "<div class='form-group'>";
        result += " <label for='location'>Location</label>";
        result += " <input id='location' type='text' name='location' placeholder='WGB G.01' />";
        result += "</div>";        
        result += "Recurring <select name='recurring'>";
        result += "  <option value='none'>None</option>";
        result += "  <option value='daily'>Daily</option>";
        result += "  <option value='weekly'>Weekly</option>";
        result += "  <option value='fortnight'>Fortnightly</option>";
        result += "  <option value='monthly'>Monthly</option>";
        result += "</select>";
        result += "<input type='submit' class='btn btn-success' name='submit' />";
        result += "</form>";

        //returns html form
        return result;
    }

}

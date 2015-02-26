package UI;

import control.CheckerClass;
import control.MeetingChecker;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class used to output html and check input of form
 *
 * @author Michael Mullarkey(112457292) & Chris Anderson(111378696)
 */
public class GUI {

    //A string varaible which ouputs html
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
        result = "<form name='form' action='registration.jsp' method='POST'>";

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
            result += "<label>Last Name*<input type='text' name='LastName' id='LastNamre' placeholder='Doe' /></label>";
            //if not empty and true
        } else {
            result += "<label>Last Name*<input type='text' name='LastName' id='LastNamre' value='" + checker.getLastName() + "' /></label>";
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
//        //If the problem description is empty or the description is incorrect format
//        if (checker.getProblem().equals("") || checker.correctProblem == false) {
//            result += "Stream: <input type='text' name='problem' placeholder='Web sys lll' />";
//            //if not empty and true
//        } else {
//            result += "Stream: <input type='text' name='problem' value='" + checker.getProblem() + "' />";
//        }

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

    public String generateRegGUI() {

        result = "<form name='form' action='registration.jsp' method='POST'>"; // start of form 
        result += "<input type='hidden' name='date' value='1993/12/15' />"; // hidden date field
        result += "<label>First Name*<input type='text' name='firstName' id='firstname' placeholder='John' /></label>"; // first name 
        result += "<label>Last Name*<input type='text' name='LastName' id='secondname' placeholder='Doe' /></label>";   //last name 
        result += "<label>Student Number* <input type='text' name='ID' id='studentNumber' placeholder='123456789'/></label>";   // Student Number 
        result += "<label>Email Address<input type='text' name='email' id='email' placeholder='1234@gmail.com' /></label>"; // email address
        result += "<label>Password<input type='password' name='password1' id='password1' /></label>"; // password 
        result += "<label>Re-Enter Password<input type='password' name='password2' id='password2' /></label>"; // reenter password

        result += "<div class='action_btns'>";
        result += "<a href='#registerdetail' data-toggle='modal' data-dismiss='modal'><button type='button' class='btn btn-success'>Continue</button></a>";
        result += "</div>";

        result += "<div class='action_btns'>";
        result += "<a href='#login' data-toggle='modal' data-dismiss='modal'>Already have an account?</a>";
        result += "</div>";
        result += "<input type='submit' name='submit' />";
        result += "</form>";
        //returns html form
        return result;
    }

    /**
     * A method for setting up html form
     *
     * @return html form
     */
    public String generateLogGUI() {

        result = "<form id='log' name='form' action='login.jsp' method='POST'>";
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

//                StringBuilder contentBuilder = new StringBuilder();
//                try {
//                    BufferedReader in = new BufferedReader(new FileReader("loginform.txt"));
//                    String str;
//                    while ((str = in.readLine()) != null) {
//                        contentBuilder.append(str);
//                    }
//                    in.close();
//                } catch (IOException e) {
//                }               
//                String result = contentBuilder.toString();
        //returns html form
        return result;
    }

    public String generateMeetingGUI(MeetingChecker meeting) {

        result = "<form name='form' action='meeting.jsp' method='POST'>";

        if (meeting.correctRecipient == false) {
            result += "ID of person you wish to meet <input type='text' name='recipient' placeholder='Enter a users ID' />";
        } else {
            result += "Person you wish to meet <input type='text' name='recipient' value='" + meeting.getRecipient() + "' />";
        }
        if (meeting.correctStartDate == false) {
            result += "Start date<input type='text' name='startDate' placeholder='2014/02/09' />";
        } else {
            result += "Start date<input type='text' name='startDate' value='" + meeting.getStartDate() + "' />";
        }

        if (meeting.correctEndDate == false) {
            result += "End date<input type='text' name='endDate' placeholder='2014/02/09' />";
        } else {
            result += "End date<input type='text' name='endDate' value='" + meeting.getEndDate() + "' />";
        }

        if (meeting.correctStartTime == false) {
            result += "Start Time <input type='text' name='startTime' placeholder='12:00:00' />";
        } else {
            result += "Start Time<input type='text' name='startTime' value='" + meeting.getStartTime() + "' />";
        }

        if (meeting.correctEndTime == false) {
            result += "End Time <input type='text' name='endTime' placeholder='17:00:00' />";

        } else {
            result += "End Time <input type='text' name='endTime' value='" + meeting.getEndTime() + "' />";
        }

        if (meeting.correctLocation == false) {
            result += "Location <input type='text' name='location' placeholder='WGB G.01' />";

        } else {
            result += "Location <input type='text' name='location' value='" + meeting.getLocation() + "' />";
        }

        result += "Recurring<select name=\"recurring\">\n"
                + "  <option value=\"none\">None</option>\n"
                + "  <option value=\"weekly\">Weekly</option>\n"
                + "  <option value=\"fortnight\">Fortnightly</option>\n"
                + "  <option value=\"monthly\">Monthly</option>\n"
                + "</select>";

        if (meeting.correctDescription == false) {
            result += "Description: <textarea name='description' cols='-40' rows='20' placeholder='Insert meeting description here' ></textarea>";

        } else {
            result += "Description: <textarea name='description' cols='-40' rows='20' >" + meeting.getDescription() + "</textarea>";
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

    public String generateMeetingGUI() {

        result = "<form name='form' action='meeting.jsp' method='POST'>";

        result += "ID of person you wish to meet with <input type='text' name='recipient' placeholder='Enter a users ID' />";
        result += "Start Date <input type='text' name='startDate' placeholder='2015/02/08' />";
        result += "End Date <input type='text' name='endDate' placeholder='2015/03/08' />";
        result += "Start Time <input type='text' name='startTime' placeholder='12:00:00' />";
        result += "End Time <input type='text' name='endTime' placeholder='17:00:00' />";
        result += "Location <input type='text' name='location' placeholder='WGB G.01' />";
        result += "Recurring <select name='recurring'>";
        result += "  <option value='none'>None</option>";
        result += "  <option value='weekly'>Weekly</option>";
        result += "  <option value='fortnight'>Fortnightly</option>";
        result += "  <option value='monthly'>Monthly</option>";
        result += "</select>";
        result += "Description: <textarea name='description' cols='-40' rows='20' placeholder='Insert meeting description here' ></textarea>";
        result += "<input type='submit' name='submit' />";

        //returns html form
        return result;
    }
}

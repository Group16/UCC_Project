package UI;
import control.CheckerClass;
import control.MeetingChecker;
/**
 * A class used to output html and check input of form
 * @author  Michael Mullarkey(112457292) & Chris Anderson(111378696)
 */
public class GUI {
    //A string varaible which ouputs html
    private String result;
    
    
    /**
     * A constructor which sets variable to stop null pointers
     */
    public GUI( ){
        result = "";
    }
    /**
     * A method for outputting html and checking form input
     * @param checker takes in checker from index.jsp
     * @return returns html form
     */
    public String generateRegGUI( CheckerClass checker  ){
        result = "<form name='form' action='registration.jsp' method='POST'>";
        //If the date is empty or the date is incorrect format
        if(checker.getDate( ).equals("") || checker.correctDate== false){
            result += "<input type='hidden' name='date' value='1993/12/15' />";
        //if not empty and true
        }else{
            result += "<input type='hidden' name='date' value='"+checker.getDate()+"' />";
        }
        //If the student ID is empty or the ID is incorrect format
        if(checker.getID().equals("") || checker.correctID== false){
            result += "Student ID:<input type='text' name='ID' placeholder='112457292' />";
         //if not empty and true
        }else{
            result += "Student ID:<input type='text' name='ID'value='"+checker.getID()+"' />";
        }
        //If the email is empty or the email is incorrect format
        if(checker.getEmail( ).equals("")||checker.correctEmail== false){
             result += "Email:<input type='email' name='email' placeholder='1234@gmail.com' />";
         //if not empty and true
        }else{
             result += "Email:<input type='email' name='email' value='"+checker.getEmail()+"' />";
        }
        //If the problem description is empty or the description is incorrect format
        if(checker.getProblem( ).equals("")||checker.correctProblem== false){
            result += "Stream: <input type='text' name='problem' placeholder='Web sys lll' />";
         //if not empty and true
        }else{
            result += "Stream: <input type='text' name='problem' value='"+checker.getProblem()+"' />";
        }
        
        if(checker.getFirstName( ).equals("")||checker.correctFirstName== false){
            result += "First name: <input type='text' name='firstName' placeholder='John' />";
         //if not empty and true
        }else{
            result += "First name: <input type='text' name='firstName' value='"+checker.getFirstName()+"' />";
        }
        
        if(checker.getLastName( ).equals("")||checker.correctLastName== false){
            result += "Last name: <input type='text' name='lastName' placeholder='Smith' />";
         //if not empty and true
        }else{
            result += "Last name: <input type='text' name='lastName' value='"+checker.getLastName()+"' />";
        }
        
        if(checker.getPassword1( ).equals("")||checker.correctPassword1== false){
           result += "Password: <input type='password' name='password1' />";
         //if not empty and true
        }else{
            result += "Password: <input type='password' name='password1' value='"+checker.getPassword1()+"' />";
        }
        
        if(checker.getPassword2( ).equals("")||checker.correctPassword2== false){
           result += "Re-eneter Password: <input type='password' name='password2'  />";
         //if not empty and true
        }else{
            result += "Re-enter Password: <input type='password' name='password2' value='"+checker.getPassword2()+"' />";
        }

        result += "<input type='submit' name='submit' />";


        result += "</form>";
        //Sets the errors array to new array
        String[] list = checker.getErrors();
        //a enhanced for loop to loop through everything in list
        for(String y:list){
            //print out erros
            result +="<p>"+y +"</p>";

        }
        //reurns html form
        return result;
    }
    
     public String generateRegGUI(){
       
        result = "<form name='form' action='registration.jsp' method='POST'>";

        result += "<input type='hidden' name='date' value='1993/12/15' />";
        result += "Student ID:<input type='text' name='ID' placeholder='112457292' />";
        result += "Email:<input type='text' name='email' placeholder='1234@gmail.com' />";
        result += "Stream: <input type='text' name='problem' placeholder='Web sys lll' />";
        result += "First name: <input type='text' name='firstName' placeholder='John' />";
        result += "Last name: <input type='text' name='lastName' placeholder='Smith' />";
        result += "Password: <input type='password' name='password1'  />";
        result += "Re-enter Password: <input type='password' name='password2'  />";
        result += "<input type='submit' name='submit' />";

        result += "</form>";
            //returns html form
        return result;
    }
    /**
     * A method for setting up html form
     * @return html form 
     */
    public String generateLogGUI(){
       
        result = "<form name='form' action='login.jsp' method='POST'>";

        result += "Student:<input type='text' name='studentID' placeholder='123456789' />";
        result += "Password:<input type='password' name='pword' placeholder='' />";
        result += "<input type='submit' name='submit' />";

        result += "</form>";

        result += "</form>";
            //returns html form
        return result;
    }
    
    public String generateMeetingGUI(MeetingChecker meeting){
        
        result = "<form name='form' action='meeting.jsp' method='POST'>";

        
        if(meeting.correctRecipient== false){
            result += "Person you wish to meet <input type='text' name='recipient' placeholder='Enter a user' />";
        }
        else
        {
            result += "Person you wish to meet <input type='text' name='recipient' value='"+meeting.getRecipient()+"' />";
        }
        if(meeting.correctStartDate== false){
            result += "Start date<input type='text' name='startDate' placeholder='2014/02/09' />";
        }
        else
        {
            result += "Start date<input type='text' name='startDate' value='"+meeting.getStartDate()+"' />";
        }
        
        if(meeting.correctEndDate== false){
            result += "End date<input type='text' name='endDate' placeholder='2014/02/09' />";
        }
        else
        {
            result += "End date<input type='text' name='endDate' value='"+meeting.getEndDate()+"' />";
        }
        
        if(meeting.correctStartTime== false){
            result += "Start Time <input type='text' name='startTime' placeholder='12:00:00' />";
        }
        else
        {
            result += "Start Time<input type='text' name='startTime' value='"+meeting.getStartTime()+"' />";
        }
        
        if(meeting.correctEndTime== false){
            result += "End Time <input type='text' name='endTime' placeholder='17:00:00' />";
        
        }
        else
        {
            result += "End Time <input type='text' name='endTime' value='"+meeting.getEndTime()+"' />";
        }
        
        if(meeting.correctLocation== false){
            result += "Location <input type='text' name='location' placeholder='WGB G.01' />";
        
        }
        else
        {
            result += "Location <input type='text' name='location' value='"+meeting.getLocation()+"' />";
        }
        
        result += "Recurring<select name=\"recurring\">\n" +
                                "  <option value=\"none\">None</option>\n" +
                                "  <option value=\"weekly\">Weekly</option>\n" +
                                "  <option value=\"fortnight\">Fortnightly</option>\n" +
                                "  <option value=\"monthly\">Monthly</option>\n" +
                                "</select>";
        
        if(meeting.correctDescription== false){
            result += "Description: <textarea name='description' cols='-40' rows='20' placeholder='Insert meeting description here' ></textarea>";
        
        }
        else
        {
            result += "Description: <textarea name='description' cols='-40' rows='20' >"+meeting.getDescription()+"</textarea>";
        }
        
        result += "<input type='submit' name='submit' />";

        result += "</form>";
        
        String[] list = meeting.getErrors();
        //a enhanced for loop to loop through everything in list
        for(String y:list){
            //print out erros
            result +="<p>"+y +"</p>";

        }
            //returns html form
        return result;
    }
    
    public String generateMeetingGUI(){
        
        result = "<form name='form' action='meeting.jsp' method='POST'>";
        
        result += "Person you wish to meet <input type='text' name='recipient' placeholder='Enter a user' />";
        result += "Start Date <input type='text' name='startDate' placeholder='2015/02/08' />";
        result += "End Date <input type='text' name='endDate' placeholder='2015/03/08' />";
        result += "Start Time <input type='text' name='startTime' placeholder='12:00:00' />";
        result += "End Time <input type='text' name='endTime' placeholder='17:00:00' />";
        result += "Location <input type='text' name='location' placeholder='WGB G.01' />";
        result += "Recurring <select name=\"recurring\">\n" +
                                "  <option value=\"none\">None</option>\n" +
                                "  <option value=\"weekly\">Weekly</option>\n" +
                                "  <option value=\"fortnight\">Fortnightly</option>\n" +
                                "  <option value=\"monthly\">Monthly</option>\n" +
                                "</select>";
        
        result += "Description: <textarea name='description' cols='-40' rows='20' placeholder='Insert meeting description here' ></textarea>";
        result += "<input type='submit' name='submit' />";

        
            //returns html form
        return result;
    }
}


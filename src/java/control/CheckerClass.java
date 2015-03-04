/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */  
package control;
import database.DbClass;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 * A class used as a bean, it also sets up the database and validates input
 * @author  Michael Mullarkey(112457292)
 */
public class CheckerClass {
    //Refrences class
    DbClass database;
    
    MeetingChecker meeting = new MeetingChecker();
    //A variable for holding date
    private String date;
    //A string variable for holding the student ID
    private String ID;
    //A int variable for parsing the String student ID
    private int intID;
    //A string variable for holding the email
    private String email;
    //A string variable for holding the problem description
    private String problem;
    
    private String firstName;
    
    private String lastName;
    
    private String password1;
    
    private String password2;
    
    //A string array for holding errors
    private String[] array;
    
    private String generatedPassword;
    
    //A boolean to check if the date is in correct format
    public boolean correctDate;
    //A boolean to check if the ID is in correct format
    public boolean correctID;
    //A boolean to check if the email is in correct format
    public boolean correctEmail;
    ////A boolean to check if the problem description is in correct format
    public boolean correctProblem;
    
    public boolean correctFirstName;
    
    public boolean correctLastName;
    
    public boolean correctPassword1;
    
    public boolean correctPassword2;
    
  
    /**
     * A constructor which sets up the database, initialize variables so not to get null pointers
     */
    public CheckerClass(){
        database = new DbClass();
        database.setup();
        this.date = "";
        this.ID ="";
        this.intID = 0;
        this.email = "";
        this.problem = "";
        this.firstName="";
        this.lastName="";
        this.password1 = "";
        this.password2="";
        this.generatedPassword="";
        array = new String[8];
    } 
    /**
     * A method which sets the date
     * @param enteredDate the date set by the user
     */
    public void setDate( String enteredDate){
        this.date = enteredDate;
    }
    /**
     * A method for returning the date
     * @return returns the date 
     */
    public String getDate( ){
        return this.date;
    }
    /**
     * A method for setting the ID
     * @param enteredID the ID set by user 
     */
    public void setID( String enteredID ){
        this.ID = enteredID;
    }
    /**
     * A method for getting the ID
     * @return the ID
     */
    public String getID( ){
        return this.ID;
    }
    /**
     * A method for setting the email
     * @param enteredEmail the email set by the user
     */
    public void setEmail( final String enteredEmail ){
        this.email = enteredEmail;
    }
    /**
     * A method which returns the email
     * @return the email 
     */
    public String getEmail( ){
        return this.email;
    }
    /**
     * A method which sets the problem
     * @param enterdProblem the problem entered by the user
     */
    public void setProblem( String enterdProblem ){
        this.problem = enterdProblem;
    }
    /**
     * A method which gets the problem description
     * @return returns the problem 
     */
    public String getProblem(){
        return this.problem;
    }
    
    public void setFirstName( String enterdFirstName ){
        this.firstName = enterdFirstName;
    }
    /**
     * A method which gets the problem description
     * @return returns the problem 
     */
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setLastName( String enterdLastName ){
        this.lastName = enterdLastName;
    }
    /**
     * A method which gets the problem description
     * @return returns the problem 
     */
    public String getLastName(){
        return this.lastName;
    }
    
    public void setPassword1( String enterdPassword1 ){
        this.password1 = enterdPassword1;
    }
    /**
     * A method which gets the problem description
     * @return returns the problem 
     */
    public String getPassword1(){
        return this.password1;
    }
    
    public void setPassword2( String enterdPassword2 ){
        this.password2 = enterdPassword2;
    }
    /**
     * A method which gets the problem description
     * @return returns the problem 
     */
    public String getPassword2(){
        return this.password2;
    }
    /**
     * A method which resets all the fields in the form
     */
    public void emptyFields( ){
        date = "";
        email = "";
        ID = "";
        problem = "";
        firstName="";
        lastName="";
        password1="";
        password2="";
        array = new String[8];
    }
    public String get_SHA_256_SecurePassword(String password)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return this.generatedPassword;
    }
    
     
     private static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
    /**
     * A method which insert the data from the form into the database
     */
    public void insertQuery( ){
     
        database.Insert( "INSERT INTO people( p_id, p_type, firstname, surname, password, email, dateToday )" +
                         "VALUES( '" + this.ID + "', '" + "student" + "', '" + this.firstName + "', '" + this.lastName + "', '" +
                                            this.generatedPassword + "', '" + this.email + "',  '" + meeting.dateSent() + "');");
    }
    /**
     * A method which formats input and checks validation of input
     * @return returns the boolean to say whether the form is valid or not
     */
    public boolean validate( ){
        //Boolean value which is returned
        boolean valid = true;
       
        database.checkQuery("select * FROM people where p_id='" + ID + "'");
                
        if(database.queryCorrect==true) 
        { 
             array[7]="*User ID already in use";
             valid=false;
             correctID = false;
        } 
        else 
        {
             correctID = true;
             array[7]="*User ID already in use";
        }
        
        //If the id field is empty
        if(ID == null){
            //set the validation to false
            valid = false;
         //if not null
        }else{
           try{
               //parse the ID to integer
               intID = Integer.parseInt(ID);
               //insert empty string to the array
               array[0] =  "";
               //set the correctID to true
               correctID = true;
            //catch if error occurs
           } catch(Exception E){
               //Add error ouput to array
               array[0] = "*Please only enter integers in Student ID.";
               //set validaiton to true
               valid = false;
               //Set validation to false
               correctID = false;
           }
        }
        //If the email field is empty
        if( getEmail().equals("") ){
            //Add error ouput to array
            array[1] = "*Email required";
            //Set validation to false
            valid = false;
            //set correctEmail to false
            correctEmail = false;
        }
        //If not empty
        else{       
             //Set correctEmail to true 
             correctEmail = true;
             //Add empty string to array
             array[1] =  "";
        }
        
        if( getFirstName().equals( "" ) ){
            ///Add error ouput to the array
            array[2] = "*First name required";
            //validation set to false
            valid = false;
            //correctProblem set to false
            correctFirstName = false;
         //if not empty
        }else{   
            //Set correctProblem to true
             correctFirstName = true;
             //Add empty string to array
             array[2] =  "";
        }
        
        if( getLastName().equals( "" ) ){
            ///Add error ouput to the array
            array[3] = "*Last name required";
            //validation set to false
            valid = false;
            //correctProblem set to false
            correctLastName = false;
         //if not empty
        }else{   
            //Set correctProblem to true
             correctLastName = true;
             //Add empty string to array
             array[3] =  "";
        }
        
        if( getPassword1().equals( "" ) ){
            ///Add error ouput to the array
            array[4] = "*Password required";
            //validation set to false
            valid = false;
            //correctProblem set to false
            correctPassword1 = false;
         //if not empty
        }else{   
            //Set correctProblem to true
             correctPassword1 = true;
             //Add empty string to array
             array[4] =  "";
        }
        
        if( getPassword2().equals( "" ) ){
            ///Add error ouput to the array
            array[5] = "*You must re-eneter password";
            //validation set to false
            valid = false;
            //correctProblem set to false
            correctPassword2 = false;
         //if not empty
        }else{   
            //Set correctProblem to true
             correctPassword2 = true;
             //Add empty string to array
             array[5] =  "";
        }
        
        if( !getPassword1().equals( getPassword2() ) ){
            ///Add error ouput to the array
            array[6] = "*Passwords do not match";
            //validation set to false
            valid = false;
            
         //if not empty
        }else{ 
             //Add empty string to array
             array[6] =  "";
        }
        //returns whether validation is true or false
        return valid;
        
    }
    /**
     * A method for getting the array of errors
     * @return returns array of errors
     */
     public String[] getErrors(){
        return array;
    }  
}


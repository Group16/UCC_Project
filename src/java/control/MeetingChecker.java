/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.DbClass;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;

/**
 *
 * @author mm37
 */
public class MeetingChecker {
    //Refrences class
    DbClass database;
    
    private int meetingID;
    
    private String sender;
    
    private String recipient;
    
    private String dateSent;
    
    private String[] array;
    
    private String startDate;
    
    private String endDate;
    
    private String recurring;
    
    private String startTime;
    
    private String endTime;
    
    private String location;
    
    private String description;
    
    public boolean correctRecipient;
    
    public boolean correctStartDate;
    
    public boolean correctEndDate;
    
    public boolean correctStartTime;
    
    public boolean correctEndTime;
    
    public boolean correctLocation;
    
    public boolean correctDescription;
    
    
    
    public MeetingChecker(){
        database = new DbClass();
        database.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
        this.meetingID=0;
        this.sender ="";
        this.recipient="";
        this.dateSent="";
        this.startDate="";
        this.endDate="";
        this.recurring="";
        this.startTime="";
        this.endTime="";
        this.location="";
        this.description="";
        array = new String[7];
    }
    
    public void setSender( String enteredSender){
        this.sender = enteredSender;
    }
    
    public String getSender( ){
        return this.sender;
    }
    
    public void setRecipient( String enteredRecipient){
        this.recipient = enteredRecipient;
    }
    
    public String getRecipient( ){
        return this.recipient;
    }
    
    
    public void setStartDate( String enteredStartDate){
        this.startDate = enteredStartDate;
    }
    
    public String getStartDate( ){
        return this.startDate;
    }
    
    public void setEndDate( String enteredEndDate){
        this.endDate = enteredEndDate;
    }
    
    public String getEndDate( ){
        return this.endDate;
    }
    
    public void setStartTime( String enteredStartTime){
        this.startTime = enteredStartTime;
    }
    
    public String getStartTime(){
        return this.startTime;
    }
    
    public void setEndTime( String enteredEndTime){
        this.endTime = enteredEndTime;
    }
    
    public String getEndTime( ){
        return this.endTime;
    }
    
    public void setLocation( String enteredLocation){
        this.location = enteredLocation;
    }
    
    public String getLocation( ){
        return this.location;
    }
    
    public void setDescription( String enteredDescription){
        this.description = enteredDescription;
    }
    
    public String getDescription( ){
        return this.description;
    }
    
    
    public void setRecurring( String enteredRecurring){
        this.recurring = enteredRecurring;
    }
    
    public String getRecurring( ){
        return this.recurring;
    }
    
    public void insertQuery( ){
        database.Insert( "INSERT INTO meetings( time, date, location, recur, recur_end, type, description, dateToday )" +
                         "VALUES( '" + this.startTime + "', '" + this.startDate + "', '" + this.location + "',  '" + this.recurring + "', '" + this.endDate + "',  '" +
                                                    "TYPE?"+ "', '" + this.description + "', '" + dateSent() + "');");
    }
    
    
    public String dateSent(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        this.dateSent = dateFormat.format(date);
        return this.dateSent;
    }
    
    public boolean validate(){
        //Boolean value which is returned
        boolean valid = true;
        
        //If the email field is empty
        if( getRecipient().equals("") ){
            
            //Set validation to false
            valid = false;
            correctRecipient = false;
            }   
            database.checkQuery("select * from users where firstName='" + this.recipient + "'");
            if(database.queryCorrect==true) 
            { 
                 array[0]="";
                 correctRecipient = true;
            } 
            else 
            {
                 valid=false;
                 array[0]="*User does not exist";
            }
            
        //If the date is null
        if(getStartDate().equals("")){
           valid = false;
           correctStartDate = false;
           //place empty string in the array
           array[1] ="*Start Date Required.";
        //If the date is not null
        }else{
           //set the correctDate to true
           correctStartDate = true;
           
           //Add error to the array
           array[1] ="";
            
         }
        
        if(getEndDate().equals("")){
           valid = false;
           correctEndDate = false;
           //place empty string in the array
           array[2] ="*End Date Required.";
        //If the date is not null
        }else{
           //set the correctDate to true
           correctEndDate = true;
           
           //Add error to the array
            array[2] ="";
            
         }
        
        //If the date is null
        if(getStartTime().equals("")){
           valid = false;
           correctStartTime = false;
           //place empty string in the array
           array[3] ="*Start Time Required. ";
        //If the date is not null
        }else{
           //set the correctDate to true
           correctStartTime = true;
           
           //Add error to the array
           array[3] ="";
            
         }
        
        if(getEndTime().equals("")){
           valid = false;
           correctEndTime = false;
           //place empty string in the array
           array[4] ="*End Time Required.";
        //If the date is not null
        }else{
           //set the correctDate to true
           correctEndTime = true;
           
           //Add error to the array
            array[4] ="";
            
         }
        
        if(getLocation().equals("")){
           valid = false;
           correctLocation = false;
           //place empty string in the array
           array[5] ="*Location Required.";
        //If the date is not null
        }else{
           //set the correctDate to true
           correctLocation = true;
           
           //Add error to the array
            array[5] ="";
        }
        
        if(getDescription().equals("")){
           valid = false;
           correctDescription = false;
           //place empty string in the array
           array[6] ="*Description Required.";
        //If the date is not null
        }else{
           //set the correctDate to true
           correctDescription = true;
           
           //Add error to the array
            array[6] ="";
            
         }
        
         return valid;
    }
    
    public String[] getErrors(){
        return array;
    }
}

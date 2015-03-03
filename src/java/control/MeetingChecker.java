/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.DbClass;
import control.FindMeetings;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

/**
 *
 * @author mm37
 */
public class MeetingChecker {
    //Refrences class
    DbClass database;
    
    DbClass database2;
    
    FindMeetings findmeetings = new FindMeetings();
    
    private int meetingID;
   
    ArrayList<String> list;
    
    private String sender;
    
    private String recipient;
    
    private String dateSent;
    
    private String[] array;
    
    private String startDate;
    
    private String endDate;
    
    private String recurring;
    
    private String time;
    
    private String location;
    
    private String description;
    
    private String group;
    
    public boolean correctRecipient;
    
    public boolean correctStartDate;
    
    public boolean correctEndDate;
    
    public boolean correctStartTime;
    
    public boolean correctEndTime;
    
    public boolean correctLocation;
    
    public boolean correctDescription;
    
    public boolean correctGroup;
    
    
    public MeetingChecker(String meetingID, String time, String startDate, String location, String recurring, String endDate, String type, String description){
        database = new DbClass();
        database2 = new DbClass();
        database.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
        database2.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
        
        try{
            this.meetingID = Integer.parseInt(meetingID);
        }catch(Exception e){
            
        }
        
        this.sender ="";
        this.recipient="";
        this.dateSent="";
        this.startDate=startDate;
        this.endDate=endDate;
        this.recurring=recurring;
        this.time=time;
        this.location=location;
        this.description=description;
        this.group ="";
        array = new String[6];
    }
    
    public MeetingChecker(){
        database = new DbClass();
        database2 = new DbClass();
        database.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
        database2.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
        this.meetingID=0;
        this.sender ="";
        this.recipient="";
        this.dateSent="";
        this.startDate="";
        this.endDate="";
        this.recurring="";
        this.time="";
        this.location="";
        this.description="";
        this.group ="";
        array = new String[6];
        list = new ArrayList<>();
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
    
    public void setGroup( String enteredGroup){
        this.group = enteredGroup;
    }
    
    public String getGroup( ){
        return this.group;
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
    
    public void setTime( String enterdtime ){
        this.time = enterdtime;
    }
    
    public String getTime(){
        return this.time;
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
    
    public int getMeetingID(){
        
        String num[] = database.SelectRow("SELECT * FROM meetings ORDER BY m_id DESC LIMIT 1;");
        String count = num[0];
        
        int realNum = Integer.parseInt(count);
        realNum++;
        
        this.meetingID = realNum;
        
        return this.meetingID;
    }
    
    
    public void insertMeetQuery( String type, String confirmed, ArrayList<String> list){
        
        System.out.println(list);
        
        TreeMap<String,String> freeTime = findmeetings.getFreeTime(list, startDate);
        
        database.Insert("INSERT INTO meetings( m_id, confirmed, time, date, location, recur, recur_end, type, description, dateToday )" +
                         "VALUES( '" + getMeetingID() + "', '" + confirmed + "', '" + freeTime.firstEntry().getKey() + "', '" + freeTime.get(freeTime.firstKey()) + "', '" + this.location + "',  '" + this.recurring + "', '" + this.endDate + "',  '" +
                                                    type + "', '" + this.description + "', '" + dateSent() + "');");
    }
    
    public void insertNotQuery( String typeOfNotification){
        database.Insert( "INSERT INTO notifications( m_id, is_seen, type, content, date, p_id )" +
                         "VALUES( '" + getMeetingID() + "', '" + "0" + "', '" + typeOfNotification + "',  '" + this.description+ "', '" + this.startDate + "',  '" +
                                                    recipient + "');");
    }
    
    public void insertOtherPIMQuery(String is_manager, ArrayList<String> recipients){
        for(String recip : recipients ){
            database.Insert( "INSERT INTO people_in_meetings( p_id,  m_id, is_manager )" +
                         "VALUES( '" + recip + "', '" + getMeetingID()  + "', '" + is_manager + "' );");
        }
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
                database.checkQuery("select * from people where p_id='" + this.recipient + "'");
                database2.checkQuery("select * FROM people where p_id='" + this.recipient+ "'");
                
                if(database.queryCorrect==true) 
                { 
                     array[0]="";
                     correctRecipient = true;
                } 
                else 
                {
                     valid=false;
                     array[0]="*Recipient does not exist.";
                     
                     if(database2.queryCorrect==true) 
                     { 
                        array[0]="";
                        correctGroup = true;
                     } 
                     else 
                     {
                         valid=false;
                         array[0]="*Recipient(s) does not exist.";
                     }
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
        if(getTime().equals("")){
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
        
        if(getLocation().equals("")){
           valid = false;
           correctLocation = false;
           //place empty string in the array
           array[4] ="*Location Required.";
        //If the date is not null
        }else{
           //set the correctDate to true
           correctLocation = true;
           
           //Add error to the array
            array[4] ="";
        }
        
        if(getDescription().equals("")){
           valid = false;
           correctDescription = false;
           //place empty string in the array
           array[5] ="*Description Required.";
        //If the date is not null
        }else{
           //set the correctDate to true
           correctDescription = true;
           
           //Add error to the array
            array[5] ="";
            
         }
        
         return valid;
    }
    
    public String[] getErrors(){
        return array;
    }
}

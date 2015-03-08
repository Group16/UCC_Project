/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import database.DbClass;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

/**
 * A class used as a bean as input for meeting forms, it also sets up the database and validates input
 * @author  Michael Mullarkey(112457292)
 */
public class MeetingChecker {
    
    private DbClass database;
 
    private DbClass database2;
  
    private final FindMeetings findmeetings;
    
    private int meetingID;
   
    private ArrayList<String> list;
    
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
    
    /**
     * A constructor for creating a meeting object
     * @param meetingID the meeting ID
     * @param time the start time of the meeting
     * @param startDate the start date of the meeting 
     * @param location the location of the meeting
     * @param recurring how the meeting recurs
     * @param endDate when the date ends
     * @param type the type of the meeting
     * @param description the description of the meeting
     */
    public MeetingChecker(String meetingID, String time, String startDate, String location, String recurring, String endDate, String type, String description)
    {
        this.database = new DbClass();
        this.database2 = new DbClass();
        this.database.setup();
        this.database2.setup();
        
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
        this.array = new String[4];
        this.list = new ArrayList<>();
        this.findmeetings = new FindMeetings();
    }
    
    /**
     * A constructor which initializes all variables and sets up objects 
     */
    public MeetingChecker()
    {
        this.database = new DbClass();
        this.database2 = new DbClass();
        this.database.setup();
        this.database2.setup();
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
        this.array = new String[4];
        this.list = new ArrayList<>();
        this.findmeetings = new FindMeetings();
    }
    
    /**
     * A method which sets the sender
     * @param enteredSender the sender of the meeting
     */
    public void setSender( String enteredSender){
        this.sender = enteredSender;
    }
    /**
     * A method for returning the sender
     * @return returns the sender 
     */
    public String getSender( ){
        return this.sender;
    }
    /**
     * A method which sets the recipient of the meeting
     * @param enteredRecipient the recipient of the meeting
     */
    public void setRecipient( String enteredRecipient){
        this.recipient = enteredRecipient;
    }
    /**
     * A method for returning the recipient
     * @return returns the recipient 
     */
    public String getRecipient( ){
        return this.recipient;
    }
    /**
     * A method which sets the start date of the meeting
     * @param enteredStartDate the start date of the meeting
     */
    public void setStartDate( String enteredStartDate){
        this.startDate = enteredStartDate;
    }
    /**
     * A method for returning the start date
     * @return returns the start date 
     */
    public String getStartDate( ){
        return this.startDate;
    }
    /**
     * A method which sets the start time of the meeting
     * @param enterdtime the start time of the meeting
     */
    public void setTime( String enterdtime ){
        this.time = enterdtime;
    }
    /**
     * A method for returning the start time
     * @return returns the start time 
     */
    public String getTime(){
        return this.time;
    }
    /**
     * A method which sets the location of the meeting
     * @param enteredLocation the location of the meeting
     */
    public void setLocation( String enteredLocation){
        this.location = enteredLocation;
    }
    /**
     * A method for returning the location
     * @return returns the location 
     */
    public String getLocation( ){
        return this.location;
    }
    /**
     * A method which sets the description of the meeting
     * @param enteredDescription the location of the meeting
     */
    public void setDescription( String enteredDescription){
        this.description = enteredDescription;
    }
    /**
     * A method for returning the description of the meeting
     * @return returns the description 
     */
    public String getDescription( ){
        return this.description;
    }
    /**
     * A method which sets the reccurence of the meeting
     * @param enteredRecurring the reccurence of the meeting
     */
    public void setRecurring( String enteredRecurring){
        this.recurring = enteredRecurring;
    }
    /**
     * A method for returning the reccurence of the meeting
     * @return returns the reccurence 
     */
    public String getRecurring( ){
        return this.recurring;
    }
    /**
     * A method which gets the last inserted meeting id and adds one to it
     * @return meeting id 
     */
    public int makeMeetingID(){
        
        String num[] = database.SelectRow("SELECT * FROM meetings ORDER BY m_id DESC LIMIT 1;");
        String count = num[0];
        
        int realNum = Integer.parseInt(count);
        realNum++;
        
        this.meetingID = realNum;
        
        return this.meetingID;
    }
    /**
     * A method which inserts a created meeting into the meeting table
     * @param type of meeting
     * @param confirmed if the meetings been confirmed by other recipient
     * @param list list of people in the meeting
     */
    public void insertMeetQuery( String type, String confirmed, ArrayList<String> list)
    {
        TreeMap<String,String> freeTime = findmeetings.getFreeTime(list, startDate);
        
        if( freeTime == null )
        {
            System.out.print("THE FIND MEETINGS TREE WAS NULL");
            return;
        }
        
        database.Insert("INSERT INTO meetings( m_id, confirmed, time, date, location, recur, type, description, dateToday )" +
                         "VALUES( '" + makeMeetingID() + "', '" + confirmed + "', '" + freeTime.firstEntry().getKey() + "', '" + freeTime.get(freeTime.firstKey()) + "', '" + this.location + "',  '" + this.recurring + "',  '" +
                                                    type + "', '" + this.description + "', '" + dateSent() + "');");
    }
    /**
     * A method which inserts a lecture into the meeting table
     * @param date start date of the lecture
     * @param time start time of the lecture
     */
    public void insertLectureQuery( String date, String time )
    {
            String type = "lecture";
        
            database.Insert("INSERT INTO meetings( m_id, confirmed, time, date, location, recur, type, description, dateToday )" +
                             "VALUES( '" + makeMeetingID() + "', '1', '"+time+"', '"+date+"', '" + this.location + "',  'weekly',  '" +
                                                        type + "', '" + this.description + "', '" + dateSent() + "');");
    }
    /**
     * A method which which inserts into the notification table for users who have been asked to have a meeting
     * @param typeOfNotification the type of meeting
     */
    public void insertNotQuery( String typeOfNotification ){
        database.Insert("INSERT INTO notifications( m_id, is_seen, type, content, date, p_id )" +
                         "VALUES( '" + makeMeetingID() + "', '" + "0" + "', '" + typeOfNotification + "',  '" + this.description+ "', '" + this.startDate + "',  '" +
                                                    recipient + "');");
    }
    /**
     * A method which which inserts into the notification table for a group of users who have been asked to have a meeting
     * @param typeOfNotification the type of meeting
     * @param listOfPeople group of people who have been asked to meeting
     */
    public void insertNotGroupQuery( String typeOfNotification, ArrayList<String> listOfPeople){
       
        for(String person : listOfPeople){
            database.Insert("INSERT INTO notifications( m_id, is_seen, type, content, date, p_id )" +
                         "VALUES( '" + makeMeetingID() + "', '" + "0" + "', '" + typeOfNotification + "',  '" + this.description+ "', '" + this.startDate + "',  '" +
                                                    person + "');");
        }
    }
    /**
     * A method which inserts the recipient of the meeting into the people_in_meetings table 
     * @param is_manager are they the manager of the meeting
     * @param recipients the list of recipients
     */
    public void insertOtherPIMQuery(String is_manager, ArrayList<String> recipients){
        for(String recip : recipients ){
            database.Insert("INSERT INTO people_in_meetings( p_id,  m_id, is_manager )" +
                         "VALUES( '" + recip + "', '" + makeMeetingID()  + "', '" + is_manager + "' );");
        }
    }
    /**
     * A method which inserts the sender of the meeting into the people_in_meetings table 
     * @param user the current user who sent the meeting request
     */
    public void insertPersonalPIMQuery(String user){
        database.Insert("INSERT INTO people_in_meetings( p_id,  m_id, is_manager )" +
                         "VALUES( '" + user + "', '" + makeMeetingID()  + "', '" + "1" + "' );");
    }
    /**
     * A method which inserts a personal event into the meetings table
     */
    public void insertPersonalQuery(){
        
        database.Insert("INSERT INTO meetings( m_id, confirmed, time, date, location, recur,  type, description, dateToday )" +
                         "VALUES( '" + makeMeetingID() + "','" + "1" + "', '" + this.time + "', '" + this.startDate + "', '" + this.location + "',  '" + this.recurring + "',  '" +
                                                    "personal"+ "', '" + this.description + "', '" + dateSent() + "');");
    }
    /**
     * A method which get the current date and sends it
     * @return the current date
     */
    public String dateSent(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        this.dateSent = dateFormat.format(date);
        return this.dateSent;
    }
    /**
     * A method which formats input and checks validation of input
     * @return returns the boolean to say whether the form is valid or not
     */
    public boolean validate(){
        //Boolean value which is returned
        boolean valid = true;
        
            //If the recipient field is empty
            if( getRecipient().equals("") ){

                //Set validation to false
                valid = false;
                correctRecipient = false;
                array[0] = "*User does not exist";
                }   
                database.checkQuery("select * from people where p_id='" + this.recipient + "'");
                database2.checkQuery("select * FROM modules where mod_id='" + this.recipient+ "'");
                
                if(database.queryCorrect==true) 
                { 
                     array[0]="";
                     correctRecipient = true;
                } 
                else 
                {
                     valid=false;
                }  
            
            //If the start date is empty
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
            //If the location field in empty
            if(getLocation().equals("")){
               valid = false;
               correctLocation = false;
               //place empty string in the array
               array[2] ="*Location Required.";
            //If the date is not null
            }else{
               //set the correctDate to true
               correctLocation = true;

               //Add error to the array
                array[2] ="";
            }
            //If the description field in empty
            if(getDescription().equals("")){
               valid = false;
               correctDescription = false;
               //place empty string in the array
               array[3] ="*Description Required.";
            //If the date is not null
            }else{
               //set the correctDate to true
               correctDescription = true;

               //Add error to the array
                array[3] ="";

             }

             return valid;
    }
    /**
     * A method which formats input and checks validation of input for the personal event form
     * @return returns the boolean to say whether the form is valid or not
     */
    public boolean validatePersonal(){
        //Boolean value which is returned
        boolean valid = true;
        
            //If the start date is empty
            if(getStartDate().equals("")){
               valid = false;
               correctStartDate = false;
               //place empty string in the array
               array[0] ="*Start Date Required.";
            //If the date is not null
            }else{
               //set the correctDate to true
               correctStartDate = true;

               //Add error to the array
               array[0] ="";

             }

            //If the time field is empty
            if(getTime().equals("")){
               valid = false;
               correctStartTime = false;
               //place empty string in the array
               array[1] ="*Start Time Required. ";
            //If the date is not null
            }else{
               //set the correctDate to true
               correctStartTime = true;

               //Add error to the array
               array[1] ="";

             }
            //if the location field is empty
            if(getLocation().equals("")){
               valid = false;
               correctLocation = false;
               //place empty string in the array
               array[2] ="*Location Required.";
            //If the date is not null
            }else{
               //set the correctDate to true
               correctLocation = true;

               //Add error to the array
                array[2] ="";
            }
            //if the description field is empty
            if(getDescription().equals("")){
               valid = false;
               correctDescription = false;
               //place empty string in the array
               array[3] ="*Description Required.";
            //If the date is not null
            }else{
               //set the correctDate to true
               correctDescription = true;

               //Add error to the array
                array[3] ="";

             }

             return valid;
    }
    /**
     * A method which formats input and checks validation of input for the tutorial form
     * @return returns the boolean to say whether the form is valid or not
     */
    public boolean validateTutorial(){
        //Boolean value which is returned
        boolean valid = true;
        
            //If the recipeint field is empty
            if( getRecipient().equals("") ){

                //Set validation to false
                valid = false;
                correctRecipient = false;
                array[0]="*Group does not exist";
                }   
                //Checks group exists
                database.checkQuery("select * FROM modules where mod_id='" + this.recipient+ "'");
                
                if(database.queryCorrect==true) 
                { 
                     array[0]="";
                     correctRecipient = true;
                } 
                else 
                {
                     valid=false;
                }
                
            
            //If the start date field is empty
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
            //if the location field is empty
            if(getLocation().equals("")){
               valid = false;
               correctLocation = false;
               //place empty string in the array
               array[2] ="*Location Required.";
            //If the date is not null
            }else{
               //set the correctDate to true
               correctLocation = true;

               //Add error to the array
                array[2] ="";
            }
            //if the description field is empty
            if(getDescription().equals("")){
               valid = false;
               correctDescription = false;
               //place empty string in the array
               array[3] ="*Description Required.";
            //If the date is not null
            }else{
               //set the correctDate to true
               correctDescription = true;

               //Add error to the array
                array[3] ="";

             }

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
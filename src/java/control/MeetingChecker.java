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
 *
 * @author mm37
 */
public class MeetingChecker {
    //Refrences class
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
    
    
    public void insertMeetQuery( String type, String confirmed, ArrayList<String> list)
    {
        TreeMap<String,String> freeTime = findmeetings.getFreeTime(list, startDate);
        
        if( freeTime == null )
        {
            System.out.print("THE FIND MEETINGS TREE WAS NULL");
            return;
        }
        
        database.Insert("INSERT INTO meetings( m_id, confirmed, time, date, location, recur, type, description, dateToday )" +
                         "VALUES( '" + getMeetingID() + "', '" + confirmed + "', '" + freeTime.firstEntry().getKey() + "', '" + freeTime.get(freeTime.firstKey()) + "', '" + this.location + "',  '" + this.recurring + "',  '" +
                                                    type + "', '" + this.description + "', '" + dateSent() + "');");
    }
    
    public void insertLectureQuery( String date, String time )
    {
            String type = "lecture";
        
            database.Insert("INSERT INTO meetings( m_id, confirmed, time, date, location, recur, type, description, dateToday )" +
                             "VALUES( '" + getMeetingID() + "', '1', '"+time+"', '"+date+"', '" + this.location + "',  'weekly',  '" +
                                                        type + "', '" + this.description + "', '" + dateSent() + "');");
    }
    
    public void insertNotQuery( String typeOfNotification ){
        database.Insert( "INSERT INTO notifications( m_id, is_seen, type, content, date, p_id )" +
                         "VALUES( '" + getMeetingID() + "', '" + "0" + "', '" + typeOfNotification + "',  '" + this.description+ "', '" + this.startDate + "',  '" +
                                                    recipient + "');");
    }
    
    public void insertNotGroupQuery( String typeOfNotification, ArrayList<String> listOfPeople){
       
        for(String person : listOfPeople){
            database.Insert( "INSERT INTO notifications( m_id, is_seen, type, content, date, p_id )" +
                         "VALUES( '" + getMeetingID() + "', '" + "0" + "', '" + typeOfNotification + "',  '" + this.description+ "', '" + this.startDate + "',  '" +
                                                    person + "');");
        }
    }
    
    public void insertOtherPIMQuery(String is_manager, ArrayList<String> recipients){
        for(String recip : recipients ){
            database.Insert( "INSERT INTO people_in_meetings( p_id,  m_id, is_manager )" +
                         "VALUES( '" + recip + "', '" + getMeetingID()  + "', '" + is_manager + "' );");
        }
    }
    
    public void insertPersonalPIMQuery(String user){
        database.Insert( "INSERT INTO people_in_meetings( p_id,  m_id, is_manager )" +
                         "VALUES( '" + user + "', '" + getMeetingID()  + "', '" + "1" + "' );");
    }
    
    public void insertPersonalQuery(){
        
        database.Insert("INSERT INTO meetings( m_id, confirmed, time, date, location, recur,  type, description, dateToday )" +
                         "VALUES( '" + getMeetingID() + "','" + "1" + "', '" + this.time + "', '" + this.startDate + "', '" + this.location + "',  '" + this.recurring + "',  '" +
                                                    "personal"+ "', '" + this.description + "', '" + dateSent() + "');");
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
    
    public boolean validatePersonal(){
        //Boolean value which is returned
        boolean valid = true;
        
            //If the date is null
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

            //If the date is null
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
    public boolean validateTutorial(){
        //Boolean value which is returned
        boolean valid = true;
        
            //If the email field is empty
            if( getRecipient().equals("") ){

                //Set validation to false
                valid = false;
                correctRecipient = false;
                array[0]="*Group does not exist";
                }   
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
    
    public String[] getErrors(){
        return array;
    }
}



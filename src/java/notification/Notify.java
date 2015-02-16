/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package notification;

/**
 *
 * @author mm37
 */
import database.DbClass;

public class Notify {
    
    DbClass database;
    
    control.MeetingChecker meeting;                                                         /////////TEST THIS TOMORROW!!!!!!!!!!!!!!!!!!!!!!
    
    private String content;
    
    private String date;
    
    String[] data;
    
    public Notify(){
        database = new DbClass();
        meeting = new control.MeetingChecker();
        this.content="";
        this.date="";
        database.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
    }
    
    public void insertNotQuery( String typeOfNotification ){
        database.Insert( "INSERT INTO notifications(  is_seen, type, content, date, p_id )" +
                         "VALUES( '" + "0" + "', '" + typeOfNotification + "',  '" + this.content+ "', '" + this.date + "',  '" +
                                                    "11123" + "');");
    }
    
    public String getContent(){
        //data = database.SelectRow( "select * from notifications where p_id='" + + "' and password = '" +  + "'");
        
        String firstName = data[2];
        String lastName = data[3];
        
        return content;
    }
}

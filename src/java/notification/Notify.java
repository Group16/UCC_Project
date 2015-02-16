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
    
    control.MeetingChecker meeting = new control.MeetingChecker();
    
    public Notify(){
        database = new DbClass();
    }
    
    public void insertNotQuery( ){
        database.Insert( "INSERT INTO notifications(  is_seen, type, content, date, p_id )" +
                         "VALUES( '" + "0" + "', '" + "meeting" + "',  '" + meeting.getDescription()+ "', '" + meeting.getStartDate() + "',  '" +
                                                    "11123" + "');");
    }
    
}

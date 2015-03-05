/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import database.DbClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author daniel
 */
public class MakeJSONArray 
{
    public JSONArray make( String p_id ) throws ParseException
    {
        JSONArray objArray = new JSONArray();
        Connection connectionObject = null;
        
        try
        {
            connectionObject = DriverManager.getConnection("jdbc:mysql://"+DbClass.getHost()+"/" + DbClass.getDatabase(), DbClass.getUser(), DbClass.getPassword());
        }
        catch ( Exception e ) {}
        
        database.DbClass db = new database.DbClass();

        Date date = new Date();
        String past;
        String future;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);

        //ADD AN IF SOME TIME

        cal.set(Calendar.MONTH, month-2);
        past = dateFormat.format(cal.getTime());
        cal.set(Calendar.MONTH, month+2);
        future = dateFormat.format(cal.getTime());

        try 
        {
            ArrayList meetings = new ArrayList();

            Statement statementObject = connectionObject.createStatement();
            ResultSet statementResult = statementObject.executeQuery("SELECT * FROM meetings AS m JOIN people_in_meetings AS pm ON m.m_id = pm.m_id WHERE pm.p_id ='" + p_id + "' AND m.date BETWEEN '" + past + "' AND '" + future + "'");

            while( statementResult.next() )
            {
                JSONMeeting meeting = new JSONMeeting( statementResult.getString(1), statementResult.getString(2), statementResult.getString(3), statementResult.getString(4), statementResult.getString(5), statementResult.getString(6), statementResult.getString(7), statementResult.getString(8) );
                meetings.add(meeting);
            }

            ResultSet statementResult2 = statementObject.executeQuery("SELECT * FROM meetings AS m JOIN  people_in_modules AS pm JOIN modules_in_meetings AS mm ON pm.mod_id = mm.mod_id AND m.m_id = mm.m_id WHERE pm.p_id = '" + p_id + "'");

            while( statementResult2.next() )
            {
                JSONMeeting lecture = new JSONMeeting( statementResult2.getString(1), statementResult2.getString(2), statementResult2.getString(3), statementResult2.getString(4), statementResult2.getString(5), statementResult2.getString(6), statementResult2.getString(7), statementResult2.getString(8) );
                meetings.add(lecture);
            }

            for( Object object : meetings )
            {   
                JSONMeeting m = (JSONMeeting) object;

                if ( m.getRecurring().equals("weekly") || m.getRecurring().equals("daily") || m.getRecurring().equals("fortnight") || m.getRecurring().equals("monthly") )
                {
                    Date recurDate = dateFormat.parse( m.getStartDate() );

                    String newDate;

                    Calendar recurCal = Calendar.getInstance();
                    recurCal.setTime(recurDate);

                    for ( int i=0 ; i < 12 ; i++ )
                    {   
                        newDate = dateFormat.format(recurCal.getTime());

                        JSONObject obj = new JSONObject();
                        obj.put("m_id", m.getM_id());                        
                        obj.put("start", newDate + "T" + m.getTime() );
                        obj.put("rawDate", newDate);
                        obj.put("rawTime", m.getTime());
                        obj.put("location", m.getLocation());
                        obj.put("recur",m.getRecurring());
                        obj.put("type", m.getType());
                        obj.put("title", m.getDescription());
                        
                        obj = formatObject( obj, m );
                        
                        objArray.add(obj);

                        // This needs to be after the object is created for reasons.
                        if ( m.getRecurring().equals("weekly") )
                        {
                            recurCal.add(Calendar.WEEK_OF_YEAR, 1);
                        }
                        else if ( m.getRecurring().equals("daily") )
                        {
                            recurCal.add(Calendar.DAY_OF_YEAR, 1);
                        }
                        else if ( m.getRecurring().equals("fortnight") )
                        {
                            recurCal.add(Calendar.WEEK_OF_YEAR, 2);
                        }
                        else if ( m.getRecurring().equals("monthly") )
                        {
                            recurCal.add(Calendar.MONTH, 1);
                        }
                    }
                }
                else
                {
                    JSONObject obj = new JSONObject();
                    obj.put("m_id", m.getM_id());                        
                    obj.put("start", m.getStartDate() + "T" + m.getTime() );
                    obj.put("rawDate", m.getStartDate());
                    obj.put("rawTime", m.getTime());
                    obj.put("location", m.getLocation());
                    obj.put("recur",m.getRecurring());
                    obj.put("type", m.getType());
                    obj.put("title", m.getDescription());

                    obj = formatObject( obj, m );

                    objArray.add(obj);
                }
            }
        }
        catch ( Exception e ) {}
        
        return objArray;
    }
    
    private JSONObject formatObject( JSONObject obj, JSONMeeting m )
    {
        if(m.getType().equals("meeting"))
        {
            obj.put("color", "#F95A5C");
        }
        if(m.getType().equals("personal"))
        {
            obj.put("color", "#FD1CA8");
        }
        if(m.getConfirmed().equals("0"))
        {
            obj.put("color", "#FFC1D4");
        }
        if(m.getType().equals("lecture"))
        {
            obj.put("color", "#FAA0A1");
        }
        if(m.getType().equals("tutorial"))
        {
            obj.put("color", "#FD1367");
        }
        
        return obj;
    }
}

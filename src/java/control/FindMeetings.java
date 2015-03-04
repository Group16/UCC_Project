package control;

import database.DbClass;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

public class FindMeetings 
{
    private final DbClass db = new DbClass();
    
    private static final int HOURS_IN_DAY = 10; //There are 10 hours in working day.
    private static final int HOUR_TO_START = 8; //Day starts at 8 am
    
    public FindMeetings( )
    {
        db.setup();
    }
    
    public ArrayList<String> getMeetingsSlot(String p_id, String date){
        
        return db.outputAllRows("SELECT * FROM meetings AS m JOIN people_in_meetings AS pm ON m.m_id = pm.m_id WHERE pm.p_id = '" + p_id + "' AND m.date = '" + date + "'");
    }
    
    public ArrayList<String> getTutorialSlot( String p_id, String date ){
        
        return db.outputAllRows("SELECT * FROM meetings AS m JOIN  people_in_modules AS pm JOIN modules_in_meetings AS mm "
                              + "ON pm.mod_id = mm.mod_id AND m.m_id = mm.m_id WHERE pm.p_id = '" + p_id + "'");
    }
    
    public TreeMap<String,String> getFreeTime( ArrayList<String> p_ids, String date )
    {   
        String mTime = "";
        boolean isDone = false;
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date meetingDate = new Date();
        try
        {
            meetingDate = dateFormat.parse(date);
        } 
        catch ( Exception e ) {}
        
        Calendar cal = Calendar.getInstance();
        
        //Set the date, then set the time to 9pm to grantee it searches a full day.
        cal.setTime( meetingDate );
        cal.set( Calendar.HOUR_OF_DAY, 21 );
        cal.set( Calendar.MINUTE, 00 );
        
        int failsafe = 0;
        do
        {   
            final ArrayList<MeetingMap> meetingMaps = new ArrayList<>();
            
            //While the day is Sunday or Saterday, add a day untill its not.
            while ( cal.get( Calendar.DAY_OF_WEEK ) == 1 || cal.get( Calendar.DAY_OF_WEEK ) == 7 )
            {
                cal.add(Calendar.DAY_OF_YEAR, 1);
            }
            
            for ( String p_id : p_ids )
            {   
                byte[] bytes = new byte[HOURS_IN_DAY];

                ArrayList<String> meetings = getMeetingsSlot(p_id, dateFormat.format(cal.getTime()));
                ArrayList<String> lectures = getTutorialSlot(p_id, dateFormat.format(cal.getTime()));
                
                meetings.addAll(lectures);
                
                for ( int i=0 ; i < HOURS_IN_DAY ; i++ )
                {
                    final String bTime = intToTimeString( i );

                    for ( String meetingTime : meetings )
                    {
                        if ( meetingTime.equals( bTime ) )
                        {
                            bytes[i] = 1;
                        }
                    }
                }

                MeetingMap meetingMap = new MeetingMap( p_id, bytes );
                meetingMaps.add(meetingMap);
            }

            byte[] startBytes = new byte[HOURS_IN_DAY];
            Integer lastFreeSlot = null;
            
            for ( int i=0 ; i < HOURS_IN_DAY ; i++ )
            {
                for ( MeetingMap meetingMap : meetingMaps )
                {
                    if ( meetingMap.getBytes()[i] == 1 )
                    {
                        startBytes[i] = 1;
                    }
                }

                if ( startBytes[i] == 0 )
                {
                    mTime = intToTimeString( i );
                    isDone = true;
                }
                
//                if ( startBytes[i] == 0 )
//                {
//                    lastFreeSlot = i;
//                }
//                
//                if ( ( startBytes[i] == 1 && lastFreeSlot != null ) || i == HOURS_IN_DAY )
//                {
//                    mTime = intToTimeString( lastFreeSlot );
//                    isDone = true;
//                }
            }
            
            if ( ! isDone )
            {
//                meetingMaps.clear();
                cal.add( Calendar.DAY_OF_YEAR, 1 );
            }
            
            // Kill the loop after x days.
            failsafe++;
            if ( failsafe > 20 )
            {
                return null;
            }
            
        } while ( ! isDone );
        
        TreeMap<String,String> returnMap = new TreeMap<>();
        returnMap.put(mTime, dateFormat.format(cal.getTime()));

        return returnMap;
    }
    
    private String intToTimeString( int rawInt )
    {
        int intTime = rawInt + HOUR_TO_START;
        String time = "";
        if ( intTime < 10 )
        {
            time = "0";
        }
        return time + intTime + ":00:00";
    }
    
    private class MeetingMap
    {
        private final String p_id;
        private final byte[] bytes;
        
        public MeetingMap( String p_id, byte[] bytes )
        {
            this.bytes = bytes;
            this.p_id = p_id;
        }
        
        public String getP_id()
        {
            return this.p_id;
        }
        
        public byte[] getBytes()
        {
            return this.bytes;
        }
    }
}

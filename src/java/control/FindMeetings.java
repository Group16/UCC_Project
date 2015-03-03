package control;

import Classes.Meeting;
import database.DbClass;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Interval;

public class FindMeetings 
{
    DbClass db = new DbClass();
    
    ArrayList<MeetingMap> meetingMaps = new ArrayList<MeetingMap>();
    
    public FindMeetings( )
    {
        db.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
    }
    
    public ArrayList<String> getMeetingsSlot(String p_id, String date){
        
        return db.outputAllRows("SELECT * FROM meetings AS m JOIN people_in_meetings pm WHERE pm.p_id = '" + p_id + "' AND m.date = '" + date + "'");
    }
    
    public String getFreeTime( ArrayList<String> p_ids, String date )
    {
        
        final int hours = 9;
        
        for ( String p_id : p_ids )
        {
            byte[] bytes = new byte[hours];
            
           ArrayList<String> meetings = getMeetingsSlot(p_id, date);
            
            for ( int i=0 ; i < bytes.length ; i++ )
            {
                
                String bTime = "";
                if ( i < 10 )
                {
                    bTime = "0";
                }
                bTime = bTime + i + ":00:00";
                
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

        byte[] startBytes = new byte[hours];
        
        for ( int i=0 ; i < startBytes.length ; i++ )
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
                int k = i+9;
                String mTime = "";
                if ( k < 10 )
                {
                    mTime = "0";
                }
                mTime = mTime + k + ":00:00";
                return  mTime;
            }
        }
        return null;
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

package Sorting;

import Classes.Meeting;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Interval;

public class FindMeetings 
{
    SQL db;
    ArrayList<MeetingMap> meetingMaps;
    
    public FindMeetings( SQL db )
    {
        this.db = db;
    }
    
    public String getFreeTime( ArrayList<Integer> p_ids, Date date )
    {
        final DateTime start = new DateTime( date );
        final int hours = 9;
        
        for ( Integer p_id : p_ids )
        {
            byte[] bytes = new byte[hours];
            
            ArrayList<Meeting> meetings = db.getAllMeetingsOnDate( p_id.toString(), date.toString() );
            
            for ( int i=0 ; i < bytes.length ; i++ )
            {
                String bTime = "";
                if ( i < 10 )
                {
                    bTime = "0";
                }
                bTime = bTime + i + ":00:00";
                
                for ( Meeting meeting : meetings )
                {
                    if ( meeting.getTime().toString().equals( bTime ) )
                    {
                        bytes[i] = 1;
                    }
                }
            }
            
            MeetingMap meetingMap = new MeetingMap( p_id, bytes );
            meetingMaps.add(meetingMap);
        }

        byte[] startBytes = new byte[hours];
        
        for ( int i=0 ; i <= startBytes.length ; i++ )
        {
            for ( MeetingMap meetingMap : meetingMaps )
            {
                if ( meetingMap.getBytes()[i] == 1 )
                {
                    startBytes[i] = 1;
                }
            }
            
            if ( startBytes[i] == 1 )
            {
                String mTime = "";
                if ( i < 10 )
                {
                    mTime = "0";
                }
                mTime = mTime + i + ":00:00";
                return  mTime;
            }
        }
        return null;
    }
    
    private class MeetingMap
    {
        private final int p_id;
        private final byte[] bytes;
        
        public MeetingMap( int p_id, byte[] bytes )
        {
            this.bytes = bytes;
            this.p_id = p_id;
        }
        
        public int getP_id()
        {
            return this.p_id;
        }
        
        public byte[] getBytes()
        {
            return this.bytes;
        }
    }
}

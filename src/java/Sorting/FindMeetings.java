package Sorting;

import Classes.Meeting;
import database.SQL;
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
    
    public Date getFreeTime( ArrayList<Integer> p_ids, Date startDate, Date endDate )
    {
        DateTime start = new DateTime( startDate );
        DateTime end = new DateTime( endDate );

        Interval interval = new Interval(end, start);
        int hours = ((Long)(((interval.getEndMillis() / 60) / 60) / 60)).intValue();
        
        for ( Integer p_id : p_ids )
        {
            byte[] bytes = new byte[hours];
            
            ArrayList<Meeting> meetings = db.getAllMeetingsBetweenDates( p_id.toString(), startDate.toString(), endDate.toString() );
            
            int counter = 0;
            DateTime lastMeeting = start;
            
            for ( byte currentByte : bytes )
            {
                for ( Meeting meeting : meetings )
                {
                    Date time = meeting.getTime();
                    
                    if ( time.equals(byteTime) || (counter >= 9 && counter <= 19) )
                    {
                        currentByte = 1;
                    }
                }
                
                counter++;
            }
            
            MeetingMap meetingMap = new MeetingMap( p_id, bytes, start );
            
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
            
            if ( startBytes[i] == 0 )
            {
                return i;
            }
        }
    }
   
    private Date dateFromByteTime( byte time )
    {
        SimpleDateFormat format = new SimpleDateFormat("hh");
                    
        Date byteTime;
        try
        {
            byteTime = format.parse( ((Integer)(counter + 9)).toString() );
        }
        catch( Exception e )
        {
        }
    }
    
    private class MeetingMap
    {
        private final int p_id;
        private final byte[] bytes;
        private final DateTime start;
        
        public MeetingMap( int p_id, byte[] bytes, DateTime start )
        {
            this.bytes = bytes;
            this.p_id = p_id;
            this.start = start;
        }
        
        public int getP_id()
        {
            return this.p_id;
        }
        
        public byte[] getBytes()
        {
            return this.bytes;
        }
        
        public DateTime getStart()
        {
            return this.start;
        }
    }
}

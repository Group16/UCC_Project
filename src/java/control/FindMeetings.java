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
    DbClass db = new DbClass();
    
    ArrayList<MeetingMap> meetingMaps = new ArrayList<>();
    
    public FindMeetings( )
    {
        db.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
    }
    
    public ArrayList<String> getMeetingsSlot(String p_id, String date){
        
        return db.outputAllRows("SELECT * FROM meetings AS m JOIN people_in_meetings AS pm ON m.m_id = pm.m_id WHERE pm.p_id = '" + p_id + "' AND m.date = '" + date + "'");
    }
    
    public TreeMap<String,String> getFreeTime( ArrayList<String> p_ids, String date )
    {
        final int hours = 10;
        
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
        cal.setTime(meetingDate);
        
        int failsafe = 0;
        do
        {   
            for ( String p_id : p_ids )
            {
                System.out.print( dateFormat.format(cal.getTime()) );
                
                byte[] bytes = new byte[hours];

                ArrayList<String> meetings = getMeetingsSlot(p_id, dateFormat.format(cal.getTime()));

                for ( int i=0 ; i < bytes.length ; i++ )
                {
                    int j = i+8;
                    String bTime = "";

                    if ( j < 10 )
                    {
                        bTime = "0";
                    }
                    bTime = bTime + j + ":00:00";

                    for ( String meetingTime : meetings )
                    {
                        System.out.println(meetingTime);
                        if ( meetingTime.equals( bTime ) )
                        {
                            bytes[i] = 1;
                        }
                    }
                }
                 System.out.print(p_id + " : ");
                for(byte diffword : bytes){
                    System.out.print( diffword);
                }
                System.out.println("!!!!!!");
                MeetingMap meetingMap = new MeetingMap( p_id, bytes );
                meetingMaps.add(meetingMap);
            }

            byte[] startBytes = new byte[hours];
            int lastFreeSlot = 0;
            
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
                    lastFreeSlot = i;
                }
                else
                {
                    int k = lastFreeSlot+8;
                    mTime = "";
                    if ( k < 10 )
                    {
                        mTime = "0";
                    }
                    mTime = mTime + k + ":00:00";
                    
                    isDone = true;
                }
            }
            
            if ( ! isDone )
            {
                meetingMaps.clear();
                cal.add(Calendar.DAY_OF_YEAR, 1);
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

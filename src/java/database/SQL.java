package database;

import Classes.Meeting;
import User.User;
import java.sql.*;
import java.util.*;

public abstract class SQL
{
    private Connection connection;

    public SQL()
    {
        try
        {
            if( connection != null && ! connection.isClosed() )
            {
                connection.createStatement().execute( "/* ping */ SELECT 1" );
            }
        }
        catch( SQLException e )
        {
            connection = getNewConnection();
        }
    }

	/**
	 * Gets a connection to the selected SQL type (MySQL, SOLite)
	 * @return the connection object.
	 */
    protected abstract Connection getNewConnection();

	/**
	 * Gets the name of the SQL type.
	 * @return The name of the database type.
	 */
    protected abstract String getName();

	/**
	 * Runs a query on the database and returns an ArrayList containing HashMaps of each row.
	 * 
	 * @param sql a String containing the SQL of the query.
	 * @param hasReturn true if the query should return an ArrayList, false to discard any return.
	 * @return an ArrayList containing HashMaps of each row.
	 */
    private ArrayList<HashMap<String,String>> query( String sql, boolean hasReturn )
    {
        if( ! checkConnection() )
        {
            System.out.println( "Error in db" );
            return null;
        }

        PreparedStatement statement;
        try
        {
            statement = connection.prepareStatement( sql );

            if( ! hasReturn )
            {
                statement.execute();
                return null;
            }

            ResultSet set = statement.executeQuery();

            ResultSetMetaData md = set.getMetaData();
            int columns = md.getColumnCount();

            ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>( 50 );

            while( set.next() )
            {
                HashMap<String,String> row = new HashMap<String,String>( columns );
                for( int i = 1; i <= columns; ++i )
                {
                    row.put( md.getColumnName( i ), set.getObject( i ).toString() );
                }
                list.add( row );
            }

            if( list.isEmpty() )
            {
                return null;
            }

            return list;
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return null;
    }

	/**
	 * Checks that the connection is active and makes a new one if it is not.
	 * @return a boolean, true if an active connection exists.
	 */
    public boolean checkConnection()
    {
        try
        {
            if( connection == null || connection.isClosed() )
            {
                connection = getNewConnection();

                if( connection == null || connection.isClosed() )
                {
                    return false;
                }
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();

            return false;
        }

        return true;
    }

	/**
	 * Disconnects from the database.
	 */
    public void disconnect()
    {
        try
        {
            if (connection != null)
            {
                connection.close();
            }
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    /**
     * Gets the data for a given meeting.
     *
     * @param m_id the meeting id.
     * @return a Meeting object containing the data of a meeting with a given m_id
     */
    public Meeting getMeeting( String m_id )
    {
	HashMap<String,String> meetingData;
		
        ArrayList<HashMap<String,String>> data = query( "SELECT * FROM meetings WHERE m_id = '" + m_id + "';", true );
        if( data == null )
        {
            return null;
        }

	meetingData = data.get( 0 );
		
        Meeting meeting = new Meeting( meetingData.get("m_id"), meetingData.get("time"), meetingData.get("date"), meetingData.get("location"), meetingData.get("description"), meetingData.get("type"), meetingData.get("recur_type"), meetingData.get("recur_end")  );
        
        return meeting;
    }
    

    public User getPerson (String email)
    {
         HashMap<String,String> userByEmail;
         ArrayList<HashMap<String,String>> data = query( "SELECT * FROM people WHERE email = '" + email + "';", true );
         if( data == null )
        {
            return null;
        }
         
        userByEmail = data.get( 0 );
        User person = new User(userByEmail.get("p_id"),userByEmail.get("firstname"),userByEmail.get("surname"), userByEmail.get("password"),userByEmail.get("isAdmin"),userByEmail.get("email"),userByEmail.get("userType"),userByEmail.get("date") );
        
        return person;
    }
    /**
     * Gets all the meetings of a person
     *
     * @param p_id the person id.
     * @return a list of Meeting objects containing the data of the meetings
     */
    public ArrayList<Meeting> getAllMeetings( String p_id )
    {
	ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		
        ArrayList<HashMap<String,String>> data = query( "SELECT * FROM meetings AS m JOIN people_in_meetings AS pm WHERE pm.p_id = '" + p_id + "';", true );
        if( data == null )
        {
            return null;
        }

        for( int i=0 ; i < data.size() ; i++ )
        {
            HashMap<String,String> meetingData;
            meetingData = data.get( i );
		
            Meeting meeting = new Meeting( meetingData.get("m_id"), meetingData.get("time"), meetingData.get("date"), meetingData.get("location"), meetingData.get("description"), meetingData.get("type"), meetingData.get("recur_type"), meetingData.get("recur_end")  );
            
            meetings.add( meeting );
        }
        
        return meetings;
    }
    
    /**
     * Gets all the meetings of a person between 2 dates
     *
     * @param p_id the person id.
     * @param start_date the start of the date search
     * @param end_date the end of the date search
     * @return a list of Meeting objects containing the data of the meetings
     */
    public ArrayList<Meeting> getAllMeetingsBetweenDates( String p_id, String start_date, String end_date )
    {
	ArrayList<Meeting> meetings = new ArrayList<Meeting>();
		
        ArrayList<HashMap<String,String>> data = query( "SELECT * FROM meetings AS m JOIN people_in_meetings pm WHERE pm.p_id = " +p_id+ " AND m.date BETWEEN '" +start_date+ "' AND '" +end_date+ "';", true );
        if( data == null )
        {
            return null;
        }

        for( int i=0 ; i < data.size() ; i++ )
        {
            HashMap<String,String> meetingData;
            meetingData = data.get( i );
	
            Meeting meeting = new Meeting( meetingData.get("m_id"), meetingData.get("time"), meetingData.get("date"), meetingData.get("location"), meetingData.get("description"), meetingData.get("type"), meetingData.get("recur_type"), meetingData.get("recur_end")  );
            
            meetings.add( meeting );
        }
        
        return meetings;

    }
}

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
	 * @param hasReturn true if the qury should return an ArrayList, false to discard any return.
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
        cache.clear();

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
	 * Gets the data for a givan meeting.
	 *
	 * @param m_id the meeting id.
	 * @return a HashMap containing the data of a meeting with a givan m_id
	 */
    public HashMap<String,String> getMeeting( String m_id )
    {
		HashMap<String,String> meeting;
		
        ArrayList<HashMap<String,String>> data = query( "SELECT * FROM meetings WHERE m_id = '" + m_id + "';", true );
        if( data == null )
        {
            return null;
        }

		meeting = data.get( 0 );
		
        return meeting;
    }
}

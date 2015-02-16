import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL extends SQL
{
	private final String host;
	private final String port;
	private final String database;
	
	private final String user;
	private final String pass;
	
    public MySQL()
    {
		this.host = "localhost";
		this.port = "3306";
		this.database = "ucc_connect";
		
		this.user = "root";
		this.pass = "root";
    }

    protected Connection getNewConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

            return DriverManager.getConnection( url, user, pass );
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public String getName()
    {
        return "MySQL";
    }
}
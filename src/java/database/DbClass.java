/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Shiny, @author  Michael Mullarkey(112457292)
 */
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

public class DbClass 
{
    private Statement statementObject;
    private Connection connectionObject;
    //Connections to database
    private static final String HOST  = "cs1.ucc.ie";
    private static final String DATABASE  = "2016_mm37";
    private static final String USERNAME = "mm37";
    private static final String PASSWORD = "uohongah";
    
    private boolean setup = false;
    public boolean queryCorrect = false;
    /**
     * Set up connection to the database
     * @return 
     */
    public String setup()
    {
        String URL = "jdbc:mysql://" + HOST + "/" + DATABASE;
        
        try 
        {// Initialiase drivers
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (Exception exceptionObject) 
        {
            writeLogSQL(URL + " caused error " + exceptionObject.getMessage()+" Error dbclass.setup.1. ");
            return("Failed to load JDBC/ODBC driver. Error dbclass.setup.1 PLEASE report this error");
        }
        
        try 
        {
            // Establish connection to database
            connectionObject = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            setup=true;
        } 
        catch (SQLException exceptionObject) 
        {
            writeLogSQL(URL + " caused error " + exceptionObject.getMessage()+" Error dbclass.setup.2");
            return("Problem with setting up " + URL+" Error dbclass.setup.2 PLEASE report this error");
        }

        return "";
    } // DatabaseConnectorNew constructor

    /**
     * A method which checks if a query is true or not
     * @param query the query to be checked
     * @return a boolean if the query is true or not
     */
    public String checkQuery(String query)
    { 
        try 
        {
            // Establish connection to database
            setup=true;
            
            Statement st = connectionObject.createStatement(); 
            ResultSet rs = st.executeQuery(query); 
            
            if( rs.next() ) 
            { 
               queryCorrect = true;
            } 
 
        } catch (SQLException exceptionObject) {}

        return "";
    }
    /**
     * Get the length of a outputs from a query
     * @param query the query to be checked
     * @return int number
     */
    public int getLength(String query)
    {
        int rowcount = 0;
        
        try 
        {
            statementObject = connectionObject.createStatement();

            ResultSet statementResult = statementObject.executeQuery(query); //Should connection be left open?
            rowcount = 0;
            if (statementResult.last()) 
            {
                rowcount = statementResult.getRow();
                statementResult.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
        
        } catch (SQLException exceptionObject) {}
        
        return rowcount;
    }
    
    /**
     * Get all the rows from a query
     * @param query the query we want the data from
     * @param num the number of the result set we want
     * @return a list of the data from all the rows
     */
    public  ArrayList<String> outputAllRows(String query, int num)
    {
        ArrayList<String> list = new ArrayList<>();
        
        try 
        {// Make connection to database
           statementObject = connectionObject.createStatement();
           ResultSet statementResult = statementObject.executeQuery(query);

           while( statementResult.next() )
           {
               list.add(statementResult.getString(num));
           }
        } 
        catch (SQLException exceptionObject) {}
     
        return list;
    }
    
     public boolean issetup()
    {
        return setup;
    }
    
    public void Close()
    {
        try {
            // Establish connection to database
            connectionObject.close(); 
        }
        catch (SQLException exceptionObject)
        {
            System.out.println("Problem with closing up ");
            writeLogSQL("closing caused error " + exceptionObject.getMessage());
        }
    } //CloseDatabaseConnection

    public void Insert(String SQLinsert)
    {
        // Setup database connection details
        try {
            // Setup statement object
            statementObject = connectionObject.createStatement();

            // execute SQL commands to insert data
            statementObject.executeUpdate(SQLinsert);
            writeLogSQL(SQLinsert +" Executed OK");
            }
        catch (SQLException exceptionObject) {
            System.out.println(SQLinsert+" - Problem is : " + exceptionObject.getMessage());
            writeLogSQL(SQLinsert + " caused error " + exceptionObject.getMessage());
            }
    } // End Insert

   public String[] SelectRow(String SQLquery)
   {
        String Result[];
        // Send an SQL query to a database and return the *single column* result in an array of strings
        try 
        {   // Make connection to database
            statementObject = connectionObject.createStatement();

            ResultSet statementResult = statementObject.executeQuery(SQLquery); //Should connection be left open?

            ResultSetMetaData rsmd = statementResult.getMetaData();
            int nrOfColumns = rsmd.getColumnCount();

            Result = new String[nrOfColumns];

            statementResult.next();
            
            int currentCounter = 0;

            while (currentCounter<nrOfColumns) // While there are rows to process
            {
                // Get the first cell in the current row
                Result[currentCounter] = statementResult.getString(currentCounter+1);
                currentCounter++;
            }
            // Close the link to the database when finished 
        } 
        catch (Exception e) 
        {
            System.err.println("Select problems with SQL " + SQLquery);
            System.err.println("Select problem is " + e.getMessage());
            Result = new String[0]; //Need to setup result array to avoid initialisation error
            writeLogSQL(SQLquery + " caused error " + e.getMessage());
        
        }
        writeLogSQL(SQLquery + "worked ");
        
        return Result;
    } // End SelectRow
   
    public String[] SelectColumn(String SQLquery)
    {
        String Result[];
        // Send an SQL query to a database and return the *single column* result in an array of strings
        try 
        {// Make connection to database
            statementObject = connectionObject.createStatement(); //Should connection be left open?

            ResultSet statementResult = statementObject.executeQuery(SQLquery);

            // Start solution from http://www.coderanch.com/t/303346/JDBC/java/find-number-rows-resultset
            int rowcount = 0;
            if (statementResult.last()) {
                rowcount = statementResult.getRow();
                statementResult.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
                }
            // End solution from http://www.coderanch.com/t/303346/JDBC/java/find-number-rows-resultset

            Result = new String[rowcount];

            int currentCounter = 0;

            while (statementResult.next()) // While there are rows to process
            {
                // Get the first cell in the current row
                Result[currentCounter] = statementResult.getString(1);
                currentCounter++;

            }
            // Close the link to the database when finished
        } 
        catch (Exception e) 
        {
            System.err.println("Select problems with SQL " + SQLquery);
            System.err.println("Select problem is " + e.getMessage());
            Result = new String[0]; //Need to setup result array to avoid initialisation error
            writeLogSQL(SQLquery + " caused error " + e.getMessage());
        }
        
        writeLogSQL(SQLquery + "worked ");
        return Result;
    } // End Select

    public void writeLogSQL(String message) 
    {
        PrintStream output;
        try 
        {
            output = new PrintStream(new FileOutputStream("sql-logfile.txt", true));
            output.println(new java.util.Date() + " " + message);
            System.out.println(new java.util.Date() + " " + message);
            output.close();
        } catch (IOException ieo) {}
    } // End writeLog

    public static String getHost()
    {
        return HOST;
    }
    
    public static String getDatabase()
    {
        return DATABASE;
    }
    
    public static String getUser()
    {
        return USERNAME;
    }
    
    public static String getPassword()
    {
        return PASSWORD;
    }
    
} //End dblib

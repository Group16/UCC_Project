
package User;

import database.DbClass;
import java.util.Date;

/**
 *
 * @author vnl1
 */
public class RootUser extends Admin
{
     DbClass database;
     public RootUser(String fName,String lName, String ID, String isAdmin, String password,
                   String email,String userType,String dateToday)
     {
         super(fName,lName,ID,isAdmin,password,email,userType,dateToday);
          database = new DbClass();
          database.setup("cs1.ucc.ie","2016_vnl1", "vnl1","ahraiziu");
          //database.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
     }            
      public void createAdmin(int id, String fName, String lName,int password, String email, String date)
     {
         database.Insert( "INSERT INTO people( p_id, p_type, firstname, surname, password, email,dateToday )" +
                         "VALUES( '" + id + "', '"+ fName + "', '" + lName + "',  "
                             + "'" + password + "', '" + email + "', " + date + "');");
     }
   
}

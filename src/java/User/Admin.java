
package User;

import Classes.*;
import database.DbClass;
import java.util.Date;

/**
 *
 * @author vnl1
 */
public class Admin extends User 
{
     DbClass database;
     private String admin;
     
     public Admin(String fName,String lName, int ID, boolean isAdmin, String password,
                   String email, String userType,Date dateToday)
     {
         super(fName,lName,ID,isAdmin,password,email,userType,dateToday);
          database = new DbClass();
          database.setup("cs1.ucc.ie","2016_vnl1", "vnl1","ahraiziu");
          //database.setup("cs1.ucc.ie","2016_mm37", "mm37","uohongah");
     }
     
     public void createLecturer(int id, String fName, String lName,int password, String email, String date)
     {
         database.Insert( "INSERT INTO people( p_id, p_type, firstname, surname, password, email,dateToday )" +
                         "VALUES( '" + id + "', '"+ fName + "', '" + lName + "',  "
                             + "'" + password + "', '" + email + "', " + date + "');");
     }
    // public void assignLecturerModules()
    //{
      //   //create module class
     //}
     public void deleteUser(int userID)
     {
        database.Insert("DELETE FROM people where p_id =( userID)");
     }   
     
     public void createModules(int moduleID, String moduleName,String courseName)
     {
         database.Insert("INSERT INTO modules(mod_id,name,c_id)" + 
                 "VALUES('"+ moduleID +" ','"+ moduleName+ "','" + courseName +"');");
         
     }
    // public void updateModuleTimes()
     //{
        // create module class
     //}        
     @Override
   public void setUserType(String userType)
    {
        userType = admin;
    }
}

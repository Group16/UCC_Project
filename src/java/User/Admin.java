
package User;

import Classes.*;
import database.DbClass;
import java.util.Date;

/**
 *
 * @author vnl1
 */
public abstract class Admin extends User 
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
     
     public void createLecturer()
     {
         database.Insert( "INSERT INTO people( p_id, p_type, firstname, surname, password, email,dateToday )" +
                         "VALUES( '" + this.getID() + "', '" + this.getFirstName() + "', '" + this.getLastName() + "',  "
                             + "'" + this.getPassword() + "', '" + this.getEmail() + "', " + this.getDate() + "');");
     }
     public void assignLecturerModules()
     {
         //create module class
     }
     public void deleteUser()
     {
        database.Insert("DELETE FROM people where p_id = this.getID()");
     }   
     
     public void createModules()
     {
        // database.Insert("INSERT INTO modules(mod_id,name,c_id)" + 
                 //"VALUES('"+ Module.getModuleID() +" ','"+Module.getModuleName()+ "','" + Module.getCourseName()+"');");
                 // error - object not created yet
     }
     public void updateModuleTimes()
     {
        // create module class
     }        
     @Override
   public void setUserType(String userType)
    {
        userType = admin;
    }
}

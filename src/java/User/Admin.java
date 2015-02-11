
package User;

/**
 *
 * @author vnl1
 */
public abstract class Admin extends User
{
     public Admin(String fName,String lName, int ID, boolean isAdmin, String password,
                   String email)
     {
         super(fName,lName,ID,isAdmin,password,email);
     } 
     
     public void createLecturer()
     {
         
     }
     public void assignLecturerModules()
     {
         
     }
     public void deleteLecturer()
     {
         
     }   
     public void deleteStudent()
     {
         
     }     
     
     public void updateModuleTimes()
     {
         
     }        
}

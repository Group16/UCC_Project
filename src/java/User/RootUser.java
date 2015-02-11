
package User;

/**
 *
 * @author vnl1
 */
public class RootUser extends Admin
{
     public RootUser(String fName,String lName, int ID, boolean isAdmin, String password,
                   String email)
     {
         super(fName,lName,ID,isAdmin,password,email);
     }            
     public void createAdmin()
     {
         
     }
     
     public void updateDatabase()
     {
         
     }
}

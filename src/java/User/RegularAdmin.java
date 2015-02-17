package User;

import java.util.Date;

/**
 *
 * @author vnl1
 */
public class RegularAdmin extends Admin
{
     public RegularAdmin(String fName,String lName, String ID, String isAdmin, String password,
                   String email,String userType,String dateToday)
     {
         super(fName,lName,ID,isAdmin,password,email,userType,dateToday);
     } 
     
    
}

package User;

import database.DbClass;
import java.util.Date;
import java.text.ParseException;

/**
 *
 * @author vnl1
 */
public class RegularAdmin extends Admin
{
     public RegularAdmin(String fName,String lName, String ID, String password,
                   String email,String userType,String dateToday) throws ParseException
     {
         super(fName,lName,ID,password,email,userType,dateToday);
          
     } 
     
    
}

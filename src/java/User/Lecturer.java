
package User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vnl1
 */
public class Lecturer extends User
{
    private List modules;
    private String student;
    private String lecturer;
    public Lecturer(String fName,String lName, String ID, String isAdmin, String password,
                   String email,String userType,String dateToday) throws ParseException
        {
            super(fName,lName,ID,password,email,userType,dateToday);
            modules = new ArrayList();
        }
    
    @Override
   public void setUserType(String userType)
    {
        userType = lecturer;
    }
    
}

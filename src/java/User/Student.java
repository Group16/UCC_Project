package User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vnl1
 */
public class Student extends User
{
    private List stream;
    private List modules;
    private String student;
    public Student(String fName,String lName, int ID, boolean isAdmin, String password,
                   String email,String userType,Date dateToday)
    {
        super(fName,lName,ID,isAdmin,password,email,userType,dateToday);
         stream = new ArrayList();
         modules = new ArrayList();
         
       
         
    }
    
    public List getStream()
    {
        return stream;
    }
    public List getModules()
    {
        return modules;
    }
    
   @Override
   public void setUserType(String userType)
    {
        userType = student;
    }
    
}

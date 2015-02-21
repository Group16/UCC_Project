package User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

/**
 *
 * @author vnl1
 */
public class Student extends User
{
    private List stream;
    private List modules;
    private String student;
    public Student(String fName,String lName, String ID, String password,
                   String email,String userType,String dateToday) throws ParseException
    {
        super(fName,lName,ID,password,email,userType,dateToday);
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

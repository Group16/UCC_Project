package User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vnl1
 */
public class Student extends User
{
    private List stream;
    private List modules;
    public Student(String fName,String lName, int ID, boolean isAdmin, String password,
                   String email)
    {
        super(fName,lName,ID,isAdmin,password,email);
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
    
}

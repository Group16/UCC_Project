
package User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vnl1
 */
public class Lecturer extends User
{
    private List modules;
    public Lecturer(String fName,String lName, int ID, boolean isAdmin, String password,
                   String email)
        {
            super(fName,lName,ID,isAdmin,password,email);
            modules = new ArrayList();
        }
    
}

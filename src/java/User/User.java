package User;

import java.util.Date;

/**
 *
 * @author vnl1
 */
public class User 
{
    private String firstName;
    private String surname;
    private int ID;
    private boolean isAdmin;
    private String password;
    private String email;
    private String userType;
    private Date dateToday;
    
    
    
    public User(String fName,String lName, String ID, String isAdmin, String password,
                   String email,String userType,String dateToday)
    {
        fName = firstName;
        lName = surname;
        this.ID = ID;
        this.isAdmin = isAdmin;
        this.password= password;
        this.email = email;
        this.userType = userType;
        this.dateToday = dateToday;
                
                
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName ()
    {
        return surname;
    }
    
    public int getID()
    {    
        return ID;
    }
    
    public String getPassword()
    {
        return password;    
    }
    
    public String getEmail()
    {
        return email;    
    } 
    
    public void setEmail(String email)
    {
        this.email = email;
    }
     public String getUserType()
    {
        return userType;
    }
     public Date getDate()
     {
         return dateToday;      
     }
     public abstract void setUserType(String userType);
}
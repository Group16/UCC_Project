package User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vnl1
 */
public abstract class User 
{
    private String firstName;
    private String surname;
    private int ID;
    //private boolean isAdmin;
    private String password;
    private String email;
    private String userType;
    private Date dateToday;
    DateFormat format ; 
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   
    
    
    
    public User(String fName,String lName, String ID, String password,
                   String email,String userType,String dateToday) throws ParseException
    {
       
        fName = firstName;
        lName = surname;
        this.ID = Integer.parseInt(ID);
        this.password= password;
        this.email = email;
        this.userType = userType;
        this.dateToday = dateFormat.parse(dateToday);
                
                
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
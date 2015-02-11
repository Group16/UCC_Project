package User;
/**
 *
 * @author vnl1
 */
public abstract class User 
{
    private String firstName;
    private String surname;
    private int ID;
    private boolean isAdmin;
    private String password;
    private String email;
    
    
    public User(String fName,String lName, int ID, boolean isAdmin, String password,
                   String email)
    {
        fName = firstName;
        lName = surname;
        this.ID = ID;
        this.isAdmin = isAdmin;
        this.password= password;
        this.email = email;
                
                
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
    
}
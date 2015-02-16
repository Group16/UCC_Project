public class Person
{
    private int p_id;

    private String firstname;
    private String surname;

    private String email;

    private String password;

    public Person( int p_id, String firstname, String surname, String email, String password )
    {
        this.p_id = p_id;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public int getP_id()
    {
        return p_id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname( String firstname )
    {
        this.firstname = firstname;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname( String surname )
    {
        this.surname = surname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }
}

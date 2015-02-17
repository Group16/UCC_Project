package Classes;

import java.util.Date;

public class Meeting
{
    private int m_id;

    private Date time;
    private Date date;

    private String location;
    private String description;

    private String type;

    private String recur_type;
    private Date resur_end;

    public Meeting( String m_id, String time, String date, String location, String description, String type, String recur_type, String resur_end )
    {
        try
        {
            this.m_id = Integer.parseInt(m_id);
            this.time = new Date();
            this.date = new Date();
            this.resur_end = new Date();
        }
        catch ( Exception e )
        {
            
        }
        
        this.location = location;
        this.description = description;
        this.type = type;
        this.recur_type = recur_type;
    }

    public int getM_id()
    {
        return m_id;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime( Date time )
    {
        this.time = time;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation( String location )
    {
        this.location = location;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public String getRecur_type()
    {
        return recur_type;
    }

    public void setRecur_type( String recur_type )
    {
        this.recur_type = recur_type;
    }

    public Date getResur_end()
    {
        return resur_end;
    }

    public void setResur_end( Date resur_end )
    {
        this.resur_end = resur_end;
    }
}

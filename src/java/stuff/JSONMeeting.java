/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuff;

/**
 *
 * @author daniel
 */
public class JSONMeeting
{
    private String m_id;
    private String confirmed;
    private String time;
    private String startDate;
    private String location;
    private String recurring;
    private String type;
    private String description;

    public JSONMeeting( String m_id, String confirmed, String time, String startDate, String location, String recurring, String type, String description )
    {
        this.m_id = m_id;
        this.confirmed = confirmed;
        this.time = time;
        this.startDate = startDate;
        this.location = location;
        this.recurring = recurring;
        this.type = type;
        this.description = description;
    }

    public String getM_id()
    {
        return m_id;
    }

    public String getConfirmed()
    {
        return confirmed;
    }

    public String getTime()
    {
        return time;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public String getLocation()
    {
        return location;
    }

    public String getRecurring()
    {
        return recurring;
    }

    public String getType()
    {
        return type;
    }

    public String getDescription()
    {
        return description;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

/**
 *
 * @author daniel
 */
public class JSONMeeting
{
    private final String m_id;
    private final String confirmed;
    private final String time;
    private final String startDate;
    private final String location;
    private final String recurring;
    private final String type;
    private final String description;

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

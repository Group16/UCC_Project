import java.util.Date;

public class Notification
{
    private int n_id;
    private int m_id;

    private boolean isSeen;

    private String type;
    private String content;

    private Date date;

    public Notification( int n_id, int m_id, boolean isSeen, String type, String content, Date date )
    {
        this.n_id = n_id;
        this.m_id = m_id;
        this.isSeen = isSeen;
        this.type = type;
        this.content = content;
        this.date = date;
    }

    public int getN_id()
    {
        return n_id;
    }

    public int getM_id()
    {
        return m_id;
    }

    public void setM_id( int m_id )
    {
        this.m_id = m_id;
    }

    public boolean isSeen()
    {
        return isSeen;
    }

    public void setSeen( boolean isSeen )
    {
        this.isSeen = isSeen;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent( String content )
    {
        this.content = content;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }
}

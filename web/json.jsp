<%-- 
    Document   : json
    Created on : 02-Mar-2015, 14:01:50
    Author     : mm37
--%>

<%@page import="stuff.JSONMeeting"%>
<%@page import="database.DbClass"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="control.MeetingChecker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
                JSONArray objArray = new JSONArray();
                Connection connectionObject = DriverManager.getConnection("jdbc:mysql://"+DbClass.getHost()+"/" + DbClass.getDatabase(), DbClass.getUser(), DbClass.getPassword());
                database.DbClass db = new database.DbClass();
                
                Date date = new Date();
                String past;
                String future;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int month = cal.get(Calendar.MONTH);
                
                //ADD AN IF SOME TIME
                
                cal.set(Calendar.MONTH, month-2);
                past = dateFormat.format(cal.getTime());
                cal.set(Calendar.MONTH, month+2);
                future = dateFormat.format(cal.getTime());
                
                try {
                        ArrayList meetings = new ArrayList();
                    
                        Statement statementObject = connectionObject.createStatement();
                        ResultSet statementResult = statementObject.executeQuery("SELECT * FROM meetings AS m JOIN people_in_meetings AS pm ON m.m_id = pm.m_id WHERE pm.p_id ='" + session.getAttribute("id") + "' AND m.date BETWEEN '" + past + "' AND '" + future + "'");
                        
                        while( statementResult.next() )
                        {
                            JSONMeeting meeting = new JSONMeeting( statementResult.getString(1), statementResult.getString(2), statementResult.getString(3), statementResult.getString(4), statementResult.getString(5), statementResult.getString(6), statementResult.getString(7), statementResult.getString(8) );
                            meetings.add(meeting);
                        }
                        
                        ResultSet statementResult2 = statementObject.executeQuery("SELECT * FROM meetings AS m JOIN  people_in_modules AS pm JOIN modules_in_meetings AS mm ON pm.mod_id = mm.mod_id AND m.m_id = mm.m_id WHERE pm.p_id = '" + session.getAttribute("id") + "'");
                        
                        while( statementResult2.next() )
                        {
                            JSONMeeting lecture = new JSONMeeting( statementResult2.getString(1), statementResult2.getString(2), statementResult2.getString(3), statementResult2.getString(4), statementResult2.getString(5), statementResult2.getString(6), statementResult2.getString(7), statementResult2.getString(8) );
                            meetings.add(lecture);
                        }
                        
                        for( Object object : meetings )
                        {   
                            JSONMeeting m = (JSONMeeting) object;
                            
                            if ( m.getRecurring().equals("weekly") || m.getRecurring().equals("daily") || m.getRecurring().equals("fortnight") || m.getRecurring().equals("monthly") )
                            {
                                Date recurDate = dateFormat.parse( m.getStartDate() );

                                String newDate;

                                Calendar recurCal = Calendar.getInstance();
                                recurCal.setTime(recurDate);

                                for ( int i=0 ; i < 12 ; i++ )
                                {   
                                    newDate = dateFormat.format(recurCal.getTime());

                                    JSONObject obj = new JSONObject();
                                    obj.put("m_id", m.getM_id());                        
                                    obj.put("start", newDate + "T" + m.getTime() );
                                    obj.put("location", m.getLocation());
                                    obj.put("recur",m.getRecurring());
                                    obj.put("type", m.getType());
                                    obj.put("title", m.getDescription());
                                    if(m.getConfirmed().equals("0"))
                                    {
                                        obj.put("color", "pink");
                                    }
                                    if(m.getType().equals("lecture"))
                                    {
                                        obj.put("color", "purple");
                                    }

                                    if(m.getType().equals("tutorial"))
                                    {
                                        obj.put("color", "lime");
                                    }
                                    objArray.add(obj);
                                    
                                    // This needs to be after the object is created for reasons.
                                    if ( m.getRecurring().equals("weekly") )
                                    {
                                        recurCal.add(Calendar.WEEK_OF_YEAR, 1);
                                    }
                                    else if ( m.getRecurring().equals("daily") )
                                    {
                                        recurCal.add(Calendar.DAY_OF_YEAR, 1);
                                    }
                                    else if ( m.getRecurring().equals("fortnight") )
                                    {
                                        recurCal.add(Calendar.WEEK_OF_YEAR, 2);
                                    }
                                    else if ( m.getRecurring().equals("monthly") )
                                    {
                                        recurCal.add(Calendar.MONTH, 1);
                                    }
                                }
                            }
                            else
                            {
                                JSONObject obj = new JSONObject();
                                obj.put("m_id", m.getM_id());                        
                                obj.put("start", m.getStartDate() + "T" + m.getTime() );
                                obj.put("location", m.getLocation());
                                obj.put("recur",m.getRecurring());
                                obj.put("type", m.getType());
                                obj.put("title", m.getDescription());
                                
                                if(m.getType().equals("lecture"))
                                {
                                    obj.put("color", "purple");
                                }
                                if(m.getType().equals("tutorial"))
                                {
                                    obj.put("color", "lime");
                                }
                                
                                objArray.add(obj);
                            }
                        }
                        out.print(objArray);
                }
                catch(Exception E) {}  
        %>

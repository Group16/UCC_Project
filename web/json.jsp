<%-- 
    Document   : json
    Created on : 02-Mar-2015, 14:01:50
    Author     : mm37
--%>

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
                 //JSONArray meetings = new JSONArray();
                 
                 ArrayList<MeetingChecker> meetings = new ArrayList<MeetingChecker>();
                 
                        
                 JSONArray objArray = new JSONArray();
                 Statement statementObject;
                 Connection connectionObject;
                 connectionObject = DriverManager.getConnection("jdbc:mysql://"+"cs1.ucc.ie"+"/" + "2016_mm37", "mm37", "uohongah");
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
                past = dateFormat.format(cal.getTime().toString());
                cal.set(Calendar.MONTH, month+2);
                future = dateFormat.format(cal.getTime().toString());
                
                try {
                    statementObject = connectionObject.createStatement();
                    ResultSet statementResult = statementObject.executeQuery("SELECT * FROM meetings AS m JOIN people_in_meetings pm WHERE pm.p_id = '" + session.getAttribute("id") + "' AND m.date BETWEEN '" + past + "' AND '" + future + "'");
                    out.print("SELECT * FROM meetings AS m JOIN people_in_meetings pm WHERE pm.p_id = '" + session.getAttribute("id") + "' AND m.date BETWEEN '" + past + "' AND '" + future + "'");
                    while(statementResult.next()){
                        
                        String m_id  = statementResult.getString(1);
                        String time  = statementResult.getString(3);
                        String startDate  = statementResult.getString(4);
                        String location = statementResult.getString(5);
                        String recurring  = statementResult.getString(6);
                        String endDate  = statementResult.getString(7);
                        String type  = statementResult.getString(8);
                        String description  = statementResult.getString(9);
                       
                        //MeetingChecker eeting = new MeetingChecker(m_id, time, startDate, location, recurring, endDate, type, description);
                        
                        //meetings.add(meeting);
                        
                        
                        JSONObject obj = new JSONObject();
                        obj.put("m_id", m_id);                        
                        obj.put("start", startDate + " " + time );
                        obj.put("location", location);
                        obj.put("recur",recurring);
                        obj.put("recur_end", endDate);
                        obj.put("type", type);
                        obj.put("title", description);
                        
                        //JSONValue.toJSONString( obj );
                        
                        objArray.add(obj);
                        
                        
                   }
                   out.print(objArray);
                    
                 }catch(Exception E){
                     
                 }   
        %>

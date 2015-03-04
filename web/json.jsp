<%-- 
    Document   : json
    Created on : 02-Mar-2015, 14:01:50
    Author     : mm37
--%>

<%@page import="json.MakeJSONArray"%>
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
    JSONArray objArray = new MakeJSONArray().make( session.getAttribute("id").toString() );
    
    out.print(objArray);
%>

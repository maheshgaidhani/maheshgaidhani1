package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Records extends HttpServlet {
	
private static  final String GET_USER_DETAILS="select firstname,lastname,email,phone,location from user";
//private static  final String SELECT_USER_DETAILS="SELECT firstname,lastname,email,phone,loc FROM USER";

        private Connection con;
		Statement cs=null;
		ResultSet rs=null;
	    int result=0;
		public void init(){
			try{
	      //Access ServletContext obj  to read global init params/context params
		     ServletContext sc=getServletContext();
			 String driver=sc.getInitParameter("driver");
			 String url=sc.getInitParameter("url");
			 String user=sc.getInitParameter("dbuser");
			 String pwd=sc.getInitParameter("dbpwd");

	      //register jdbc driver
		  Class.forName(driver);
		  //Establish the connection
		  con=DriverManager.getConnection(url,user,pwd);
		  //create PareparedStatement obj
		cs=con.createStatement();
		 //st=con.createStatement();
		 
		
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}//init()

		public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
		{
			//General settings 
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			
			
			try{
					
             rs = cs.executeQuery(GET_USER_DETAILS);
            pw.println("<body bgcolor=lightblue>");
            pw.println("<table bgcolor='#ccccff'align='center'border=1 >");
             pw.println("<tr><th>FirstName</th><th>LastName</th><th>Email</th><th>Phone</th><th>Location</th><th>-------</th><tr>");
             while (rs.next()) { 				
            	 String n = rs.getString(1);
                 String nm = rs.getString(2);
                 String e = rs.getString(3);
                 long p=rs.getLong(4);
                 String l = rs.getString(5);
                  pw.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + e + "</td><td>" + p + "</td><td>" + l + "</td><td><a href='editfrm?fname1="+n+"&lname1="+nm+"&email1="+e+"&phone1="+p+"&loc1="+l+"'>Edit</a><a href='deleteurl?name="+n+"'>Delete</a></td></tr>"); 
             }
             pw.println("</table>");
           
			// add hyperlink
			pw.println("<br><center><a href='Form1.html' >Home </a></center><br><br><br><br>");
			
			res.setIntHeader("Refresh", 5);
			pw.close();
			}//try
			catch(Exception e){
				//ServletContext sc=getServletContext();
				//RequestDispatcher rd=sc.getRequestDispatcher("/eurl");
			
			//	rd.forward(req,res);
			
			}
		}//doGet(-,-)

		public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
		{
	     doGet(req,res);
		}

		public void destroy(){
			try{
				if(cs!=null)
					cs.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}//destroy()

	}//class
	//>javac  -d  . DBSrv.java



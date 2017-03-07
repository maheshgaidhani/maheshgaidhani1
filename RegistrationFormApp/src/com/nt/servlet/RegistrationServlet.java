package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationServlet extends HttpServlet {
	
private static  final String INSERT_USER_DETAILS="insert into user values(?,?,?,?,?,?,?)";

        private Connection con;
		PreparedStatement cs=null;
		//Statement st=null;
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
		 cs=con.prepareStatement(INSERT_USER_DETAILS);
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
			pw.println("<a href='regurl1'><p style='text-align:center'>View All Records</a>");

			
			try{
			//read form data
			String username=req.getParameter("uname");
			String passw=req.getParameter("pass");
			String firstname=req.getParameter("fname");
			String lastname=req.getParameter("lname");
			String email=req.getParameter("email");
			String phone=req.getParameter("phone");
			String loc=req.getParameter("location");
			//set value to Query param
			cs.setString(1,username);
			cs.setString(2,passw);
			cs.setString(3,firstname);
			cs.setString(4,lastname);
			cs.setString(5,email);
			cs.setString(6,phone);
			cs.setString(7,loc);

	        //execute the Query
			result=cs.executeUpdate();
		    if(result==0)
			{
				pw.println("<h2>Insertion Failed</h2>");
			}
			else
			{
				pw.println("<body bgcolor='lightblue'>");
				pw.println("<h2><center>Record successfully inserted</center></h2>");
				pw.println("<a href='Form1.html'>Home</a>");
				ServletContext sc=getServletContext();
				RequestDispatcher rd=sc.getRequestDispatcher("/regurl1");
				
			}
		  
			pw.close();
			}//try
			catch(Exception e){
				
			}
		}

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
	



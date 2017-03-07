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


public class DeleteServlet extends HttpServlet {
	
private static  final String DELETE_USER_DETAILS="delete from user where firstname=?";

        private Connection con;
		PreparedStatement cs=null;
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
		 cs=con.prepareStatement(DELETE_USER_DETAILS);
			
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
				String name1=req.getParameter("name");
				cs.setString(1,name1);
			
			//execute the Query
			result=cs.executeUpdate();
		    if(result==0)
			{
				pw.println("Deletion Failed");
			}
			else
			{  				res.sendRedirect("http://localhost:8181/RegistrationFormApp/regurl1");

				pw.println(" Record deleted Successfully");
			}
		
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
	



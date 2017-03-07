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


public class EditServlet extends HttpServlet {
	
private static  final String EDIT_USER_DETAILS="update user set email=?,phone=?,location=? WHERE firstname=?";

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
				String email1=req.getParameter("email");
				String phone1=req.getParameter("phone");
				String location1=req.getParameter("location");
				String firstname1=req.getParameter("fn");
			
			//execute the Query
			 if(email1!="" && location1 !="" && phone1!=""){
			 result=cs.executeUpdate("update user set email="+"'"+email1+"'"+",phone="+"'"+phone1+"'"+",location="+"'"+location1+"'"+"where firstname="+"'"+firstname1+"'");
			 }
			 else
				 {
				 pw.println("<center>Enter Valid Details<br></center>");
				 
				 }if(result==0)
			 {  pw.println("<body bgcolor='lightblue'>");
				pw.println("<center>Updation Failed</center><br>");
				pw.println("<center><a href='regurl1'>GO BACK</a></center>");
			}
			else
			{
				//pw.println(" Record Updated Successfully");
				res.sendRedirect("http://localhost:8181/RegistrationFormApp/regurl1");
				//ServletContext sc=getServletContext();
				//RequestDispatcher rd=sc.getRequestDispatcher("/regurl1");
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
	



package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditForm extends HttpServlet {
	


		public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
		{
			//General settings 
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			
			
			try{
			//read form data
				String fname2=req.getParameter("fname1");
			    String lname2=req.getParameter("lname1");
			    
			    pw.println("<body bgcolor='lightblue'>");
				pw.println("<form align='center'action='editurl?fn="+fname2+"' method='post'>");
				pw.println("<b>First Name:</b><input type='text' id='nm' name='fname' value="+fname2+" disabled><br>");
				pw.println("<b>Last Name:</b><input type='text' name='lname' value="+lname2+" disabled><br>");
				pw.println("<b>Email:</b><input type='text' name='email'><br>");
				pw.println("<b>Phone:</b><input type='text' name='phone'><br>");
				pw.println("<b>Location:</b><input type='text' name='location'><br>");
			
				pw.println("<input type='submit' value='UPDATE'>");
				pw.println("<input type='reset' value='RESET'><br><br>");
				pw.println("<a href='regurl1'>GO BACK</a>");
                pw.println("</form>");
			
			
              
			
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
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}//destroy()

	}//class




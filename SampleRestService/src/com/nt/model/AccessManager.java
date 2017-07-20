package com.nt.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
 
import com.nt.dao.Access;
import com.nt.dao.Database;
import com.nt.dto.Course;
 
public class AccessManager
{
public ArrayList<Course> getCourses() throws Exception
{
ArrayList<Course> courseList = new ArrayList<Course>();
Database db = new Database();
Connection con = db.getConnection();
Access access = new Access();
courseList = access.getCourses(con);
return courseList;
}
}

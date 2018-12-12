

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


@ManagedBean
public class CourseDAO {
	private DataSource mysqlDS;
	private ArrayList<Course> courses;
	
	public CourseDAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/studentdb";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	public ArrayList<Course> loadCourses() throws SQLException{
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from course";
		ResultSet rs = myStmt.executeQuery(query);
		
		 this.courses = new ArrayList<Course>();
		
		while (rs.next()) {
			String cID = rs.getString("cID");
			String cName = rs.getString("cName");
			int duration = rs.getInt("duration");
			Course c = new Course(cID, cName, duration);
			courses.add(c);
		}
		
		rs.close();
		myStmt.close();
		return courses;
	}

	public DataSource getMysqlDS() {
		return mysqlDS;
	}

	public void setMysqlDS(DataSource mysqlDS) {
		this.mysqlDS = mysqlDS;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	public void insertCourse(Course c) throws SQLException
	{ 
		String cID = c.getcID();
		String cName = c.getcName();
		int duration = c.getDuration();
		Connection conn = mysqlDS.getConnection();
		//sql command that inserts course where course id, name and duration is entered
		PreparedStatement myStmt = conn.prepareStatement("insert into course(cID, cName, duration) values(?, ?, ?)");
		myStmt.setString(1, cID);
		myStmt.setString(2, cName);
		myStmt.setInt(3, duration);
	
		myStmt.executeUpdate();//updates statement
		myStmt.close();//closes statement
		conn.close();//closes connection
	}

	public void deleteCourse(Course c) {
		Connection conn;
		try {
			conn = mysqlDS.getConnection();
			// sql command that deletes course where course id is entered
			PreparedStatement myStmt = conn.prepareStatement("delete from course where cid = ?");
			myStmt.setString(1, c.getcID());
			myStmt.executeUpdate();
			myStmt.close();// closes statement
			conn.close();// closes connection
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch
	}
	
}


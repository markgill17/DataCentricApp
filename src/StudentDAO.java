

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
public class StudentDAO {
	private DataSource mysqlDS;
	ArrayList<Student> students;
	
	public StudentDAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/studentdb";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	public ArrayList<Student> loadStudents() throws SQLException{
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from student";
		ResultSet rs = myStmt.executeQuery(query);
		
		this.students = new ArrayList<Student>();
		
		while (rs.next()) {
			String sID = rs.getString("sID");
			String cID = rs.getString("cID");
			String name = rs.getString("name");
			String address = rs.getString("address");
			
			Student s = new Student(sID, cID, name, address);
			students.add(s);
		}
		rs.close();
		myStmt.close();
		return students;
	}

	public DataSource getMysqlDS() {
		return mysqlDS;
	}

	public void setMysqlDS(DataSource mysqlDS) {
		this.mysqlDS = mysqlDS;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	public void insertStudent(Student s) throws SQLException
	{ 
		String sID = s.getsID();
		String cID = s.getcID();
		String name = s.getName();
		String address = s.getAddress();
		Connection conn = mysqlDS.getConnection();
		//sql command that inserts course where course id, name and duration is entered
		PreparedStatement myStmt = conn.prepareStatement("insert into student(sId, cID, name, address) values(?, ?, ?, ?)");
		myStmt.setString(1, sID);
		myStmt.setString(2, cID);
		myStmt.setString(3, name);
		myStmt.setString(4, address);
		
		myStmt.executeUpdate();//updates statement
		myStmt.close();//closes statement
		conn.close();//closes connection
	}
	
	public void deleteStudent(Student s) {
		Connection conn;
		try {
			conn = mysqlDS.getConnection();
			// sql command that deletes course where course id is entered
			PreparedStatement myStmt = conn.prepareStatement("delete from student where sid = ?");
			myStmt.setString(1, s.getsID());
			myStmt.executeUpdate();
			myStmt.close();// closes statement
			conn.close();// closes connection
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch
	}
}

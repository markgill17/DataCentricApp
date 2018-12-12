

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class StudentController {

	StudentDAO dao;
	ArrayList<Student> students;
	
	

	public StudentController() throws Exception {
		dao = new StudentDAO();
		students = new ArrayList<Student>();

	}

	public void loadStudents() throws SQLException {
		students = dao.loadStudents(); 
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	public String addStudent(Student s) {
		System.out.println(s.getcID() + s.getName() + s.getAddress() + s.getsID());
		try {
			dao.insertStudent(s);
			
		}
		catch(SQLException e){
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return "list_students.xhtml";
	}
	
	public String deleteStudent(Student s)
	{
		System.out.println("in deleteStudent");
		dao.deleteStudent(s);
		
		return "list_students.xhtml";
	}

}
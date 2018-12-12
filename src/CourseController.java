

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CourseController {

	CourseDAO dao;
	ArrayList<Course> courses;

	public CourseController() throws Exception {
		dao = new CourseDAO();
		courses = new ArrayList<Course>();

	}

	public void loadCourses() throws SQLException{
		courses = dao.loadCourses();
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	public String addCourse(Course c) {
		System.out.println("in addCourse");
		try {
			dao.insertCourse(c);
		}
		catch(SQLException e){
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return "list_courses.xhtml";
	}
	
	public String deleteCourse(Course c)
	{
		System.out.println("in deleteCourse");
		dao.deleteCourse(c);
		
		return "list_courses.xhtml";
	}
	

}

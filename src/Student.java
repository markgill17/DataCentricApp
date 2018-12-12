

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Student {

	private String sID;
	private String cID;
	private String name;
	private String address;
	
	public Student(){ //Default Constructor
		
	}

	public Student(String sID, String cID, String name, String address) {
		super();
		this.sID = sID;
		this.cID = cID;
		this.name = name;
		this.address = address;
	}

	public String getsID() {
		return sID;
	}

	public void setsID(String sID) {
		this.sID = sID;
	}

	public String getcID() {
		return cID;
	}

	public void setcID(String cID) {
		this.cID = cID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

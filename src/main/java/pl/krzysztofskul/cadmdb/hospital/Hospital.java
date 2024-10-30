package pl.krzysztofskul.cadmdb.hospital;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.hospital.department.Department;

@Entity
public class Hospital extends HealthcareFacility {

	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<Department> departmentList = new ArrayList<Department>();
	
	/**
	 * 
	 */
	public Hospital() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param address
	 * @param contactdetails
	 */
	public Hospital(String name, String address, String contactdetails) {
		super(name, address, contactdetails);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public Hospital(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		for (Department department : departmentList) {
			department.setHospital(this);
		}
		this.departmentList = departmentList;
	}
	
}

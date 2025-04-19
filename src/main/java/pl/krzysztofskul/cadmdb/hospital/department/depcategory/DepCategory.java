package pl.krzysztofskul.cadmdb.hospital.department.depcategory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.hospital.department.Department;

/**
 * STARDIZED NAME OF THE DEPARTMENT
 */
@Entity
public class DepCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String depcategorynamePL;
	private String depcategorynameEN;

	@OneToMany(mappedBy = "depcategory")
	private List<Department> departmentList = new ArrayList<Department>();
	
	/**
	 * 
	 */
	public DepCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param depcategorynamePL
	 * @param depcategorynameEN
	 */
	public DepCategory(String depcategorynamePL, String depcategorynameEN) {
		super();
		this.depcategorynamePL = depcategorynamePL;
		this.depcategorynameEN = depcategorynameEN;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepcategorynamePL() {
		return depcategorynamePL;
	}

	public void setDepcategorynamePL(String depcategorynamePL) {
		this.depcategorynamePL = depcategorynamePL;
	}

	public String getDepcategorynameEN() {
		return depcategorynameEN;
	}

	public void setDepcategorynameEN(String depcategorynameEN) {
		this.depcategorynameEN = depcategorynameEN;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	
		
}

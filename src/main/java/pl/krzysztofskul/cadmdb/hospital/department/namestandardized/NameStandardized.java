package pl.krzysztofskul.cadmdb.hospital.department.namestandardized;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import pl.krzysztofskul.cadmdb.hospital.department.Department;

@Entity(name="Department_NameStandardized")
public class NameStandardized {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nameStandardizedPl;
	private String nameStandardizedEn;

	@OneToMany(mappedBy = "nameStandardized")
	private List<Department> departmentList = new ArrayList<Department>();
	
	/**
	 * 
	 */
	public NameStandardized() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nameStandardizedPl
	 * @param nameStandardizedEn
	 */
	public NameStandardized(String nameStandardizedPl, String nameStandardizedEn) {
		super();
		this.nameStandardizedPl = nameStandardizedPl;
		this.nameStandardizedEn = nameStandardizedEn;
	}

	/**
	 * Getter
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter
	 * @return the nameStandardizedPl
	 */
	public String getNameStandardizedPl() {
		return nameStandardizedPl;
	}

	/**
	 * Setter
	 * @param nameStandardizedPl the nameStandardizedPl to set
	 */
	public void setNameStandardizedPl(String nameStandardizedPl) {
		this.nameStandardizedPl = nameStandardizedPl;
	}

	/**
	 * Getter
	 * @return the nameStandardizedEn
	 */
	public String getNameStandardizedEn() {
		return nameStandardizedEn;
	}

	/**
	 * Setter
	 * @param nameStandardizedEn the nameStandardizedEn to set
	 */
	public void setNameStandardizedEn(String nameStandardizedEn) {
		this.nameStandardizedEn = nameStandardizedEn;
	}

	/**
	 * Getter
	 * @return the departmentList
	 */
	public List<Department> getDepartmentList() {
		return departmentList;
	}

	/**
	 * Setter
	 * @param departmentList the departmentList to set
	 */
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
	
}

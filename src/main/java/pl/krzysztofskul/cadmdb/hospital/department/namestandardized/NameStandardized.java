package pl.krzysztofskul.cadmdb.hospital.department.namestandardized;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.department.Department;

@Entity(name="Department_NameStandardized")
public class NameStandardized {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*
	 * Hospital-specific standardized name.
	 * <p>If a user needs a standardized department name that does not already exist
	 * in the global dictionary, the name is created and scoped to that hospital.
	 * When {@code hospital} is {@code null}, the name is global.</p>
	 */
	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital = null;
	
	private String nameStandardizedPl;
	private String nameStandardizedEn;

	@OneToMany(mappedBy = "nameStandardized")
	private List<Department> departmentList = new ArrayList<Department>();
	
	/**
	 * Constructor
	 */
	public NameStandardized() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor
	 * @param hospital
	 */
	public NameStandardized(Hospital hospital) {
		super();
		this.hospital = hospital;
	}

	/**
	 * Constructor
	 * @param nameStandardizedPl
	 * @param nameStandardizedEn
	 */
	public NameStandardized(String nameStandardizedPl, String nameStandardizedEn) {
		super();
		this.nameStandardizedPl = nameStandardizedPl;
		this.nameStandardizedEn = nameStandardizedEn;
	}

	/**
	 * Constructor
	 * @param hospital
	 * @param nameStandardizedPl
	 * @param nameStandardizedEn
	 */
	public NameStandardized(Hospital hospital, String nameStandardizedPl, String nameStandardizedEn) {
		super();
		this.hospital = hospital;
		this.nameStandardizedPl = nameStandardizedPl;
		this.nameStandardizedEn = nameStandardizedEn;
	}

	/**
	 * Getter
	 * @return the hospital
	 */
	public Hospital getHospital() {
		return hospital;
	}

	/**
	 * Setter
	 * @param hospital the hospital to set
	 */
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
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

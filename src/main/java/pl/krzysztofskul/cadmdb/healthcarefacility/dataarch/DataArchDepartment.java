package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.hospital.department.Department;

@Entity
public class DataArchDepartment extends DataArch{

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "id")
    private Department department;

	/**
	 * Constructor
	 */
	public DataArchDepartment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param department
	 */
	public DataArchDepartment(Department department) {
		super();
		this.department = department;
	}



	/**
	 * Getter
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Setter
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
    
}

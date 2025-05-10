package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.hospital.department.Department;

@Entity
public class DataArchDepartment extends DataArch{

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

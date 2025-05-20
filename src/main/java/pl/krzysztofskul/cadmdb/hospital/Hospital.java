package pl.krzysztofskul.cadmdb.hospital;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.DataArchDepartment;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.DataArchHospital;
import pl.krzysztofskul.cadmdb.hospital.department.Department;

@Entity
public class Hospital extends HealthcareFacility {

	@OneToMany(mappedBy = "hospital", cascade = {CascadeType.ALL})
	private List<Department> departmentList = new ArrayList<Department>();

    @OneToOne(
            mappedBy    = "hospital",
            cascade     = CascadeType.ALL,
            orphanRemoval = true,
            fetch       = FetchType.EAGER
        )
    private DataArchHospital dataArchHospital;
	
    @PrePersist
    private void ensureDataArchHospital() {
        if (this.dataArchHospital == null) {
            DataArchHospital dArch = new DataArchHospital();
            dArch.setHospital(this);
            this.dataArchHospital = dArch;
        }
    }
    
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
	public Hospital(String name, Address address, String contactdetails) {
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
	
	/**
	 * Getter
	 * @return the dataArchHospital
	 */
	public DataArchHospital getDataArchHospital() {
		return dataArchHospital;
	}

	/**
	 * Setter
	 * @param dataArchHospital the dataArchHospital to set
	 */
	public void setDataArchHospital(DataArchHospital dataArchHospital) {
		this.dataArchHospital = dataArchHospital;
		dataArchHospital.setHospital(this);
	}
	
	public void addDepartment(Department department) {
		this.departmentList.add(department);
		department.setHospital(this);
		
	}
	
	public void removeDepartment(Department department) {
		this.departmentList.remove(department);
		department.setHospital(null);
	}
}

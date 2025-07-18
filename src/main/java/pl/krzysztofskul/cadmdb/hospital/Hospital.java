package pl.krzysztofskul.cadmdb.hospital;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.DataArchDepartment;
import pl.krzysztofskul.cadmdb.healthcarefacility.dataarch.DataArchHospital;
import pl.krzysztofskul.cadmdb.healthcarefacility.simulation.Simulation;
import pl.krzysztofskul.cadmdb.hospital.department.Department;

@Entity
public class Hospital extends HealthcareFacility {

	@Valid
	@NotBlank(message = "Name can't be blank!")
	private String name;
	
	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<Department> departmentList = new ArrayList<Department>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dataarchhospital_id")
    private DataArchHospital dataArchHospital;
	
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "simulation_id")
    private Simulation simulation;
	
    @PrePersist
    private void ensureDataArchHospital() {
        if (this.dataArchHospital == null) {
        	this.dataArchHospital = new DataArchHospital(this);
        }
        if (this.simulation == null) {
        	this.simulation = new Simulation(this);
        }
    }
    
	/**
	 * Constructor
	 */
	public Hospital() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		this.departmentList.clear();
		for (Department department : departmentList) {
			this.addDepartment(department);
		}
	}

	/**
	 * Getter
	 * @return the dataArchHospital
	 */
	public DataArchHospital getDataArchHospital() {
		return dataArchHospital;
	}
	
	/**
	 * Getter
	 * @return the simulation
	 */
	public Simulation getSimulation() {
		return simulation;
	}

	/**
	 * Setter
	 * @param simulation the simulation to set
	 */
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	/**
	 * Setter
	 * @param dataArchHospital the dataArchHospital to set
	 */
	public void setDataArchHospital(DataArchHospital dataArchHospital) {
		this.dataArchHospital = dataArchHospital;
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

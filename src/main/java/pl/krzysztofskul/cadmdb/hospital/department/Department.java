package pl.krzysztofskul.cadmdb.hospital.department;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.hospital.Hospital;

@Entity
public class Department extends HealthcareFacility {

	@ManyToOne
	private Hospital hospital;
	
	/**
	 * 
	 */
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param address
	 * @param contactdetails
	 */
	public Department(String name, Address address, String contactdetails) {
		super(name, address, contactdetails);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public Department(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	
}

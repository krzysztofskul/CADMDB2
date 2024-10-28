package pl.krzysztofskul.cadmdb2.healthcarefacility;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class HealthcareFacility {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
	private String contactdetails;
	
	/**
	 * 
	 */
	public HealthcareFacility() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public HealthcareFacility(String name) {
		super();
		this.name = name;
	}

	/**
	 * @param name
	 * @param address
	 * @param contactdetails
	 */
	public HealthcareFacility(String name, String address, String contactdetails) {
		super();
		this.name = name;
		this.address = address;
		this.contactdetails = contactdetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getContactdetails() {
		return contactdetails;
	}

	public void setContactdetails(String contactdetails) {
		this.contactdetails = contactdetails;
	}
	
}

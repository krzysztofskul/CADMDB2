package pl.krzysztofskul.cadmdb.company;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.address.Address;

@MappedSuperclass
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address = new Address();
	
	private String contactDetails;

	/**
	 * Constructor
	 */
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param name
	 * @param address
	 * @param contactDetails
	 */
	public Company(String name, Address address, String contactDetails) {
		super();
		this.name = name;
		this.address = address;
		this.contactDetails = contactDetails;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Setter
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Getter
	 * @return the contactDetails
	 */
	public String getContactDetails() {
		return contactDetails;
	}

	/**
	 * Setter
	 * @param contactDetails the contactDetails to set
	 */
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	
}

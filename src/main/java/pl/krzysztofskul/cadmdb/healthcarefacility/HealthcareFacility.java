package pl.krzysztofskul.cadmdb.healthcarefacility;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.healthcarefacility.datafinancial.DataFinancial;

@MappedSuperclass
public class HealthcareFacility {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String namePL;
	private String nameEN;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "hfdatafinancial")
	private DataFinancial dataFinancial = new DataFinancial();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address = new Address();
	
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
	public HealthcareFacility(String name, Address address, String contactdetails) {
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

	public String getNamePL() {
		return namePL;
	}

	public void setNamePL(String namePL) {
		this.namePL = namePL;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public DataFinancial getDataFinancial() {
		return dataFinancial;
	}

	public void setDataFinancial(DataFinancial dataFinancial) {
		this.dataFinancial = dataFinancial;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContactdetails() {
		return contactdetails;
	}

	public void setContactdetails(String contactdetails) {
		this.contactdetails = contactdetails;
	}
	
}

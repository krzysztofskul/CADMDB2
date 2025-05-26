package pl.krzysztofskul.cadmdb.healthcarefacility;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.healthcarefacility.datafinancial.DataFinancial;
import pl.krzysztofskul.cadmdb.healthcarefacility.datatech.DataTech;

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

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "hfdatatech")
	private DataTech dataTech = new DataTech();
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;
	
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
	 * @return the namePL
	 */
	public String getNamePL() {
		return namePL;
	}

	/**
	 * Setter
	 * @param namePL the namePL to set
	 */
	public void setNamePL(String namePL) {
		this.namePL = namePL;
	}

	/**
	 * Getter
	 * @return the nameEN
	 */
	public String getNameEN() {
		return nameEN;
	}

	/**
	 * Setter
	 * @param nameEN the nameEN to set
	 */
	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	/**
	 * Getter
	 * @return the dataFinancial
	 */
	public DataFinancial getDataFinancial() {
		return dataFinancial;
	}

	/**
	 * Setter
	 * @param dataFinancial the dataFinancial to set
	 */
	public void setDataFinancial(DataFinancial dataFinancial) {
		this.dataFinancial = dataFinancial;
	}

	/**
	 * Getter
	 * @return the dataTech
	 */
	public DataTech getDataTech() {
		return dataTech;
	}

	/**
	 * Setter
	 * @param dataTech the dataTech to set
	 */
	public void setDataTech(DataTech dataTech) {
		this.dataTech = dataTech;
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
	 * @return the contactdetails
	 */
	public String getContactdetails() {
		return contactdetails;
	}

	/**
	 * Setter
	 * @param contactdetails the contactdetails to set
	 */
	public void setContactdetails(String contactdetails) {
		this.contactdetails = contactdetails;
	}
	
}

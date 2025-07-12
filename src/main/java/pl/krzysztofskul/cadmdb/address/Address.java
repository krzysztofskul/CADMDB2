package pl.krzysztofskul.cadmdb.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Country can't be blank!")
	private String country;
	
	private String city;
	
	private String postalcode;
	
	private String streetname;
	
	private String streetno;
	
	private String flatno;
	
	/**
	 * 
	 */
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param country
	 * @param city
	 * @param postalcode
	 * @param streetname
	 * @param streetno
	 * @param flatno
	 */
	public Address(String country, String city, String postalcode, String streetname,
			String streetno, String flatno) {
		super();
		this.country = country;
		this.city = city;
		this.postalcode = postalcode;
		this.streetname = streetname;
		this.streetno = streetno;
		this.flatno = flatno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public String getStreetno() {
		return streetno;
	}

	public void setStreetno(String streetno) {
		this.streetno = streetno;
	}

	public String getFlatno() {
		return flatno;
	}

	public void setFlatno(String flatno) {
		this.flatno = flatno;
	}
	
}

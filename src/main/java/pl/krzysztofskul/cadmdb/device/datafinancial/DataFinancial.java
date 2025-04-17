package pl.krzysztofskul.cadmdb.device.datafinancial;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.device.Device;

@Entity
public class DataFinancial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Device device;
	
	private BigDecimal price = BigDecimal.ZERO;

	/**
	 * Constructor
	 */
	public DataFinancial() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param price
	 */
	public DataFinancial(BigDecimal price) {
		super();
		this.price = price;
	}

	/**
	 * Constructor
	 * @param device
	 * @param price
	 */
	public DataFinancial(Device device, BigDecimal price) {
		super();
		this.device = device;
		this.price = price;
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
	 * @return the device
	 */
	public Device getDevice() {
		return device;
	}

	/**
	 * Setter
	 * @param device the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}

	/**
	 * Getter
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Setter
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}

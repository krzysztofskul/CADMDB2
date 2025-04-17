package pl.krzysztofskul.cadmdb.healthcarefacility.datafinancial;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "hfdatafinancial")
@Table(name = "hfdatafinancial")
public class DataFinancial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// costs of purchase
	private BigDecimal purCostOfDevice = BigDecimal.ZERO;
	private BigDecimal purCostOfDevicePlan = BigDecimal.ZERO;
	
	// cost of maintenance
	private BigDecimal maintCostOfDevice = BigDecimal.ZERO;
	private BigDecimal maintCostOfDevicePlan = BigDecimal.ZERO;
	
	/**
	 * Constructor
	 */
	public DataFinancial() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the purCostOfDevice
	 */
	public BigDecimal getPurCostOfDevice() {
		return purCostOfDevice;
	}

	/**
	 * Setter
	 * @param purCostOfDevice the purCostOfDevice to set
	 */
	public void setPurCostOfDevice(BigDecimal purCostOfDevice) {
		this.purCostOfDevice = purCostOfDevice;
	}

	/**
	 * Getter
	 * @return the purCostOfDevicePlan
	 */
	public BigDecimal getPurCostOfDevicePlan() {
		return purCostOfDevicePlan;
	}

	/**
	 * Setter
	 * @param purCostOfDevicePlan the purCostOfDevicePlan to set
	 */
	public void setPurCostOfDevicePlan(BigDecimal purCostOfDevicePlan) {
		this.purCostOfDevicePlan = purCostOfDevicePlan;
	}

	/**
	 * Getter
	 * @return the maintCostOfDevice
	 */
	public BigDecimal getMaintCostOfDevice() {
		return maintCostOfDevice;
	}

	/**
	 * Setter
	 * @param maintCostOfDevice the maintCostOfDevice to set
	 */
	public void setMaintCostOfDevice(BigDecimal maintCostOfDevice) {
		this.maintCostOfDevice = maintCostOfDevice;
	}

	/**
	 * Getter
	 * @return the maintCostOfDevicePlan
	 */
	public BigDecimal getMaintCostOfDevicePlan() {
		return maintCostOfDevicePlan;
	}

	/**
	 * Setter
	 * @param maintCostOfDevicePlan the maintCostOfDevicePlan to set
	 */
	public void setMaintCostOfDevicePlan(BigDecimal maintCostOfDevicePlan) {
		this.maintCostOfDevicePlan = maintCostOfDevicePlan;
	}
	
	
	
}

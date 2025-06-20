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
	private BigDecimal purCostOfProduct = BigDecimal.ZERO;
	private BigDecimal purCostOfProductPlan = BigDecimal.ZERO;
	
	// cost of maintenance
	private BigDecimal maintCostOfProduct = BigDecimal.ZERO;
	private BigDecimal maintCostOfProductPlan = BigDecimal.ZERO;
	
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
	 * @return the purCostOfProduct
	 */
	public BigDecimal getPurCostOfProduct() {
		return purCostOfProduct;
	}

	/**
	 * Setter
	 * @param purCostOfProduct the purCostOfProduct to set
	 */
	public void setPurCostOfProduct(BigDecimal purCostOfProduct) {
		this.purCostOfProduct = purCostOfProduct;
	}

	/**
	 * Getter
	 * @return the purCostOfProductPlan
	 */
	public BigDecimal getPurCostOfProductPlan() {
		return purCostOfProductPlan;
	}

	/**
	 * Setter
	 * @param purCostOfProductPlan the purCostOfProductPlan to set
	 */
	public void setPurCostOfProductPlan(BigDecimal purCostOfProductPlan) {
		this.purCostOfProductPlan = purCostOfProductPlan;
	}

	/**
	 * Getter
	 * @return the maintCostOfProduct
	 */
	public BigDecimal getMaintCostOfProduct() {
		return maintCostOfProduct;
	}

	/**
	 * Setter
	 * @param maintCostOfProduct the maintCostOfProduct to set
	 */
	public void setMaintCostOfProduct(BigDecimal maintCostOfProduct) {
		this.maintCostOfProduct = maintCostOfProduct;
	}

	/**
	 * Getter
	 * @return the maintCostOfProductPlan
	 */
	public BigDecimal getMaintCostOfProductPlan() {
		return maintCostOfProductPlan;
	}

	/**
	 * Setter
	 * @param maintCostOfProductPlan the maintCostOfProductPlan to set
	 */
	public void setMaintCostOfProductPlan(BigDecimal maintCostOfProductPlan) {
		this.maintCostOfProductPlan = maintCostOfProductPlan;
	}
	
	
	
}

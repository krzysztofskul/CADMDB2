package pl.krzysztofskul.cadmdb.product.datafinancial;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import pl.krzysztofskul.cadmdb.product.Product;

@Entity
public class DataFinancial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "dataFinancial")
	private Product product;
	
	private BigDecimal price = BigDecimal.ZERO;
	
	/*
	 * Maintenance cost per year
	 */
	private BigDecimal maintenanceCost = BigDecimal.ZERO;

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
	 * @param product
	 * @param price
	 */
	public DataFinancial(Product product, BigDecimal price) {
		super();
		this.product = product;
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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Setter
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
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

	/**
	 * Getter
	 * @return the maintenanceCostPerYear
	 */
	public BigDecimal getMaintenanceCost() {
		return maintenanceCost;
	}

	/**
	 * Setter
	 * @param maintenanceCostPerYear the maintenanceCostPerYear to set
	 */
	public void setMaintenanceCost(BigDecimal maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}

}

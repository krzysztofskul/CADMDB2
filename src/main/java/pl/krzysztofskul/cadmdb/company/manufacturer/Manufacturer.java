package pl.krzysztofskul.cadmdb.company.manufacturer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import pl.krzysztofskul.cadmdb.address.Address;
import pl.krzysztofskul.cadmdb.company.Company;
import pl.krzysztofskul.cadmdb.product.Product;

@Entity
public class Manufacturer extends Company {

	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
	private List<Product> productList = new ArrayList<Product>();

	/**
	 * Constructor
	 */
	public Manufacturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param name
	 * @param address
	 * @param contactDetails
	 */
	public Manufacturer(String name, Address address, String contactDetails) {
		super(name, address, contactDetails);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter
	 * @return the productList
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * Setter
	 * @param productList the productList to set
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public void addProduct(Product product) {
		this.productList.add(product);
		product.setManufacturer(this);
	}
	
	public void removeProduct(Product product) {
		this.productList.remove(product);
		product.setManufacturer(null);
	}
	
}

package pl.krzysztofskul.cadmdb.product;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.product.category.Category;

@Service
public class ProductService {

	private ProductRepo productRepo;

	/**
	 * @param productRepo
	 */
	@Autowired
	public ProductService(ProductRepo productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
	public void save(Product product) {
		productRepo.save(product);
	}
	
	public Product saveAndReturn(Product product) {
		return productRepo.save(product);
	}
	
	public List<Product> loadAll() {
		return productRepo.findAll();
	}
	
	public List<Product> LoadAllActive() {
		return productRepo.findAllByIsActiveOrderByCategoryNamePLAsc(true);
	}
	
	public Product loadById(Long id) {
		return productRepo.findById(id).get();
	}
	
	public Product loadRandom() {
		List<Product> productList = this.loadAll();
		Product product = productList.get(new Random().nextInt(productList.size()));
		return product;
	}

	public Product loadRandom(List<Product> productList) {
		Product product = productList.get(new Random().nextInt(productList.size()));
		return product;
	}
	
	public Product loadRandomByCategory(Category category) {
		List<Product> productList = productRepo.findAllByCategory(category);
		Product product = null;
		if (productList.size() > 0) {
			product = this.loadRandom(productList);	
		}
		return product;
	}
	
	public void deleteById(Long id) {
		productRepo.deleteById(id);
	}
	
}

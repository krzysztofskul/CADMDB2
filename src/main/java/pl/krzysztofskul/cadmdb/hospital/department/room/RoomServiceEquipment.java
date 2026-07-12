package pl.krzysztofskul.cadmdb.hospital.department.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.krzysztofskul.cadmdb.product.ProductService;
import pl.krzysztofskul.cadmdb.product.category.Category;
import pl.krzysztofskul.cadmdb.product.category.CategoryService;

@Service
public class RoomServiceEquipment {

	private RoomRepo roomRepo;
	private ProductService productService;
	private CategoryService categoryService;
	
	/**
	 * @param roomRepo
	 */
	@Autowired
	public RoomServiceEquipment(
			RoomRepo roomRepo
			, ProductService productService
			, CategoryService categoryService
			) {
		super();
		this.productService = productService;
		this.categoryService = categoryService;
		this.roomRepo = roomRepo;
	}
	
	

	
}

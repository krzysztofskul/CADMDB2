package pl.krzysztofskul.cadmdb.product.category;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/categories")
public class CategoryControllerRest {

	private CategoryService categoryService;

	/**
	 * Constructor
	 * @param categoryService
	 */
	@Autowired
	public CategoryControllerRest(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping("/")
	public List<Category> getCategory() {
		List<Category> categoryList = categoryService.loadAll();
		categoryList.forEach(c -> c.setCategoryChildren(null));
		categoryList.forEach(c -> c.setProductList(null));
		return categoryList;
	}
	
	@GetMapping("/code/{code}")
	public Category getCategoryByCode(@PathVariable String code) {
		Category category = categoryService.loadByCategoryCodeWithChildrenAndProducts(code);
		return category;
	}

}

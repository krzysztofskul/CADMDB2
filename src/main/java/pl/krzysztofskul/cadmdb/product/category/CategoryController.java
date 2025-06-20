package pl.krzysztofskul.cadmdb.product.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

	private CategoryService categoryService;

	/**
	 * Constructor
	 * @param categoryService
	 */
	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping("/category/medical")
	public String getCategoryMedical(
				@RequestParam(name = "code", required = false) String code,
				Model model
			) {
		List<Category> categoryList = categoryService.loadAllByCategoryCode("A");
		model.addAttribute("categoryListMenu", categoryList);
		
		if (code != null) {
			Category category = categoryService.loadByCategoryCodeWithProductList(code);
			model.addAttribute(category);
		}
		
		return "category/list";
	}
	
}

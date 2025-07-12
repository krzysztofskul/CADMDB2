package pl.krzysztofskul.cadmdb.company.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.product.Product;
import pl.krzysztofskul.cadmdb.product.ProductService;
import pl.krzysztofskul.cadmdb.product.category.CategoryService;
import pl.krzysztofskul.cadmdb.product.mounting.MountingTypeEnum;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

	private ManufacturerService manufacturerService;
	private ProductService productService;
	private CategoryService categoryService;
	private ModelAndView mav = new ModelAndView();

	/**
	 * Constructor
	 * @param manufacturerService
	 */
	@Autowired
	public ManufacturerController(
			ManufacturerService manufacturerService, 
			CategoryService categoryService,
			ProductService productService
			) {
		super();
		this.manufacturerService = manufacturerService;
		this.categoryService = categoryService;
		this.productService = productService;
	}
	
	@ModelAttribute
	public FunctionEnum[] getModelFunctionEnumList() {
		return FunctionEnum.values();
	}
	@ModelAttribute
	public MountingTypeEnum[] getModelMountingTypeEnumList() {
		return MountingTypeEnum.values();
	}
	
	@GetMapping
	public ModelAndView getManufacturers() {
		mav.clear();
			mav.setViewName("manufacturer/list");
			mav.addObject("manufacturerList", manufacturerService.loadAll());			
		return mav;
	}

	@GetMapping("/{manufacturerId}")
	public ModelAndView getManufacturers(
			@PathVariable(name = "manufacturerId") Long manufacturerId,
			@RequestParam(name = "edit", required = false) boolean edit
			) {
		mav.clear();
		mav.setViewName("manufacturer/id");
		// new manufacturer
		if (manufacturerId == 0) {
			mav.addObject("edit", true);			
			mav.addObject("manufacturer", new Manufacturer());			
		}
		// existing manufacturer
		if (manufacturerId != 0) {
			mav.addObject("manufacturer", manufacturerService.loadById(manufacturerId));
			if (edit == true) { // edit existing manufacturer
				mav.addObject("edit", true);
			}
		}
		return mav;
	}
	
	@PostMapping("/{manufacturerId}")
	public ModelAndView postManufacturer(
				@ModelAttribute Manufacturer manufacturer
			) {
		mav.clear();
		manufacturer = manufacturerService.saveAndReturn(manufacturer);
		mav.setViewName("redirect:/manufacturers/"+manufacturer.getId());
		return mav;
	}
	
	@GetMapping("/{manufacturerId}/delete")
	public ModelAndView deleteManufacturer(@PathVariable Long manufacturerId) {
		mav.clear();
//		manufacturerService.deleteById(manufacturerId);
		mav.setViewName("redirect:/manufacturers");
		return mav;
	}

	@GetMapping("/{manufacturerId}/products")
	public ModelAndView getManufacturerProducts(
			@PathVariable Long manufacturerId
			) {
		mav.clear();
		mav.addObject("manufacturer", manufacturerService.loadByIdWithProducts(manufacturerId));
		mav.setViewName("manufacturer/idWithProducts");
		return mav;
	}
	
	@GetMapping("/{manufacturerId}/create-product")
	public ModelAndView getManufacturerCreateProduct(
				@PathVariable Long manufacturerId
			) {
		mav.clear();
		Product product = new Product();
		product.setManufacturer(manufacturerService.loadById(manufacturerId));
		mav.addObject("categoryList", categoryService.loadAll());
		mav.addObject("product", product);
		mav.addObject("manufacturer", product.getManufacturer());
		mav.setViewName("manufacturer/idAddProduct");
		return mav;
	}
	
	@PostMapping("/{manufacturerId}/create-product")
	public ModelAndView postManufacturerCreateProduct(
			@PathVariable Long manufacturerId,	
			@ModelAttribute Product product
			) {
		mav.clear();
		Manufacturer manufacturer = manufacturerService.loadByIdWithProducts(manufacturerId);
		manufacturer.addProduct(product);
		manufacturerService.save(manufacturer);
		mav.setViewName("redirect:/manufacturers/"+manufacturerId+"/products");
		return mav;
	}
	
	@GetMapping("/{manufacturerId}/products/{productId}/delete-product")
	public ModelAndView getManufacturerDeleteProduct(
				@PathVariable Long manufacturerId,
				@PathVariable Long productId
			) {
		mav.clear();
		mav.setViewName("redirect:/manufacturers/"+manufacturerId+"/products");
		return mav;
	}
	
	@GetMapping("/{manufacturerId}/products/{productId}/deactivate-product")
	public ModelAndView getManufacturerDeactivateProduct(
			@PathVariable Long manufacturerId,
			@PathVariable Long productId
			){
		mav.clear();
		Product product = productService.loadById(productId);
		product.setIsActive(false);
		productService.save(product);
		mav.setViewName("redirect:/manufacturers/"+manufacturerId+"/products");
		return mav;
	}
	@GetMapping("/{manufacturerId}/products/{productId}/activate-product")
	public ModelAndView getManufacturerActivateProduct(
			@PathVariable Long manufacturerId,
			@PathVariable Long productId
			){
		mav.clear();
		Product product = productService.loadById(productId);
		product.setIsActive(true);
		productService.save(product);
		mav.setViewName("redirect:/manufacturers/"+manufacturerId+"/products");
		return mav;
	}
}

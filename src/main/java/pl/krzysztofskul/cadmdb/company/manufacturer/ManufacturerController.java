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

import pl.krzysztofskul.cadmdb.device.Device;
import pl.krzysztofskul.cadmdb.device.DeviceService;
import pl.krzysztofskul.cadmdb.device.category.CategoryService;
import pl.krzysztofskul.cadmdb.device.mounting.MountingTypeEnum;
import pl.krzysztofskul.cadmdb.function.FunctionEnum;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

	private ManufacturerService manufacturerService;
	private DeviceService deviceService;
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
			DeviceService deviceService
			) {
		super();
		this.manufacturerService = manufacturerService;
		this.categoryService = categoryService;
		this.deviceService = deviceService;
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
		Device device = new Device();
		device.setManufacturer(manufacturerService.loadById(manufacturerId));
		mav.addObject("categoryList", categoryService.loadAll());
		mav.addObject("device", device);
		mav.addObject("manufacturer", device.getManufacturer());
		mav.setViewName("manufacturer/idAddProduct");
		return mav;
	}
	
	@PostMapping("/{manufacturerId}/create-product")
	public ModelAndView postManufacturerCreateProduct(
			@PathVariable Long manufacturerId,	
			@ModelAttribute Device device
			) {
		mav.clear();
		Manufacturer manufacturer = manufacturerService.loadByIdWithProducts(manufacturerId);
		manufacturer.addDevice(device);
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
//		Manufacturer manufacturer = manufacturerService.loadByIdWithProducts(manufacturerId);
//		manufacturer.removeDevice(deviceService.loadById(productId));
//		deviceService.deleteById(productId);
//		manufacturerService.save(manufacturer);
		mav.setViewName("redirect:/manufacturers/"+manufacturerId+"/products");
		return mav;
	}
	
	@GetMapping("/{manufacturerId}/products/{productId}/deactivate-product")
	public ModelAndView getManufacturerDeactivateProduct(
			@PathVariable Long manufacturerId,
			@PathVariable Long productId
			){
		mav.clear();
		Device device = deviceService.loadById(productId);
		device.setIsActive(false);
		deviceService.save(device);
		mav.setViewName("redirect:/manufacturers/"+manufacturerId+"/products");
		return mav;
	}
	@GetMapping("/{manufacturerId}/products/{productId}/activate-product")
	public ModelAndView getManufacturerActivateProduct(
			@PathVariable Long manufacturerId,
			@PathVariable Long productId
			){
		mav.clear();
		Device device = deviceService.loadById(productId);
		device.setIsActive(true);
		deviceService.save(device);
		mav.setViewName("redirect:/manufacturers/"+manufacturerId+"/products");
		return mav;
	}
}

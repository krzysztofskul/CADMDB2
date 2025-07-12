package pl.krzysztofskul.cadmdb.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.krzysztofskul.cadmdb.function.FunctionEnum;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacility;
import pl.krzysztofskul.cadmdb.healthcarefacility.HealthcareFacilityService;
import pl.krzysztofskul.cadmdb.hospital.department.room.Room;
import pl.krzysztofskul.cadmdb.hospital.department.room.RoomService;
import pl.krzysztofskul.cadmdb.product.mounting.MountingTypeEnum;

@Controller
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	private RoomService roomService;
	private HealthcareFacilityService healthcareFacilityService;
	
	/**
	 * Constructor
	 * @param productService
	 */
	@Autowired
	public ProductController(ProductService productService, RoomService roomService, HealthcareFacilityService healthcareFacilityService) {
		super();
		this.productService = productService;
		this.roomService = roomService;
		this.healthcareFacilityService = healthcareFacilityService;
	}

	@GetMapping("{productId}")
	public String getProductById(
				@PathVariable Long productId
				, Model model
				, @RequestParam(name = "edit", required = false) String edit
			) {
		Product product = productService.loadById(productId);
		model.addAttribute("product", product);
		model.addAttribute("mountingTypeEnumList", MountingTypeEnum.values());
		model.addAttribute("functionEnumList", FunctionEnum.values());
		model.addAttribute("manufacturer", product.getManufacturer());
		if (edit == null) {
			edit = "false";
		}
		model.addAttribute("edit", edit);
		return "product/id";
	}
	
	@PostMapping("/{productId}")
	public String postProductById(
				@ModelAttribute Product product
			) {
		//Load all room where product is planned
		List<Room> roomList = roomService.loadAllByProductList_Id(product.getId());
 		//Remove old product from all rooms
		Product productDB = productService.loadById(product.getId());
		for (Room room : roomList) {
			healthcareFacilityService.removeProductFromRoom(productDB, room);
		}
		//Updated product in DB
		product = productService.saveAndReturn(product);
		//add product to all rooms and save rooms
		for (Room room : roomList) {
			room = healthcareFacilityService.addProductToRoom(product, room);
			roomService.saveAndReturn(room);
		}
		return "redirect:/products/"+product.getId();
	}
	
}

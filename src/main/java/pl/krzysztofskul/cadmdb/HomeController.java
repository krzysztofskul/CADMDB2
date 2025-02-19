package pl.krzysztofskul.cadmdb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;
import pl.krzysztofskul.cadmdb.hospital.HospitalTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategory;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategoryService;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategoryTestGenerator;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategory;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategoryService;
import pl.krzysztofskul.cadmdb.hospital.department.room.roomcategory.RoomCategoryTestGenerator;

@Controller
public class HomeController {

	private HospitalService hospitalService;
	private HospitalTestGenerator hospitalTestGenerator;
	private DepCategoryTestGenerator depCategoryTestGenerator;
	private DepCategoryService depCategoryService;
	private RoomCategoryTestGenerator roomCategoryTestGenerator;
	private RoomCategoryService roomCategoryService;
	
	/**
	 * @param hospitalService
	 */
	public HomeController(
			HospitalService hospitalService,
			HospitalTestGenerator hospitalTestGenerator,
			DepCategoryTestGenerator depCategoryTestGenerator,
			DepCategoryService depCategoryService,
			RoomCategoryTestGenerator roomCategoryTestGenerator,
			RoomCategoryService roomCategoryService
			) {
		super();
		this.hospitalService = hospitalService;
		this.hospitalTestGenerator = hospitalTestGenerator;
		this.depCategoryTestGenerator = depCategoryTestGenerator;
		this.depCategoryService = depCategoryService;
		this.roomCategoryTestGenerator = roomCategoryTestGenerator;
		this.roomCategoryService = roomCategoryService;
	}

	@GetMapping({"/login"})
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@GetMapping({"", "/", "/home"})
	public ModelAndView home() {
		return new ModelAndView("home");
	}
	
	@GetMapping("/initDbTest")
	private ModelAndView getHome() {
		ModelAndView mav = new ModelAndView("redirect:/home");
		this.initialDbTest();
		mav.addObject("hospitalList", hospitalService.loadAll());
		return mav;
	}
	
	private void initialDbTest() {
		//init room categories
		for (RoomCategory roomcategory : roomCategoryTestGenerator.iniListAndReturn()) {
			roomCategoryService.save(roomcategory);
		}
		
		//init departemt categories
		for (DepCategory depcategory : depCategoryTestGenerator.iniListAndReturn()) {
			depCategoryService.save(depcategory);
		}
		
		//init hospitals
		for (Hospital hospital : hospitalTestGenerator.iniListAndReturn()) {
			hospitalService.save(hospital);
		}
	}
	
}

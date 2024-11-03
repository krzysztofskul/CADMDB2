package pl.krzysztofskul.cadmdb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;
import pl.krzysztofskul.cadmdb.hospital.HospitalTestGenerator;

@Controller
public class HomeController {

	private HospitalService hospitalService;
	private HospitalTestGenerator hospitalTestGenerator;
	
	/**
	 * @param hospitalService
	 */
	public HomeController(
			HospitalService hospitalService,
			HospitalTestGenerator hospitalTestGenerator
			) {
		super();
		this.hospitalService = hospitalService;
		this.hospitalTestGenerator = hospitalTestGenerator;
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
		for (Hospital hospital : hospitalTestGenerator.iniListAndReturn()) {
			hospitalService.save(hospital);
		}
	}
	
}

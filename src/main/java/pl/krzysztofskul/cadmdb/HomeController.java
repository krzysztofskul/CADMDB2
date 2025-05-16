package pl.krzysztofskul.cadmdb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.hospital.HospitalService;

@Controller
public class HomeController {

	private HospitalService hospitalService;
	private HomeService homeService;
	
	/**
	 * @param hospitalService
	 */
	public HomeController(
			HospitalService hospitalService,
			HomeService homeService
			) {
		super();
		this.hospitalService = hospitalService;
		this.homeService = homeService;
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
	public ModelAndView getInitDbDemo() {
		ModelAndView mav = new ModelAndView("redirect:/home");
		homeService.initDbTest();
		mav.addObject("hospitalList", hospitalService.loadAll());
		return mav;
	}
	
	@GetMapping("/initDbEssentials")
	public ModelAndView getInitDbEssentials() {
		homeService.initialDbEssentials();
		return new ModelAndView("redirect:/home");
	}
	
}

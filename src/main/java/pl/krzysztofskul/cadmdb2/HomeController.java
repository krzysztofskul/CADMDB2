package pl.krzysztofskul.cadmdb2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb2.hospital.Hospital;
import pl.krzysztofskul.cadmdb2.hospital.HospitalService;
import pl.krzysztofskul.cadmdb2.hospital.HospitalTestGenerator;

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

	@GetMapping("/test")
	private ModelAndView getHome() {
		ModelAndView mav = new ModelAndView("home");
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

package pl.krzysztofskul.cadmdb.hospital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test/hospitals")
public class HospitalController {

	private HospitalService hospitalService;
	private ModelAndView modelAndView = new ModelAndView();
	
	/**
	 * @param hospitalService
	 */
	@Autowired
	public HospitalController(HospitalService hospitalService) {
		super();
		this.hospitalService = hospitalService;
	}

	@GetMapping
	public ModelAndView getHospitals() {
		ModelAndView mav = new ModelAndView("hospital/list");
		List<Hospital> hospitalList = hospitalService.loadAll();
		mav.addObject("hospitalList", hospitalList);
		return mav;
	}
	
	@GetMapping("/{hospitalId}")
	public ModelAndView getHospitalById(
			@PathVariable Long hospitalId
		) {
		ModelAndView mav = new ModelAndView("hospital/id");
		if (hospitalId.equals(Long.valueOf(0))) {
			mav.addObject("hospital", new Hospital());
			mav.addObject("edit", true);
		} else {
			mav.addObject("hospital", hospitalService.loadById(hospitalId));
		}
		return mav;
	}
	
	@PostMapping
	public ModelAndView postHospital(
			@RequestParam(required = false, name = "backToPage") String backToPage,
			@ModelAttribute Hospital hospital
			) {
		
		hospital = hospitalService.saveAndReturn(hospital);
		modelAndView.addObject("hospital", hospital);
		
		if (backToPage == null) {
			modelAndView.setViewName("redirect:/test/hospitals/"+hospital.getId());
		} else {
			modelAndView.setViewName(backToPage);
		}
		
		
		return modelAndView;
	}
}

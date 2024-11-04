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
@RequestMapping("/hospitals")
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
			@PathVariable Long hospitalId,
			@RequestParam(required = false, name ="edit") boolean edit
		) {
		ModelAndView mav = new ModelAndView("hospital/id");
		if (hospitalId.equals(Long.valueOf(0))) {
			mav.addObject("hospital", new Hospital());
			mav.addObject("edit", true);
		} else {
			if (edit == true) {
				mav.addObject("edit", true);
			}
			mav.addObject("hospital", hospitalService.loadById(hospitalId));
		}
		return mav;
	}
	
	@GetMapping("/{hospitalId}/departments")
	public ModelAndView getHospitalByIdWithDepartments(@PathVariable Long hospitalId) {
		modelAndView.setViewName("hospital/idWithDepartments");
		modelAndView.addObject("hospital", hospitalService.loadByIdWithDepartments(hospitalId));
		return modelAndView;
	}
	
	@PostMapping
	public ModelAndView postHospital(
			@RequestParam(required = false, name = "backToPage") String backToPage,
			@ModelAttribute Hospital hospital
			) {
		
		hospital = hospitalService.saveAndReturn(hospital);
		modelAndView.addObject("hospital", hospital);
		
		if (backToPage == null) {
			modelAndView.setViewName("redirect:/hospitals/"+hospital.getId());
		} else {
			modelAndView.setViewName(backToPage);
		}
		
		
		return modelAndView;
	}
}

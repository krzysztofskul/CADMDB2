package pl.krzysztofskul.cadmdb.hospital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test/hospitals")
public class HospitalController {

	private HospitalService hospitalService;
	
	/**
	 * @param hospitalService
	 */
	@Autowired
	public HospitalController(HospitalService hospitalService) {
		super();
		this.hospitalService = hospitalService;
	}

	@GetMapping("/")
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
		Hospital hospital = hospitalService.loadById(hospitalId);
		mav.addObject("hospital", hospital);
		return mav;
	}
	
}

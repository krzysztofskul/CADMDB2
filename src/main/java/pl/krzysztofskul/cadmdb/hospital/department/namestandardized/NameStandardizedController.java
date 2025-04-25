package pl.krzysztofskul.cadmdb.hospital.department.namestandardized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;

@Controller("department_namecustomedcontroller")
public class NameStandardizedController {

	private NameStandardizedService nameStandardizedService;
	private HospitalService hospitalService;

	/**
	 * Constructor
	 * @param nameStandardizedService
	 */
	@Autowired
	public NameStandardizedController(
			NameStandardizedService nameStandardizedService,
			HospitalService hospitalService
			) {
		super();
		this.nameStandardizedService = nameStandardizedService;
		this.hospitalService = hospitalService;
	}
	
	@GetMapping("/hospitals/{hospitalId}/departmentstandardizednames/{nameStandardizedId}")
	public ModelAndView getDepartmentStandardizedNames(
				@PathVariable(required = false) Long nameStandardizedId,
				@PathVariable Long hospitalId,
				@RequestParam(required = false) String edit
			) {
		ModelAndView mav = new ModelAndView();
		switch (edit) {
			case "true": {
				if (nameStandardizedId == 0) {
					NameStandardized nameStandardized = new NameStandardized(hospitalService.loadById(hospitalId));
					mav.addObject("nameStandardized", nameStandardized);
					mav.addObject(nameStandardized.getHospital());
					mav.setViewName("hospital/idAddDepartmentStandardizedName");
				}
				break;
			}
			case "false": {
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + edit);
		}
		
		return mav;
	}
	
	@PostMapping("/hospitals/{hospitalId}/departmentstandardizednames/{nameStandardizedId}")
	public ModelAndView postDepartmentStandardizedNames(
				@ModelAttribute NameStandardized nameStandardized,
				@PathVariable Long hospitalId,
				@PathVariable Long nameStandardizedId
			) {
		ModelAndView mav = new ModelAndView();
		nameStandardized = nameStandardizedService.saveAndReturn(nameStandardized);
		Hospital hospital = hospitalService.loadByIdWithDepartments(nameStandardized.getHospital().getId());
		mav.addObject("edit", false);
		mav.addObject("hospital", hospital);
		
		mav.setViewName("redirect:/hospitals/"+hospital.getId()+"/departments");
		
		return mav;
	}
	
}

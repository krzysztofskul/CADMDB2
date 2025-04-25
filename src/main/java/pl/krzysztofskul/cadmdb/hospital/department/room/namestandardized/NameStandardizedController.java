package pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.hospital.Hospital;
import pl.krzysztofskul.cadmdb.hospital.HospitalService;
import pl.krzysztofskul.cadmdb.hospital.department.DepartmentService;

@Controller("room_namecustomedcontroller")
public class NameStandardizedController {

	private NameStandardizedService nameStandardizedService;
	private HospitalService hospitalService;
	private DepartmentService departmentService;

	/**
	 * Constructor
	 * @param nameStandardizedService
	 */
	@Autowired
	public NameStandardizedController(
			NameStandardizedService nameStandardizedService,
			HospitalService hospitalService,
			DepartmentService departmentService
			) {
		super();
		this.nameStandardizedService = nameStandardizedService;
		this.hospitalService = hospitalService;
		this.departmentService = departmentService;
	}
	
	@GetMapping("/hospitals/{hospitalId}/departments/{departmentId}/roomstandardizednames/{nameStandardizedId}")
	public ModelAndView getDepartmentStandardizedNames(
				@PathVariable(required = false) Long nameStandardizedId,
				@PathVariable Long hospitalId,
				@PathVariable Long departmentId,
				@RequestParam(required = false) String edit
			) {
		ModelAndView mav = new ModelAndView();
		switch (edit) {
			case "true": {
				if (nameStandardizedId == 0) {
					NameStandardized nameStandardized = new NameStandardized(hospitalService.loadById(hospitalId));
					mav.addObject("nameStandardized", nameStandardized);
					mav.addObject("department", departmentService.loadById(departmentId));
					mav.addObject(nameStandardized.getHospital());
					mav.setViewName("hospital/department/idAddRoomStandardizedName");
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
	
	@PostMapping("/hospitals/{hospitalId}/departments/{departmentId}/roomstandardizednames/{nameStandardizedId}")
	public ModelAndView postDepartmentStandardizedNames(
				@ModelAttribute NameStandardized nameStandardized,
				@PathVariable Long hospitalId,
				@PathVariable Long departmentId,
				@PathVariable Long nameStandardizedId
			) {
		ModelAndView mav = new ModelAndView();
		nameStandardized = nameStandardizedService.saveAndReturn(nameStandardized);
		Hospital hospital = hospitalService.loadByIdWithDepartments(nameStandardized.getHospital().getId());
		mav.addObject("edit", true);
		mav.addObject("hospital", hospital);
		
		mav.setViewName("redirect:/departments/"+departmentId+"/rooms");
		
		return mav;
	}
	
}

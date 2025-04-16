package pl.krzysztofskul.cadmdb.hospital;

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
import org.springframework.web.servlet.ModelAndView;

import pl.krzysztofskul.cadmdb.hospital.department.Department;
import pl.krzysztofskul.cadmdb.hospital.department.depcategory.DepCategoryService;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

	private HospitalService hospitalService;
	private DepCategoryService depCategoryService;
	private ModelAndView modelAndView = new ModelAndView();
	
	/**
	 * @param hospitalService
	 */
	@Autowired
	public HospitalController(
				HospitalService hospitalService,
				DepCategoryService depCategoryService
			) {
		super();
		this.depCategoryService = depCategoryService;
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
	
	@PostMapping("/{hospitalId}")
	public ModelAndView postHospitalById(
				@ModelAttribute Hospital hospital
			) {
		hospitalService.save(hospital);
		modelAndView.setViewName("redirect:/hospitals/"+hospital.getId());
		return modelAndView;
	}
	
	@GetMapping("/{hospitalId}/departments")
	public ModelAndView getHospitalByIdWithDepartments(
				@PathVariable Long hospitalId,
				@RequestParam(required = false) boolean edit
			) {
		Hospital hospital = hospitalService.loadByIdWithDepartments(hospitalId);
		modelAndView.clear();
		modelAndView.setViewName("hospital/idWithDepartments");
		modelAndView.addObject("hospital", hospital);
		modelAndView.addObject("edit", false);
		if (edit == true) {
			modelAndView.addObject("edit", true);
			modelAndView.setViewName("hospital/idAddDepartment");
			modelAndView.addObject("depCategoryList", depCategoryService.loadAll());
			modelAndView.addObject("department", new Department(hospitalService.loadById(hospitalId)));
		}

		return modelAndView;
	}
	
	@PostMapping("/{hospitalId}/departments")
	public ModelAndView postAddDepartmentToHospital(
			@RequestParam(required = false, name = "backToPage") String backToPage,
			@ModelAttribute Department department
			) {
		
		Hospital hospital = hospitalService.loadById(department.getHospital().getId());
		hospital.addDepartment(department);
		hospitalService.save(hospital);
		
		if (backToPage == null) {
			modelAndView.setViewName("redirect:/hospitals/"+hospital.getId()+"/departments");
		} else {
			modelAndView.setViewName(backToPage);
		}
		modelAndView.addObject("edit", false);
		
		return modelAndView;
	}
	
	@GetMapping("/{hospitalId}/delete")
	public ModelAndView deleteById(
				@PathVariable Long hospitalId,
				@RequestParam(required = false, name = "backToPage") String backToPage
			) {
		if (backToPage != null) {
			modelAndView.setViewName(backToPage);	
		} else {
			modelAndView.setViewName("redirect:/hospitals");
		}
		hospitalService.deleteById(hospitalId);
		return modelAndView;
	}
	
}

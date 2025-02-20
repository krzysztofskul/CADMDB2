package pl.krzysztofskul.cadmdb.hospital.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

	private DepartmentService departmentService;
	private ModelAndView mav = new ModelAndView();

	/**
	 * @param departmentService
	 */
	@Autowired
	public DepartmentController(
			DepartmentService departmentService
			) {
		super();
		this.departmentService = departmentService;
	}
	
	@GetMapping("/{id}")
	public ModelAndView getDepartmentById(@PathVariable Long id) {
		Department department = departmentService.loadById(id);
		mav.addObject("department", department);
		mav.setViewName("hospital/department/id");
		return mav;
	}
	
	@GetMapping("/{id}/delete")
	public ModelAndView departmentDeleteById(
				@PathVariable Long id
			) {
		Long hospitalId = departmentService.loadById(id).getHospital().getId();
		departmentService.depeteById(id);
		mav.setViewName("redirect:/hospitals/"+hospitalId+"/departments");
		return mav;
	}
	
}

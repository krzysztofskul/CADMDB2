package pl.krzysztofskul.cadmdb.hospital.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hospitals/{hospitalId}/departments")
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
	
}

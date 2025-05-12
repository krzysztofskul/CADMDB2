package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.krzysztofskul.cadmdb.hospital.department.DepartmentService;

@Controller
@RequestMapping("/departments/architectural-data")
public class DataArchDepartmentController {

	private pl.krzysztofskul.cadmdb.hospital.department.DepartmentService departmentService;
	
	/**
	 * Constructor
	 * @param departmentService
	 */
	@Autowired
	public DataArchDepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@GetMapping("/{id}")
	public String getDepartmentsArchitecturalData(
				@PathVariable Long id,
				Model model
			) {
		model.addAttribute("department", departmentService.loadByIdWithDataArchDepartment(id));
		return "hospital/department/idWithDataArch";
	}

	
}

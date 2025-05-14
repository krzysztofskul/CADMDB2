package pl.krzysztofskul.cadmdb.healthcarefacility.dataarch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.krzysztofskul.cadmdb.hospital.HospitalService;

@Controller
@RequestMapping("/hospitals/architectural-data")
public class DataArchHospitalController {

	private HospitalService hospitalService;
	
	/**
	 * Constructor
	 * @param departmentService
	 */
	@Autowired
	public DataArchHospitalController(HospitalService hospitalService) {
		super();
		this.hospitalService = hospitalService;
	}

	@GetMapping("/{id}")
	public String getHospitalsArchitecturalData(
				@PathVariable Long id,
				Model model
			) {
		model.addAttribute("hospital", hospitalService.loadByIdWithDataArchHospital(id));
		return "hospital/idWithDataArch";
	}

	
}

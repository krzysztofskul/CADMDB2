package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	private DepartmentRepo departmentRepo;

	/**
	 * @param departmentRepo
	 */
	@Autowired
	public DepartmentService(DepartmentRepo departmentRepo) {
		super();
		this.departmentRepo = departmentRepo;
	}
	
	public Department saveAndReturn(Department department) {
		return departmentRepo.save(department);
	}
	
	public List<Department> loadAll() {
		return departmentRepo.findAll();
	}
	
	public List<Department> loadAllByHospitalId(Long hospitalId) {
		return departmentRepo.findAllByHospitalId(hospitalId);
	}
	
	public Department loadById(Long id) {
		return departmentRepo.findById(id).get();
	}
	
}

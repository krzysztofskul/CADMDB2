package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.List;

import org.hibernate.Hibernate;
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
	
	public void save(Department department) {
		departmentRepo.save(department);
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

	public Department loadByIdWithRoomList(Long id) {
		Department department = this.loadById(id);
		Hibernate.initialize(department.getRoomList());
		return department;
	}
	
	public void depeteById(Long id) {
		departmentRepo.deleteById(id);
	}

}

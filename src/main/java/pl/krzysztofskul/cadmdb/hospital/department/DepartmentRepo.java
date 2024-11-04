package pl.krzysztofskul.cadmdb.hospital.department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long>{

	List<Department> findAllByHospitalId(Long hospitalId);
	
}

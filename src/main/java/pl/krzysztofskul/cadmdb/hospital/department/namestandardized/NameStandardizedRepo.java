package pl.krzysztofskul.cadmdb.hospital.department.namestandardized;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("Department_NameStandardizedRepo")
public interface NameStandardizedRepo extends JpaRepository<NameStandardized, Long>{
	
	List<NameStandardized> findAllByHospitalId(Long hospitalId);

	List<NameStandardized> findAllByHospitalIdOrHospitalIsNullOrderByNameStandardizedPlAsc(Long hospitalId);
	
}

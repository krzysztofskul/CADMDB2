package pl.krzysztofskul.cadmdb.hospital;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

	private HospitalRepo hospitalRepo;

	/**
	 * @param hospitalRepo
	 */
	@Autowired
	public HospitalService(HospitalRepo hospitalRepo) {
		super();
		this.hospitalRepo = hospitalRepo;
	}
	
	public void save(Hospital hospital) {
		hospitalRepo.save(hospital);
	}

	public Hospital saveAndReturn(Hospital hospital) {
		return hospitalRepo.save(hospital);
	}
	
	public List<Hospital> loadAll() {
		return hospitalRepo.findAll();
	}

	public Hospital loadById(Long hospitalId) {
		return hospitalRepo.findById(hospitalId).get();
	}

	public Object loadByIdWithDepartments(Long hospitalId) {
		Hospital hospital = this.loadById(hospitalId);
		Hibernate.initialize(hospital.getDepartmentList());
		return hospital;
	}

	public void deleteById(Long hospitalId) {
		hospitalRepo.deleteById(hospitalId);
	}
	
}

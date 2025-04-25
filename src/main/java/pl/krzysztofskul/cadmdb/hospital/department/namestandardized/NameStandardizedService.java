package pl.krzysztofskul.cadmdb.hospital.department.namestandardized;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysztofskul.cadmdb.random.Random;

@Service("Department_NameStandardizedService")
public class NameStandardizedService {

	private NameStandardizedRepo nameStandardizedRepo;

	/**
	 * Constructor
	 * @param nameStandardizedRepo
	 */
	@Autowired 
	public NameStandardizedService(NameStandardizedRepo nameStandardizedRepo) {
		super();
		this.nameStandardizedRepo = nameStandardizedRepo;
	}

	public void save(NameStandardized nameStandardized) {
		nameStandardizedRepo.save(nameStandardized);
	}
	
	public NameStandardized saveAndReturn(NameStandardized nameStandardized) {
		return nameStandardizedRepo.save(nameStandardized);
	}
	
	public NameStandardized loadById(Long id) {
		return nameStandardizedRepo.findById(id).get();
	}
	
	public List<NameStandardized> loadAll() {
		return nameStandardizedRepo.findAll();
	}

	/**
	 * Load a list of all standardized department names available to a given hospital.
	 * <p>Returns names assigned to a given hospital and global ones (hospital is null)</p>
	 * @param hospitalId the ID of the hospital
	 * @return a list of NameStandardized entities for that hospital and any global ones
	 */
	public List<NameStandardized> loadAllByHospitalIdOrHospitalIsNull(Long hospitalId) {
		return nameStandardizedRepo.findAllByHospitalIdOrHospitalIsNullOrderByNameStandardizedPlAsc(hospitalId);
	}
	
	public List<NameStandardized> loadAllByHospitalId(Long hospitalId) {
		return nameStandardizedRepo.findAllByHospitalId(hospitalId);
	}
	
	public List<NameStandardized> loadRandomList(int amount) {
		List<NameStandardized> nameStandardizedList = this.loadAll();
		List<NameStandardized> nameStandardizedlistAllowed = new ArrayList<NameStandardized>();
		for (int i = 0; i < amount; i++) {
			nameStandardizedlistAllowed.add(nameStandardizedList.get(Random.randomInt(0, nameStandardizedList.size())));
		}
		nameStandardizedList.retainAll(nameStandardizedlistAllowed);
		return nameStandardizedList;
	}
	
	public void deleteById(Long id) {
		nameStandardizedRepo.deleteById(id);
	}
	
}
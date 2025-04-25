package pl.krzysztofskul.cadmdb.hospital.department.room.namestandardized;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysztofskul.cadmdb.random.Random;

@Service("Room_NameStandardizedService")
public class NameStandardizedService {

	private NameStandardizedRepo nameStandardizedRepo;

	/**
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
	 * Load a list of all standardized room names available to a given hospital.
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
		List<NameStandardized> nameStandardizedListAllowed = new ArrayList<NameStandardized>();
		for (int i = 0; i < amount; i++) {
			nameStandardizedListAllowed.add(nameStandardizedList.get(Random.randomInt(0, nameStandardizedList.size())));
		}
		nameStandardizedList.retainAll(nameStandardizedListAllowed);
		return nameStandardizedList;
	}
	
	public void deleteById(Long id) {
		nameStandardizedRepo.deleteById(id);
	}
	
}

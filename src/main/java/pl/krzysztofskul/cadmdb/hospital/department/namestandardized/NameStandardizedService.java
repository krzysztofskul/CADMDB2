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
	
	public List<NameStandardized> loadAll() {
		return nameStandardizedRepo.findAll();
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
	
}
